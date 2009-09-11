package ca.sait.oosd.travel.products;

import ca.sait.oosd.business.TEObject;

/**
 * @author duminda
 * Encapsulate product data
 */
public class Products extends TEObject{

	private int productId;
	private String productName;
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return this.getProductName();
	}
		
}
