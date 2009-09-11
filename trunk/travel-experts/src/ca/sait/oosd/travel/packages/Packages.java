package ca.sait.oosd.travel.packages;

import java.util.Date;

import ca.sait.oosd.business.TEObject;

/**
 * All properties encapsulated and could be access only via the getters and setters
 * @author Duminda
 *
 */
public class Packages extends TEObject{
    private int packageId;
    private String packageName;
    private Date packageStartDate;
    private Date packageEndDate;
    private String packageDescription;
    private double packageBasePrice;
    private double packageAgencycommission;
    
	public int getPackageId() {
		return packageId;
	}
	
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public Date getPackageStartDate() {
		return packageStartDate;
	}
	
	public void setPackageStartDate(Date packageStartDate) {
		this.packageStartDate = packageStartDate;
	}
	
	public Date getPackageEndDate() {
		return packageEndDate;
	}
	
	public void setPackageEndDate(Date packageEndDate) {
		this.packageEndDate = packageEndDate;
	}
	
	public String getPackageDescription() {
		return packageDescription;
	}
	
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	
	public double getPackageBasePrice() {
		return packageBasePrice;
	}
	
	public void setPackageBasePrice(double packageBasePrice) {
		this.packageBasePrice = packageBasePrice;
	}
	
	public double getPackageAgencycommission() {
		return packageAgencycommission;
	}
	
	public void setPackageAgencycommission(double packageAgencycommission) {
		this.packageAgencycommission = packageAgencycommission;
	}
    
	@Override
    public String toString() {
    	return this.packageName;
    }
}
