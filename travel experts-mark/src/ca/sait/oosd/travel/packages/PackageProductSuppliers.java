package ca.sait.oosd.travel.packages;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.travel.products.ProductSuppliers;

/**
 * @author duminda
 * Encapsulate package product supplier relationship details
 *
 */
public class PackageProductSuppliers extends TEObject{

	private Packages packages;
	private ProductSuppliers productSupplier;
	
	public Packages getPackages() {
		return packages;
	}
	
	public void setPackages(Packages packages) {
		this.packages = packages;
	}
	
	public ProductSuppliers getProductSupplier() {
		return productSupplier;
	}
	
	public void setProductSupplier(ProductSuppliers productSupplier) {
		this.productSupplier = productSupplier;
	}

	@Override
	public String toString() {
		return "";
	}
	
}
