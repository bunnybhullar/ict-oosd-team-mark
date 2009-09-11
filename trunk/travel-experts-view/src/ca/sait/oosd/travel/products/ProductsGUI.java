package ca.sait.oosd.travel.products;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.TEJFrame;
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
    private DefaultListModel model;
    
	private JTextField productIdTextField = new JTextField(20);
	private JTextField productNameTextField = new JTextField(20);

    private final int WIDTH = 600;
	private final int HEIGHT = 500;
	
	public ProductsGUI() {
		super();
		
		//this should be the way to call delegate. Since this is not implemented yet, we can use some dummy data as below
		delegate = new TEBusinessDelegateImpl();
		productCollection = delegate.getProductsCollection();

        model = new DefaultListModel();
        
		this.initGUI();
        this.adjustSize(WIDTH, HEIGHT);
        this.alignFrameOnScreen(this);
		
	}
	
	@Override
	protected void adjustSize(int width, int height) {
        this.setSize(new Dimension(width, height));
	}

	@Override
	protected void initGUI() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel dataEntryPane = new JPanel(new GridLayout(2, 2));
		dataEntryPane.add(new JLabel("Product ID", JLabel.TRAILING));
		dataEntryPane.add(productIdTextField);
		dataEntryPane.add(new JLabel("Product Name", JLabel.TRAILING));
		dataEntryPane.add(productNameTextField);


		//list to display the data
		final JList list = new JList(productCollection.toArray());
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

		JPanel northPane = new JPanel();
		JButton productButton = new JButton("Product");
		JButton supplierButton = new JButton("Supplier");
		JButton packageButton = new JButton("Package");
		JButton psupplierButton = new JButton("Product Supplier");
		JButton ppsupplierButton = new JButton("Package Product Supplier");
		JButton mmenuButton = new JButton("Main Menu");
		northPane.add(productButton);
		northPane.add(supplierButton);
		northPane.add(packageButton);
		northPane.add(psupplierButton);
		northPane.add(ppsupplierButton);
		northPane.add(mmenuButton);



		GridLayout grid = new GridLayout(2, 1);
		JPanel centerPane = new JPanel();
		centerPane.setLayout(grid);
		//centerPane.add(northPane, JPanel.TOP_ALIGNMENT);
		centerPane.add(dataEntryPane);
		centerPane.add(southPane, JPanel.BOTTOM_ALIGNMENT);

		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.add(new NavigationButtonPanel(TravelParts.PRODUCT), BorderLayout.NORTH);
		mainPane.add(centerPane, BorderLayout.CENTER);
		mainPane.add(listScroller, BorderLayout.EAST);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);

		//add the listener to the list
		ListSelectionModel selectionModel = list.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//TODO Auto-generated method stub
				 products = (Products)list.getSelectedValue();
				productIdTextField.setText(Long.toString(products.getProductid()));
				productNameTextField.setText(products.getProdname());
			}

		});

		//Clear the content of the data entry
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					clearComponents();
			}
		});

		//Add new records to the database
		addButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					products = new Products();
					products.setProdname(productNameTextField.getText().trim());

                    products = (Products)delegate.save(products);

                    model.addElement(products);
					clearComponents();
                    
				}catch (TEBusinessException ex){
					helper.log(LogLevel.ERROR, "Exception occured while saving Data.class.." + ex.getMessage());
				}

			}

		});
		//delete records from the database
		deleteButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(products != null){
					try{
						int position = model.indexOf(products, 0);

                        delegate.delete(products);

                        clearComponents();
						removeFromListModel(position);
                        
					}catch (TEBusinessException ex){
						helper.log(LogLevel.ERROR, "Exception occured while saving Data.class.." + ex.getMessage());
					}
				}
			}

			private void removeFromListModel(int position) {
				// TODO Auto-generated method stub

			}

		});
		//update records from the user interface
		updateButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
				products = new Products();
				products.setProductid(Long.parseLong(productIdTextField.getText()));
				products.setProdname(productNameTextField.getText().trim());

                products = (Products)delegate.update(products);
                
                updateListModel(products);
                
				} catch (TEBusinessException e1) {
					// TODO Auto-generated catch block
					helper.log(LogLevel.ERROR, "Exception occured while saving Data.class.." + e1.getMessage());
				}
			}

		});
		
	}

	//update the list model when user update a record from the user interface
	protected void updateListModel(Products products2) {
		// TODO Auto-generated method stub
		for(Products prod : productCollection){
			if(prod.getProductid() == products.getProductid()){
				int position = model.indexOf(prod, 0);
				model.set(position, products);
			}
		}
	}

	protected void removeFromListModel(int position) {
		// TODO Auto-generated method stub
		model.set(position, products);
	}

	protected void clearComponents() {
		// TODO Auto-generated method stub
		productIdTextField.setText("");
		productNameTextField.setText("");

	}

}
