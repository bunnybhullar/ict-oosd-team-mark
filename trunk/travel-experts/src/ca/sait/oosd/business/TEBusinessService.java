package ca.sait.oosd.business;

import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Customers;
import ca.sait.oosd.hibernate.Packages;
import ca.sait.oosd.hibernate.PackagesProductsSuppliers;
import ca.sait.oosd.hibernate.Products;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.hibernate.Suppliers;
import ca.sait.oosd.util.TENullValueException;
import java.util.Collection;

/**
 * @author duminda
 * Business service layer is the actual implemenation of the business logic. When Travel Experts need to integrate
 * their web tier, they can use this business service layer, hence all modifications to the business layer applies
 * to both desktop client and the web client. 
 *
 */
public interface TEBusinessService {

	public TEObject save(TEObject teObject) throws TENullValueException;
    public void delete(TEObject teObject) throws TENullValueException;
    public TEObject update(TEObject teObject) throws TENullValueException;
    public Collection<Packages> getPackageCollection();
	public Collection<PackagesProductsSuppliers> getPackageProductSupplierCollection();
	public Collection<Agents> getAgentCollection();
	public Collection<Products> getProductsCollection();
	public Collection<ProductsSuppliers> getProductSuppliersCollection();
	public Collection<Suppliers> getSuppliersCollection();
    public ProductsSuppliers makeProductSupplierRelationship(ProductsSuppliers productsSuppliers);
    public PackagesProductsSuppliers makePackagesProductsSuppliersRelationship(PackagesProductsSuppliers packagesProductsSuppliers);
    public Collection<Customers> getCustomersForAgent(Agents agent);
    public Collection<Customers> getCustomersCollection();
    
}
