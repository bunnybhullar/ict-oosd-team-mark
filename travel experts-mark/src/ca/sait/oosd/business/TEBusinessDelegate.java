package ca.sait.oosd.business;

import ca.sait.oosd.hibernate.Agencies;
import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Packages;
import ca.sait.oosd.hibernate.Products;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.hibernate.Suppliers;
import java.util.Collection;

import ca.sait.oosd.travel.packages.PackageProductSuppliers;

/**
 * @author duminda
 * Business delegate is a way to hide the complexity of the business service from the client. Delegates used to
 * decouple presentation tier and the business service layer. Delegates gives an abstraction to the client tier.
 *  
 */
public interface TEBusinessDelegate {

	public TEObject save(TEObject teObject) throws TEBusinessException;
    public void delete(TEObject teObject) throws TEBusinessException;
    public TEObject update(TEObject teObject) throws TEBusinessException;
	public Collection<Packages> getPackageCollection();
	public Collection<PackageProductSuppliers> getPackageProductSupplierCollection();
	public Collection<Agents> getAgentCollection();
	public Collection<Products> getProductsCollection();
	public Collection<ProductsSuppliers> getProductSuppliersCollection();
	public Collection<Suppliers> getSuppliersCollection();
	public Collection<Agencies> getAgenciesCollection();
	
}
