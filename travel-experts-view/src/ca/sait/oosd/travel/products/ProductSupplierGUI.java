package ca.sait.oosd.travel.products;

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.components.DragList;
import ca.sait.oosd.components.DropList;
import ca.sait.oosd.components.ImageButton;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.hibernate.Products;
import ca.sait.oosd.hibernate.Suppliers;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;

import ca.sait.oosd.models.ProductSupplierDataTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

/**
 *
 * @author duminda
 * @pupose This is the user interface that used to create relationships between products and suppliers. The drag and drop
 *          functionality is available for the ease of relationship creation. 
 */
public class ProductSupplierGUI extends TEJFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
    
    private LoggerHelper helper = new LoggerHelper(ProductSupplierGUI.class.getName());
    private Collection<ProductsSuppliers> poductsSuppliersCollection;
    private Collection<Products> poductsCollection;
    private Collection<Suppliers> suppliersCollection;
    private TEBusinessDelegate delegate;

    private DefaultListModel supplierModel;
    private DefaultListModel productModel;
    private ProductSupplierDataTableModel productSupplierDataTableModel;
    private Vector<ProductsSuppliers> productSupplierVector;
    private String[] headerNames = {"ID", "Product", "Supplier"};

    private DragList productList;
    private DropList supplierList;
    private ImageButton relationshipButton;

    private final String ADD = "ADD";
    private final String DELETE = "DELETE";


    //default constructor
    public ProductSupplierGUI() {
        super();

        delegate = new TEBusinessDelegateImpl();
        poductsSuppliersCollection = delegate.getProductSuppliersCollection();
        poductsCollection = delegate.getProductsCollection();
        suppliersCollection = delegate.getSuppliersCollection();

        supplierModel = new DefaultListModel();
        productModel = new DefaultListModel();
        productSupplierVector = new Vector<ProductsSuppliers>();

        relationshipButton = new ImageButton("resources/cup.gif", "Relationship", 70, 120);
        relationshipButton.setActionCommand(ADD);
        relationshipButton.addActionListener(this);

        this.initGUI();
        super.alignFrameOnScreen(this);

    }
    
    @Override
    protected void initGUI() {
        helper.log(LogLevel.INFO, "Start initlializing product supplier user interface...");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(ProductsSuppliers productsSuppliers : poductsSuppliersCollection) {
            productSupplierVector.add(productsSuppliers);
        }

        productSupplierDataTableModel = new ProductSupplierDataTableModel(headerNames, productSupplierVector);
        
        for(Products products : poductsCollection) {
            productModel.addElement(products);
        }

        for(Suppliers suppliers : suppliersCollection) {
            supplierModel.addElement(suppliers);
        }

        productList = new DragList(productModel);
        supplierList = new DropList(ProductSupplierGUI.this, supplierModel, productList, delegate, productSupplierDataTableModel);

        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productList.setLayoutOrientation(JList.VERTICAL);
		productList.setVisibleRowCount(-1);

		supplierList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplierList.setLayoutOrientation(JList.VERTICAL);
		supplierList.setVisibleRowCount(-1);

		JScrollPane productListScroller = new JScrollPane(productList);
		productListScroller.setPreferredSize(new Dimension(250, 80));

		JScrollPane supplierListScroller = new JScrollPane(supplierList);
		supplierListScroller.setPreferredSize(new Dimension(250, 80));

        JPanel listPanel = new JPanel(new SpringLayout());
        listPanel.add(productListScroller);
        listPanel.add(supplierListScroller);
        listPanel.add(new JLabel(""));
        listPanel.add(relationshipButton);

        SpringUtilities.makeCompactGrid(listPanel,
				2, 2,
				6, 6,
				6, 6);

        //generate the product supplier data table
        JTable productSupplierTable = new JTable(productSupplierDataTableModel);
        productSupplierTable.setFillsViewportHeight(true);

        JScrollPane productSupplierScrollPane = new JScrollPane(productSupplierTable);
        
        this.getContentPane().add(new NavigationButtonPanel(TravelParts.PRODUCTSUPPLIER), BorderLayout.NORTH);
        this.getContentPane().add(listPanel, BorderLayout.CENTER);
        this.getContentPane().add(productSupplierScrollPane, BorderLayout.SOUTH);


    }

    @Override
    protected void adjustSize(int width, int height) {
        
    }

    //perform the button action here
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(ADD)) {
            
            if(productList.getSelectedIndex() != -1 && supplierList.getSelectedIndex() != -1) {
                try{
                    ProductsSuppliers productsSuppliers = delegate.makeProductSupplierRelationship((TEObject)productList.getSelectedValue(), (TEObject)supplierList.getSelectedValue());
                    productSupplierDataTableModel.addRelationship(productsSuppliers);

                } catch(TEBusinessException exp) {
                    //TODO : Log and display the error

                }

            } else {
                JOptionPane.showMessageDialog(this,
                        "You need to select valid product and supplier to make the relationship",
                        "Make new Relationship",
                        JOptionPane.INFORMATION_MESSAGE);

            } 

        } else if (e.getActionCommand().equals(DELETE)) {

            
        }

    }

}
