package ca.sait.oosd.travel.supplier;

import ca.sait.oosd.business.TEObject;

/**
 * @author duminda
 * Encapsulate supplier data
 */
public class Suppliers extends TEObject{

	private int supplierId;
	private String supplierName;
	
	public int getSupplierId() {
		return supplierId;
	}
	
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Override
	public String toString() {
		return "";
	}
	
	
}
