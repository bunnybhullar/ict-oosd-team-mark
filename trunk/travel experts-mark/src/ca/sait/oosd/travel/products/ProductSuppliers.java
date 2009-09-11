package ca.sait.oosd.travel.products;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.travel.supplier.Suppliers;

/**
 * @author duminda
 * Encapsulate product supplier relationships
 *
 */
public class ProductSuppliers extends TEObject{

	private int productSupplierId;
	private Products product;
	private Suppliers supplier;
	
	public int getProductSupplierId() {
		return productSupplierId;
	}
	
	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}
	
	public Products getProduct() {
		return product;
	}
	
	public void setProduct(Products product) {
		this.product = product;
	}
	
	public Suppliers getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Suppliers supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "";
	}
	
}
