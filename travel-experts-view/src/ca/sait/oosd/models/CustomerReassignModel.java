package ca.sait.oosd.models;

import java.util.Vector;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.dao.CustomerReassignDAO;

/**
*
* @author duminda
* @pupose 
* 
*/
public class CustomerReassignModel extends TERelationshipDataTableModel{

	private static final long serialVersionUID = 1L;

	private Vector<CustomerReassignDAO> customeReassignVector;
	
	public CustomerReassignModel(String[] headerNames, Vector<CustomerReassignDAO> customeReassignVector) {
		super(headerNames);
		this.customeReassignVector = customeReassignVector;
		
	}

	@Override
	public int getRowCount() {
		return customeReassignVector.size();
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CustomerReassignDAO customerReassignDAO = customeReassignVector.elementAt(rowIndex);
		
		switch(columnIndex) {
			case 0:
				return customerReassignDAO.getAgant().getAgtfirstname() + 
				", " + customerReassignDAO.getAgant().getAgtlastname();
			case 1:
				return customerReassignDAO.getCustomerCollection().size();
			default:
				return null;
		}
		
	}
	
	public CustomerReassignDAO getSelectedRow(int rowIndex) {
		if(rowIndex != -1) {
			CustomerReassignDAO customerReassignDAO = customeReassignVector.elementAt(rowIndex);
			return customerReassignDAO;
			
		} else {
            return null;

        }
	}
	
	@Override
	public void addRelationship(TEObject teObject) {
		customeReassignVector.add((CustomerReassignDAO)teObject);
		
		fireTableDataChanged();
		
	}	

	@Override
	public void removeRelationship(TEObject teObject) {
		customeReassignVector.remove((CustomerReassignDAO)teObject);
		
		fireTableDataChanged();
		
	}

}
