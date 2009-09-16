package ca.sait.oosd.listeners;

import java.awt.datatransfer.DataFlavor;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.components.CustomerDragList;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.dao.CustomerReassignDAO;
import ca.sait.oosd.hibernate.Customers;
import ca.sait.oosd.models.CustomerReassignModel;

public class CustomerRelationshipReassignHandler extends TransferHandler{

	private static final long serialVersionUID = 1L;

	private CustomerDragList customerList;
    private TEBusinessDelegate delegate;
    private TEJFrame parent;
    private CustomerReassignModel customerReassignModel;
    private DefaultListModel customerModel;
    private Vector<CustomerReassignDAO> customeReassignVector;
    
    public CustomerRelationshipReassignHandler() {
    	
    }
    
    public CustomerRelationshipReassignHandler(CustomerDragList customerList, TEBusinessDelegate delegate, TEJFrame parent,
    		CustomerReassignModel customerReassignModel, DefaultListModel customerModel, Vector<CustomerReassignDAO> customeReassignVector) {
    	this.customerList = customerList;
    	this.delegate = delegate;
    	this.parent = parent;
    	this.customerReassignModel = customerReassignModel;
    	this.customerModel = customerModel;
    	this.customeReassignVector = customeReassignVector;
    	
    }
    
    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }

        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }

        return true;
    }
    
    @Override
    public boolean importData(TransferSupport support) {
        CustomerReassignDAO customerReassignDAO = getSelectedAgent(support);
        
        int selection = JOptionPane.showConfirmDialog(parent,
                "Do you want to assign " +  ((Customers)customerList.getSelectedOption()).getCustfirstname()
                + " to " + customerReassignDAO.getAgant().getAgtfirstname() +" ?",
                "Customer Reassign", JOptionPane.YES_NO_OPTION);
        
        if(selection == JOptionPane.YES_NO_OPTION) {        	
            makeRelationship(customerReassignDAO, support);
            return true;   
            
        } else {
        	return false;
        	
        }
    }
    
    private CustomerReassignDAO getSelectedAgent(TransferSupport support) {
        JTable.DropLocation dropLocation = (JTable.DropLocation)support.getDropLocation();
        int row = dropLocation.getRow();
        
        return customerReassignModel.getSelectedRow(row);
    }
    
    private void makeRelationship(CustomerReassignDAO customerReassignDAO, TransferSupport support) {
    	delegate.reassignCustomerToAgent((Customers)customerList.getSelectedOption(), customerReassignDAO.getAgant());
    	
    	updateDataTableModel(support);
    	
    }
    
    private void updateDataTableModel(TransferSupport support) {
    	customerModel.remove(customerList.getSelectedIndex());
    	CustomerReassignDAO customerReassignDAO = getSelectedAgent(support);
    	
    	Iterator<CustomerReassignDAO> itr = customeReassignVector.iterator();
    	while(itr.hasNext()) {
    		CustomerReassignDAO reassignDAO = itr.next();
    		
    		if(reassignDAO.getAgant().getAgentid() == customerReassignDAO.getAgant().getAgentid()) {
    			reassignDAO.setCustomer((Customers)customerList.getSelectedOption());
    			customerReassignModel.addRelationship(reassignDAO);
    			
    			return;
    		}
    		
    	}
    }
    
}
