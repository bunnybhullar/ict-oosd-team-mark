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
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

/**
 *
 * @author duminda
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
    private String[] headerNames = {"Agent", "Count"};

    private CustomerDragList customerList;
    private ImageButton assignButton;

    private final String ADD = "ADD";


    public CustomerReassignGUI() {
        super();

        delegate = new TEBusinessDelegateImpl();
        customerCollection = delegate.getCustomersCollection();
        customerReassignDAOCollection = delegate.getAgentCustomerCollection();

        customerModel = new DefaultListModel();
        customeReassignVector = new Vector<CustomerReassignDAO>();

        assignButton = new ImageButton("resources/cup.gif", "Assign", 70, 120);
        assignButton.setActionCommand(ADD);
        assignButton.addActionListener(this);

        this.initGUI();
        super.alignFrameOnScreen(this);
        
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
        
        JTable customerReassignTable = new JTable(customerReassignModel);
        customerReassignTable.setFillsViewportHeight(true);    
        customerReassignTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        customerReassignTable.setDropMode(DropMode.USE_SELECTION);
        JScrollPane customerReassignTableScrollPane = new JScrollPane(customerReassignTable);
        
        customerReassignTable.setTransferHandler(new CustomerRelationshipReassignHandler(customerList, delegate,
        		CustomerReassignGUI.this, customerReassignModel));
        
        ListSelectionModel rowSelectionMode = customerReassignTable.getSelectionModel();
		
/*		JTreeTable treeTable = new JTreeTable(new CustomerReassignModel(customerReassignDAO));
		JScrollPane agentCustomerListScroller = new JScrollPane(treeTable);
		agentCustomerListScroller.setPreferredSize(new Dimension(250, 80));		*/

        JPanel centerPanel = new JPanel(new SpringLayout());
        centerPanel.add(customerListScroller);
        centerPanel.add(customerReassignTableScrollPane);

        SpringUtilities.makeCompactGrid(centerPanel,
				1, 2,
				6, 6,
				6, 6);

        this.getContentPane().add(new NavigationButtonPanel(TravelParts.PRODUCTSUPPLIER), BorderLayout.NORTH);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);

    }

    @Override
    protected void adjustSize(int width, int height) {
        
    }

    public void actionPerformed(ActionEvent e) {
        
    }

}
