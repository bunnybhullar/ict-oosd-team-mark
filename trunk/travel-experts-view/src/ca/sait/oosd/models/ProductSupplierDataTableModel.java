package ca.sait.oosd.models;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import java.util.Vector;

/**
 *
 * @author duminda
 * @purpose This is the concrete implementation of the product supplier data table model
 * 
 */
public class ProductSupplierDataTableModel extends TERelationshipDataTableModel{

	private static final long serialVersionUID = 1L;
	private Vector<ProductsSuppliers> productSupplierVector;

    public ProductSupplierDataTableModel(String[] headerNames, Vector<ProductsSuppliers> productSupplierVector) {
        super(headerNames);
        this.productSupplierVector = productSupplierVector;
        
    }

    @Override
    public int getRowCount() {
        return productSupplierVector.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductsSuppliers productsSuppliers = productSupplierVector.elementAt(rowIndex);

        switch(columnIndex){
            case 0 :
                return productsSuppliers.getProductsupplierid();

            case 1:
                return productsSuppliers.getProducts().getProdname();

            case 2:
                return productsSuppliers.getSuppliers().getSupname();
                
            default:
                return "";

        }
    }

    public ProductsSuppliers getSelectedRow(int rowIndex) {
        if(rowIndex != -1) {
            ProductsSuppliers productsSuppliers = productSupplierVector.elementAt(rowIndex);
            return productsSuppliers;

        } else {
            return null;

        }
    }

    @Override
    public void addRelationship(TEObject teObject) {
        productSupplierVector.add((ProductsSuppliers) teObject);

        fireTableDataChanged();
    }

    @Override
    public void removeRelationship(int rowNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
