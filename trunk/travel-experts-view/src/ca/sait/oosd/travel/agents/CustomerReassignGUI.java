package ca.sait.oosd.travel.agents;

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.components.CustomerDragList;
import ca.sait.oosd.components.ImageButton;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.dao.CustomerReassignDAO;
import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Customers;
import ca.sait.oosd.listeners.CustomerRelationshipReassignHandler;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;
import ca.sait.oosd.models.CustomerReassignModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mark Southwell
 * @pupose The pupose of this GUI is to provide an interface reassign customers to new agents.
 * 
 */
public class CustomerReassignGUI extends TEJFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    private LoggerHelper helper = new LoggerHelper(CustomerReassignGUI.class.getName());

    private Collection<Customers> customerCollection;
    private Collection<CustomerReassignDAO> customerReassignDAOCollection;
    private TEBusinessDelegate delegate;

    private DefaultListModel customerModel;
    private CustomerReassignModel customerReassignModel;
    private Vector<CustomerReassignDAO> customeReassignVector;
    private JTable customerReassignTable;
    private String[] headerNames = {"Agent", "Count"};

    private CustomerDragList customerList;
    private ImageButton assignButton;
    
    private Agents selectedAgent;

    private final String ADD = "ADD";
    private final int WIDTH = 850;
	private final int HEIGHT = 500;	    


    public CustomerReassignGUI() {
        super("Reassign Customer");

        delegate = new TEBusinessDelegateImpl();
        customerCollection = delegate.getCustomersCollection();
        customerReassignDAOCollection = delegate.getAgentCustomerCollection();

        customerModel = new DefaultListModel();
        customeReassignVector = new Vector<CustomerReassignDAO>();

        assignButton = new ImageButton("resources/attach.png", "Assign", 70, 120);
        assignButton.setActionCommand(ADD);
        assignButton.addActionListener(this);

        this.initGUI();
        this.adjustSize(WIDTH, HEIGHT);
        super.alignFrameOnScreen(WIDTH, HEIGHT);
        
    }
    
    @Override
    protected void initGUI() {
        helper.log(LogLevel.INFO, "Start initlializing customer re-assign user interface...");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(Customers customers : customerCollection) {
            customerModel.addElement(customers);
        }
        
        customerList = new CustomerDragList(customerModel);
		JScrollPane customerListScroller = new JScrollPane(customerList);
		customerListScroller.setPreferredSize(new Dimension(250, 80));        
        
        for(CustomerReassignDAO customerReassignDAO : customerReassignDAOCollection) {
        	customeReassignVector.add(customerReassignDAO);
        }
        
        customerReassignModel = new CustomerReassignModel(headerNames, customeReassignVector);
        
        customerReassignTable = new JTable(customerReassignModel);
        customerReassignTable.setFillsViewportHeight(true);    
        customerReassignTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerReassignTable.setDropMode(DropMode.USE_SELECTION);
        JScrollPane customerReassignTableScrollPane = new JScrollPane(customerReassignTable);
        
        customerReassignTable.setTransferHandler(new CustomerRelationshipReassignHandler(customerList, delegate,
        		CustomerReassignGUI.this, customerReassignModel, customerModel, customeReassignVector));
        
        ListSelectionModel rowSelectionMode = customerReassignTable.getSelectionModel();
        rowSelectionMode.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();
				if(! listSelectionModel.isSelectionEmpty()) {
					int row = customerReassignTable.getSelectedRow();
					selectedAgent = ((CustomerReassignDAO)customerReassignModel.getSelectedRow(row)).getAgant();
				}
			}
        	
        });

        JPanel centerPanel = new JPanel(new SpringLayout());
        centerPanel.add(customerListScroller);
        centerPanel.add(customerReassignTableScrollPane);
        centerPanel.add(new JLabel(""));
        
        JPanel buttonPane = new JPanel();
        buttonPane.add(assignButton);
        centerPanel.add(buttonPane);

        SpringUtilities.makeCompactGrid(centerPanel,
				2, 2,
				6, 6,
				6, 6);

        this.getContentPane().add(new NavigationButtonPanel(TravelParts.NONE), BorderLayout.NORTH);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);

    }

    @Override
    protected void adjustSize(int width, int height) {
    	this.setSize(new Dimension(width, height));
    	
    }

    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals(ADD)) {
    		if(customerList.getSelectedIndex() != -1 && customerReassignTable.getSelectedRow() != -1) {
    			
    	        int selection = JOptionPane.showConfirmDialog(CustomerReassignGUI.this,
    	                "Do you want to assign " +  ((Customers)customerList.getSelectedOption()).getCustfirstname()
    	                + " to " + selectedAgent.getAgtfirstname() +" ?",
    	                "Customer Reassign", JOptionPane.YES_NO_OPTION);
    	        
    	        if(selection == JOptionPane.YES_OPTION) {
    	        	delegate.reassignCustomerToAgent((Customers)customerList.getSelectedOption(), selectedAgent);
    	        	updateDataTableModel();
    	        	
    	        }
    			
    		} else {
    			JOptionPane.showMessageDialog(CustomerReassignGUI.this, "You need to select a valid items to make the assign", 
						"Invalid Selection", JOptionPane.WARNING_MESSAGE);
    			
    		}
    	}
    }
    
    private void updateDataTableModel() {
    	customerModel.remove(customerList.getSelectedIndex());
    	
    	Iterator<CustomerReassignDAO> itr = customeReassignVector.iterator();
    	while(itr.hasNext()) {
    		CustomerReassignDAO reassignDAO = itr.next();
    		if(reassignDAO.getAgant().getAgentid() == selectedAgent.getAgentid()) {
    			reassignDAO.setCustomer((Customers)customerList.getSelectedOption());
    			customerReassignModel.addRelationship(reassignDAO);
    			
    			return;
    		}    		
    	}
    	
    }

}
