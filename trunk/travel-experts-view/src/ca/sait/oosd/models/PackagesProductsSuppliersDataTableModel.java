package ca.sait.oosd.models;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.hibernate.PackagesProductsSuppliers;
import java.util.Vector;

/**
 *
 * @author duminda
 * 
 */
public class PackagesProductsSuppliersDataTableModel extends TERelationshipDataTableModel{

    private Vector<PackagesProductsSuppliers> packagesProductsSuppliersVector;

    public PackagesProductsSuppliersDataTableModel(String[] headerNames, Vector<PackagesProductsSuppliers> packagesProductsSuppliersVector) {
        super(headerNames);
        
        this.packagesProductsSuppliersVector = packagesProductsSuppliersVector;
    }
    
    @Override
    public int getRowCount() {
        return packagesProductsSuppliersVector.size();
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PackagesProductsSuppliers packagesProductsSuppliers = packagesProductsSuppliersVector.elementAt(rowIndex);

        switch(columnIndex) {
            case 0:
                return packagesProductsSuppliers.getPackageId().getPkgname();
            case 1:
                return packagesProductsSuppliers.getProductsSuppliersid().getProducts().getProdname();
            case 2:
                return packagesProductsSuppliers.getProductsSuppliersid().getSuppliers().getSupname();
            case 3:
                return packagesProductsSuppliers.getPackageId().getPkgstartdate();
            case 4:
                return packagesProductsSuppliers.getPackageId().getPkgenddate();
            case 5:
                return packagesProductsSuppliers.getPackageId().getPkgdesc();
            default:
                return "";
        }
    }

    public PackagesProductsSuppliers getSelectedValue(int rowIndex) {
        if(rowIndex != -1) {
            PackagesProductsSuppliers packagesProductsSuppliers = packagesProductsSuppliersVector.elementAt(rowIndex);
            return packagesProductsSuppliers;

        } else {
            return null;

        }
    }

    @Override
    public void addRelationship(TEObject teObject) {
        packagesProductsSuppliersVector.add((PackagesProductsSuppliers) teObject);

        fireTableDataChanged();
        
    }

    @Override
    public void removeRelationship(int rowNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
