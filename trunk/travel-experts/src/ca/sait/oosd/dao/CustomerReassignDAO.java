package ca.sait.oosd.dao;

import java.util.Collection;

import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Customers;

public class CustomerReassignDAO {

	private Collection<Customers> customerCollection;
	private Agents agant;
	
	public Collection<Customers> getCustomerCollection() {
		return customerCollection;
	}
	
	public void setCustomerCollection(Collection<Customers> customerCollection) {
		this.customerCollection = customerCollection;
	}
	
	public Agents getAgant() {
		return agant;
	}
	
	public void setAgant(Agents agant) {
		this.agant = agant;
	}
	
	public Object[] getChildren() {
		return customerCollection.toArray();
	}
	
	public String toString() {
		return agant.getAgtfirstname() + ", " + agant.getAgtlastname();
	}
	
}
