package ca.sait.oosd.listeners;

import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.components.DragList;
import ca.sait.oosd.components.DropList;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.models.ProductSupplierDataTableModel;
import java.awt.Point;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author duminda
 * 
 */
public class ListDropListener implements DropTargetListener{

    private DropList target;
    private DragList source;
    private TEBusinessDelegate delegate;
    private TEJFrame parent;
    private ProductSupplierDataTableModel productSupplierDataTableModel;


    public ListDropListener() {

    }

    public ListDropListener(DropList target, DragList source, TEBusinessDelegate delegate, TEJFrame parent,
            ProductSupplierDataTableModel productSupplierDataTableModel) {
        this.target = target;
        this.source = source;
        this.delegate = delegate;
        this.parent = parent;
        this.productSupplierDataTableModel = productSupplierDataTableModel;
        
    }

    public void dragEnter(DropTargetDragEvent dtde) {
        
    }

    public void dragOver(DropTargetDragEvent dtde) {
            Point point = dtde.getLocation();
            int location = target.locationToIndex(point);
            target.setSelectedIndex(location);
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        
    }

    public void dragExit(DropTargetEvent dte) {
        
    }

    public void drop(DropTargetDropEvent dtde) {
        int selection = JOptionPane.showConfirmDialog(parent,
                "Do you want to make the relationship",
                "Make new Relationship", JOptionPane.YES_NO_OPTION);

        if(selection == JOptionPane.YES_NO_OPTION) {
            //get the selected items and save it
            TEObject sourceSelection = (TEObject) source.getSelectedOption();
            TEObject tagetSelection = (TEObject) target.getSelectedValue();

            makeRelationship(sourceSelection, tagetSelection);
            
        }
    }

    private void makeRelationship(TEObject sourceSelection, TEObject tagetSelection) {
        try {
            ProductsSuppliers productsSuppliers = delegate.makeProductSupplierRelationship(sourceSelection, tagetSelection);
            
            if(productsSuppliers != null) {
                updateProductSupplierRelationshipModel(productsSuppliers);
                
            }


        } catch (TEBusinessException ex) {
            Logger.getLogger(ListDropListener.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    private void updateProductSupplierRelationshipModel(ProductsSuppliers productsSuppliers) {
        productSupplierDataTableModel.addRelationship(productsSuppliers);
        
    }

}
