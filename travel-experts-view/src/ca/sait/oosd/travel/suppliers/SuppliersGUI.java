package ca.sait.oosd.travel.suppliers;

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.components.Validator;
import ca.sait.oosd.components.ValidatorException;
import ca.sait.oosd.hibernate.Suppliers;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * @author Omojola Ajayi
 *
 */
public class SuppliersGUI extends TEJFrame {

	private static final long serialVersionUID = 1L;
    private LoggerHelper helper = new LoggerHelper(SuppliersGUI.class.getName());
    private Collection<Suppliers> supplierCollection;
    private TEBusinessDelegate delegate;
    private Suppliers suppliers;
    private JList list;
    private DefaultListModel model;
	private JTextField supplierIdTextField = new JTextField(10);
	private JTextField supplierNameTextField = new JTextField(20);

    
    private final int WIDTH = 850;
	private final int HEIGHT = 300;       

    public SuppliersGUI() {
        super("Suppliers");

        delegate = new TEBusinessDelegateImpl();
        supplierCollection = delegate.getSuppliersCollection();
        model = new DefaultListModel();
        
        this.initGUI();
        this.adjustSize(WIDTH, HEIGHT);
        super.alignFrameOnScreen(WIDTH, HEIGHT);
        
    }

	@Override
	protected void initGUI() {
        helper.log(LogLevel.INFO, "Start initlializing Supplier user interface...");

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        //list to display the data
        for(Suppliers suppliers : supplierCollection) {
            model.addElement(suppliers);
        }
        
		JPanel dataEntryPane = new JPanel(new SpringLayout());
		dataEntryPane.add(new JLabel("Supplier ID", JLabel.TRAILING));
		supplierIdTextField.setEnabled(false);
		dataEntryPane.add(supplierIdTextField);
		dataEntryPane.add(new JLabel("Supplier Name", JLabel.TRAILING));
		dataEntryPane.add(supplierNameTextField);

        SpringUtilities.makeCompactGrid(dataEntryPane,
				2, 2,
				6, 6,
				6, 6);		

		GridLayout grid = new GridLayout(2, 1);
		JPanel centerPane = new JPanel();
		centerPane.setLayout(grid);
		centerPane.add(dataEntryPane);
		
		//list to display the data
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);

		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));

		//button panel
		JPanel southPane = new JPanel();
		JButton addButton = new JButton("Add");
		JButton updateButton = new JButton("Update");
		JButton clearButton = new JButton("Clear");
		JButton deleteButton = new JButton("Delete");
		southPane.add(addButton);
		southPane.add(updateButton);
		southPane.add(clearButton);
		southPane.add(deleteButton);

		this.getContentPane().add(new NavigationButtonPanel(TravelParts.SUPPLIER), BorderLayout.NORTH);
        this.getContentPane().add(centerPane, BorderLayout.CENTER);
        this.getContentPane().add(listScroller, BorderLayout.EAST);
        this.getContentPane().add(southPane, BorderLayout.SOUTH);
        
        //add the listener to the list
		ListSelectionModel selectionModel = list.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() != -1) {
					suppliers = (Suppliers)list.getSelectedValue();
					supplierIdTextField.setText(Long.toString(suppliers.getSupplierid()));
					supplierNameTextField.setText(suppliers.getSupname());
					supplierIdTextField.setEnabled(false);	
				}
			}
			
		});

		//Clear the content of the data entry
		clearButton.addActionListener(new ActionListener()  {

			@Override
			public void actionPerformed(ActionEvent e) {
					clearComponents();
			}
		});

		
		//Add new records to the database
		addButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					validateForm();
					
					suppliers = new Suppliers();
					suppliers.setSupname(supplierNameTextField.getText().trim());
					
					suppliers = (Suppliers)delegate.save(suppliers);
					
					model.addElement(suppliers);
					clearComponents();
					
					JOptionPane.showMessageDialog(SuppliersGUI.this, "New Supplier added successfully", 
							"Successful", JOptionPane.INFORMATION_MESSAGE);
					
				}catch (TEBusinessException ex){
					helper.log(LogLevel.ERROR, "Exception occured while saving Data.class.." + ex.getMessage());
					
				} catch (ValidatorException ex) {
                	helper.log(LogLevel.ERROR, "Validation exception occured..." + ex.getMessage());
					JOptionPane.showMessageDialog(SuppliersGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		//delete records from the database
		deleteButton.addActionListener(new ActionListener()  {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() != -1) {
	            	int option = JOptionPane.showConfirmDialog(SuppliersGUI.this,
	            			"Do you want to delete this ?", "Delete confirmation",
	            			JOptionPane.YES_NO_OPTION);
	            	
	            	if(option == JOptionPane.YES_OPTION) {
	    				try{
	    					int position = model.indexOf(suppliers, 0);
	    					delegate.delete(suppliers);

	    					clearComponents();
	    					removeFromListModel(position);
	    					
	    					JOptionPane.showMessageDialog(SuppliersGUI.this, "The selected supplier removed successfully", 
	    							"Successful", JOptionPane.INFORMATION_MESSAGE);    					

	    				}catch (TEBusinessException ex){
	    					helper.log(LogLevel.ERROR, "Exception occured while saving Data.class.." + ex.getMessage());

	    				}	
	            	}	
	            	
				} else {
					JOptionPane.showMessageDialog(SuppliersGUI.this, "You need to select a valid item from the list to delete", 
							"Successful", JOptionPane.INFORMATION_MESSAGE);  
					
				}
			}
		});

		//update records from the user interface
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(list.getSelectedIndex() != -1) {
					try{
						validateForm();
						
						suppliers = new Suppliers();
						suppliers.setSupplierid(Long.parseLong(supplierIdTextField.getText()));
						suppliers.setSupname(supplierNameTextField.getText().trim());
						
						suppliers = (Suppliers)delegate.update(suppliers);
						updateListModel(suppliers);
						
						JOptionPane.showMessageDialog(SuppliersGUI.this, "The selected supplier updated successfully", 
								"Successful", JOptionPane.INFORMATION_MESSAGE);  
						
					} catch (TEBusinessException e1) {
						helper.log(LogLevel.ERROR, "Exception occured while saving Data.class.." + e1.getMessage());
						
					} catch (ValidatorException ex) {
	                	helper.log(LogLevel.ERROR, "Validation exception occured..." + ex.getMessage());
						JOptionPane.showMessageDialog(SuppliersGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(SuppliersGUI.this, "You need to select a valid item from the list to update", 
							"Successful", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		
	}
	
	protected void clearComponents() {
		supplierIdTextField.setText("");
		supplierNameTextField.setText("");
		
		list.clearSelection();
	}
	
	private void validateForm() throws ValidatorException{
		Validator.isEmptyString("Supplier Name", supplierNameTextField.getText());
		
	}
	
    private void removeFromListModel(int position) {
        model.removeElementAt(position);
        
    }
    
	//update the list model when user update a record from the user interface
	protected void updateListModel(Suppliers suppliers) {
		for(Suppliers sup : supplierCollection){
			if(sup.getSupplierid() == suppliers.getSupplierid()){
				int position = model.indexOf(sup, 0);
				model.set(position, suppliers);
			}
		}
	}    
	
	@Override
	protected void adjustSize(int width, int height) {
		this.setSize(new Dimension(width, height));
		
	}	

}
