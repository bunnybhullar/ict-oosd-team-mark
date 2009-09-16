package ca.sait.oosd.travel.products;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;

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

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.components.Validator;
import ca.sait.oosd.components.ValidatorException;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;
import ca.sait.oosd.hibernate.Products;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Omojola Ajayi
 *
 */
public class ProductsGUI extends TEJFrame {

	private static final long serialVersionUID = 1L;
	private LoggerHelper helper = new LoggerHelper(ProductsGUI.class.getName());

	Collection<Products> productCollection;
	private TEBusinessDelegate delegate;
	private Products products;
	private JList list;
	private DefaultListModel model;
	private JTextField productIdTextField = new JTextField(20);
	private JTextField productNameTextField = new JTextField(20);


    private final int WIDTH = 850;
	private final int HEIGHT = 300;
	
	public ProductsGUI() {
		super("Products");

		delegate = new TEBusinessDelegateImpl();
		productCollection = delegate.getProductsCollection();

		model = new DefaultListModel();
		
		this.initGUI();
		this.adjustSize(WIDTH, HEIGHT);
		super.alignFrameOnScreen(WIDTH, HEIGHT);

	}

	@Override
	protected void initGUI() {
		helper.log(LogLevel.INFO,
		"Start initlializing Products user interface...");

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel dataEntryPane = new JPanel(new SpringLayout());
		dataEntryPane.add(new JLabel("Product ID", JLabel.TRAILING));
		productIdTextField.setEnabled(false);
		dataEntryPane.add(productIdTextField);
		dataEntryPane.add(new JLabel("Product Name", JLabel.TRAILING));
		dataEntryPane.add(productNameTextField);
		
        SpringUtilities.makeCompactGrid(dataEntryPane,
				2, 2,
				6, 6,
				6, 6);	
        
        for(Products product : productCollection) {
        	model.addElement(product);
        }
        
		GridLayout grid = new GridLayout(2, 1);
		JPanel centerPane = new JPanel();
		centerPane.setLayout(grid);

		centerPane.add(dataEntryPane);        

		// list to display the data
		list = new JList(productCollection.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);

		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));

		// button panel
		JPanel southPane = new JPanel();
		JButton addButton = new JButton("Add");
		JButton updateButton = new JButton("Update");
		JButton clearButton = new JButton("Clear");
		JButton deleteButton = new JButton("Delete");
		southPane.add(addButton);
		southPane.add(updateButton);
		southPane.add(clearButton);
		southPane.add(deleteButton);

		this.getContentPane().add(new NavigationButtonPanel(TravelParts.PRODUCT), BorderLayout.NORTH);
		this.getContentPane().add(centerPane, BorderLayout.CENTER);
		this.getContentPane().add(listScroller, BorderLayout.EAST);
		this.getContentPane().add(southPane, BorderLayout.SOUTH);

		// add the listener to the list
		ListSelectionModel selectionModel = list.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() != -1) {
					products = (Products) list.getSelectedValue();
					productIdTextField.setText(Long.toString(products
							.getProductid()));
					productNameTextField.setText(products.getProdname());
					productIdTextField.setEnabled(false);	
				}
			}

		});

		// Clear the content of the data entry
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearComponents();
			}
		});
		// Add new records to the database
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validateForm();

					products = new Products();
					products.setProdname(productNameTextField.getText().trim());

					products = (Products) delegate.save(products);
					model.addElement(products);
					clearComponents();
					
					JOptionPane.showMessageDialog(ProductsGUI.this, "New Product added successfully", 
							"Successful", JOptionPane.INFORMATION_MESSAGE);

				} catch (TEBusinessException ex) {				
					helper.log(LogLevel.ERROR,
							"Exception occured while saving Data..."
							+ ex.getMessage());

				} catch (ValidatorException ex) {
					helper.log(LogLevel.ERROR, "Validation exception occured..." + ex.getMessage());
					JOptionPane.showMessageDialog(ProductsGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}		
			}

		});
		
		// delete records from the database
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() != -1) {
	            	int option = JOptionPane.showConfirmDialog(ProductsGUI.this,
	            			"Do you want to delete this ?", "Delete confirmation",
	            			JOptionPane.YES_NO_OPTION);
	            	
	            	if(option == JOptionPane.YES_OPTION) {
	    				if (products != null) {
	    					try {
	    						int position = model.indexOf(products, 0);
	    						delegate.delete(products);

	    						clearComponents();
	    						removeFromListModel(position);
	    						
	        					JOptionPane.showMessageDialog(ProductsGUI.this, "The selected product removed successfully", 
	        							"Successful", JOptionPane.INFORMATION_MESSAGE);  

	    					} catch (TEBusinessException ex) {
	    						helper.log(LogLevel.ERROR,
	    								"Exception occured while saving Data.class.."
	    								+ ex.getMessage());
	    					}
	    				}	
	            	}
	            	
				} else {
					JOptionPane.showMessageDialog(ProductsGUI.this, "You need to select a valid item from the list to delete", 
							"Successful", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}


		});
		
		// update records from the user interface
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() != -1) {
					try {
						validateForm();

						products = new Products();
						products.setProductid(Long.parseLong(productIdTextField.getText()));
						products.setProdname(productNameTextField.getText().trim());
						// try {
						products = (Products) delegate.update(products);
						updateListModel(products);
						
						JOptionPane.showMessageDialog(ProductsGUI.this, "The selected product updated successfully", 
								"Successful", JOptionPane.INFORMATION_MESSAGE);  

					} catch (TEBusinessException e1) {
						// TODO Auto-generated catch block
						helper.log(LogLevel.ERROR,
								"Exception occured while saving Data.class.."
								+ e1.getMessage());
					} catch (ValidatorException ex) {
						helper.log(LogLevel.ERROR, "Validation exception occured..." + ex.getMessage());
						JOptionPane.showMessageDialog(ProductsGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(ProductsGUI.this, "You need to select a valid item from the list to update", 
							"Successful", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});

	}
	
	// update the list model when user update a record from the user interface
	protected void updateListModel(Products products) {
		for (Products prod : productCollection) {
			if (prod.getProductid() == products.getProductid()) {
				int position = model.indexOf(prod, 0);

				model.set(position, products);
			}
		}
	}

	protected void removeFromListModel(int position) {
		model.set(position, products);
		
	}

	protected void clearComponents() {
		productIdTextField.setText("");
		productNameTextField.setText("");
		
		list.clearSelection();

	}
	
	private void validateForm() throws ValidatorException{
		Validator.isEmptyString("Product Name", productNameTextField.getText());
		
	}
	
	@Override
	protected void adjustSize(int width, int height) {
        this.setSize(new Dimension(width, height));
	}

}
