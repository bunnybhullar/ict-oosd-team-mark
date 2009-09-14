/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.sait.oosd.listeners;

import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.components.PackageDragList;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.hibernate.PackagesProductsSuppliers;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.models.PackagesProductsSuppliersDataTableModel;
import ca.sait.oosd.models.ProductSupplierDataTableModel;
import java.awt.datatransfer.DataFlavor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

/**
 *
 * @author duminda
 * 
 */
public class TableTransferHandler extends TransferHandler{

	private static final long serialVersionUID = 1L;
	
	private PackageDragList source;
    private TEBusinessDelegate delegate;
    private TEJFrame parent;
    private ProductSupplierDataTableModel productSupplierDataTableModel;
    private PackagesProductsSuppliersDataTableModel packagesProductsSuppliersDataTableModel;

    public TableTransferHandler() {

    }

    public TableTransferHandler(PackageDragList source, TEBusinessDelegate delegate, TEJFrame parent,
            ProductSupplierDataTableModel productSupplierDataTableModel,
            PackagesProductsSuppliersDataTableModel packagesProductsSuppliersDataTableModel) {

        this.source = source;
        this.delegate = delegate;
        this.parent = parent;
        this.productSupplierDataTableModel = productSupplierDataTableModel;
        this.packagesProductsSuppliersDataTableModel = packagesProductsSuppliersDataTableModel;
        
    }

    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }

        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean importData(TransferSupport support) {
        JTable.DropLocation dropLocation = (JTable.DropLocation)support.getDropLocation();
        int row = dropLocation.getRow();

        int selection = JOptionPane.showConfirmDialog(parent,
                "Do you want to make the relationship",
                "Make new Relationship", JOptionPane.YES_NO_OPTION);

        if(selection == JOptionPane.YES_NO_OPTION) {
            ProductsSuppliers productsSuppliers = productSupplierDataTableModel.getSelectedRow(row);

            makeRelationship(productsSuppliers);

            return true;

        } else {
            return false;

        }

    }

    private void makeRelationship(ProductsSuppliers targetProductsSuppliers) {
        try {
            PackagesProductsSuppliers packagesProductsSuppliers = delegate.makePackagesProductsSuppliersRelationship(
                    this.source.getSelectedOption(), targetProductsSuppliers);

            if(packagesProductsSuppliers != null) {
                updatePackagesProductsSuppliersTableModel(packagesProductsSuppliers);
            }

        } catch (TEBusinessException ex) {
            Logger.getLogger(TableTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    private void updatePackagesProductsSuppliersTableModel(PackagesProductsSuppliers packagesProductsSuppliers) {
        packagesProductsSuppliersDataTableModel.addRelationship(packagesProductsSuppliers);
    }
}
