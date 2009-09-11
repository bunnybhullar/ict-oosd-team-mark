package ca.sait.oosd.hibernate;

import ca.sait.oosd.business.TEObject;

/**
 *
 * @author duminda
 */
public class PackagesProductsSuppliers extends TEObject implements java.io.Serializable{

    private Packages packageId;
    private ProductsSuppliers productsSuppliersid;

    public PackagesProductsSuppliers() {

    }

    public PackagesProductsSuppliers(Packages packageId, ProductsSuppliers productsSuppliersid) {
        this.packageId = packageId;
        this.productsSuppliersid = productsSuppliersid;
    }

    /**
     * @return the packageId
     */
    public Packages getPackageId() {
        return packageId;
    }

    /**
     * @param packageId the packageId to set
     */
    public void setPackageId(Packages packageId) {
        this.packageId = packageId;
    }

    /**
     * @return the productsSuppliersid
     */
    public ProductsSuppliers getProductsSuppliersid() {
        return productsSuppliersid;
    }

    /**
     * @param productsSuppliersid the productsSuppliersid to set
     */
    public void setProductsSuppliersid(ProductsSuppliers productsSuppliersid) {
        this.productsSuppliersid = productsSuppliersid;
    }
    
    @Override
    public String toString() {
        return "";
    }


}
