package ca.sait.oosd.dao;

import java.util.Collection;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Customers;

public class CustomerReassignDAO extends TEObject{

	private Collection<Customers> customerCollection;
	private Agents agant;
	
	public Collection<Customers> getCustomerCollection() {
		return customerCollection;
	}
	
	public void setCustomerCollection(Collection<Customers> customerCollection) {
		this.customerCollection = customerCollection;
	}
	
	public void setCustomer(Customers customer) {
		this.customerCollection.add(customer);
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
	
	@Override
	public String toString() {
		return agant.getAgtfirstname() + ", " + agant.getAgtlastname();
	}
	
}
