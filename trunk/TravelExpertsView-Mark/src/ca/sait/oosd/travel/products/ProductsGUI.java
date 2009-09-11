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
import javax.swing.SpringLayout;

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;
import ca.sait.oosd.hibernate.Products;

/**
 * @author Omojola Ajayi
 *
 */
public class ProductsGUI extends TEJFrame {

	private static final long serialVersionUID = 1L;
	private LoggerHelper helper = new LoggerHelper(ProductsGUI.class.getName());
	Collection<Products> productCollection;
    private TEBusinessDelegate delegate;
	
	public ProductsGUI() {
		super();
		
		//this should be the way to call delegate. Since this is not implemented yet, we can use some dummy data as below
		delegate = new TEBusinessDelegateImpl();
		productCollection = delegate.getProductsCollection();
		
		this.initGUI();
        this.alignFrameOnScreen(this);
		
	}
	
	@Override
	protected void adjustSize(int width, int height) {

	}

	@Override
	protected void initGUI() {
        helper.log(LogLevel.INFO, "Start initlializing Products user interface...");

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String[] labels = {"Product ID", "Product Name"};
		JPanel dataEntryPane = new JPanel(new SpringLayout());
		for (String label : labels) {
			JLabel l = new JLabel(label, JLabel.TRAILING);
			dataEntryPane.add(l);
			JTextField textField = new JTextField(10);
			l.setLabelFor(textField);
			dataEntryPane.add(textField);
		}

        SpringUtilities.makeCompactGrid(dataEntryPane,
				labels.length, 2,
				6, 6,
				6, 6);

		//list to display the data
		JList list = new JList(productCollection.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
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
		
	}

}
