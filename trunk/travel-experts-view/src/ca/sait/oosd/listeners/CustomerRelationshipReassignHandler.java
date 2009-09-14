package ca.sait.oosd.listeners;

import java.awt.datatransfer.DataFlavor;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;

import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessException;
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
    
    public CustomerRelationshipReassignHandler() {
    	
    }
    
    public CustomerRelationshipReassignHandler(CustomerDragList customerList, TEBusinessDelegate delegate, TEJFrame parent,
    		CustomerReassignModel customerReassignModel) {
    	this.customerList = customerList;
    	this.delegate = delegate;
    	this.parent = parent;
    	this.customerReassignModel = customerReassignModel;
    	
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
        JTable.DropLocation dropLocation = (JTable.DropLocation)support.getDropLocation();
        int row = dropLocation.getRow();
        CustomerReassignDAO customerReassignDAO = customerReassignModel.getSelectedRow(row);
        
        int selection = JOptionPane.showConfirmDialog(parent,
                "Do you want to assign " +  ((Customers)customerList.getSelectedOption()).getCustfirstname()
                + " to " + customerReassignDAO.getAgant().getAgtfirstname() +" ?",
                "Customer Reassign", JOptionPane.YES_NO_OPTION);
        
        if(selection == JOptionPane.YES_NO_OPTION) {        	
            makeRelationship(customerReassignDAO);
            return true;   
            
        } else {
        	return false;
        	
        }
    }
    
    private void makeRelationship(CustomerReassignDAO customerReassignDAO) {
    	delegate.reassignCustomerToAgent((Customers)customerList.getSelectedOption(), customerReassignDAO.getAgant());
    }
    
}
