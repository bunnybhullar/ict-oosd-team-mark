package ca.sait.oosd.travel.packages;

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.components.DragList;
import ca.sait.oosd.components.ImageButton;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.PackageDragList;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.hibernate.Packages;
import ca.sait.oosd.hibernate.PackagesProductsSuppliers;
import ca.sait.oosd.listeners.TableTransferHandler;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;
import ca.sait.oosd.models.PackagesProductsSuppliersDataTableModel;
import ca.sait.oosd.models.ProductSupplierDataTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
 * @author duminda
 * @purpose This is the user interface for the packages products supplier relationships.
 *  Refer https://www.hibernate.org/117.html#A36 for issues with generating key-may-to-one type hibernate relations.
 * 
 */
public class PackagesProductsSuppliersGUI extends TEJFrame implements ActionListener{

    private LoggerHelper helper = new LoggerHelper(PackagesProductsSuppliersGUI.class.getName());
    private TEBusinessDelegate delegate;
    private Collection<ProductsSuppliers> poductsSuppliersCollection;
    private Collection<Packages> packagesCollection;
    private Collection<PackagesProductsSuppliers> packagesProductsSuppliersCollection;

    private ProductSupplierDataTableModel productSupplierDataTableModel;
    private PackagesProductsSuppliersDataTableModel packagesProductsSuppliersDataTableModel;
    private DefaultListModel packagesModel;
    private Vector<ProductsSuppliers> productSupplierVector;
    private Vector<PackagesProductsSuppliers> packagesProductsSuppliersVector;

    private JTable productSupplierTable;
    private PackageDragList packagesList;
    private ImageButton relationshipButton;

    private ProductsSuppliers selectedProductsSupplier;

    private final String ADD = "ADD";
    private final String DELETE = "DELETE";
    

    private String[] productSupplierHeaders = {"ID", "Product", "Supplier"};
    private String[] packageProductSupplierHeaders = {"Package", "Product Name", "Supplier Name", "Package Start", "Package End", "Package Description"};
    


    public PackagesProductsSuppliersGUI() {
        super();

        delegate = new TEBusinessDelegateImpl();

        packagesCollection = delegate.getPackageCollection();
        poductsSuppliersCollection = delegate.getProductSuppliersCollection();
        packagesProductsSuppliersCollection = delegate.getPackageProductSupplierCollection();

        packagesModel = new DefaultListModel();
        productSupplierVector = new Vector<ProductsSuppliers>();
        packagesProductsSuppliersVector = new Vector<PackagesProductsSuppliers>();

        relationshipButton = new ImageButton("resources/cup.gif", "Relationship", 70, 120);
        relationshipButton.setActionCommand(ADD);
        relationshipButton.addActionListener(this);

        this.initGUI();
        this.alignFrameOnScreen(this);
        
    }
    
    @Override
    protected void initGUI() {
        helper.log(LogLevel.INFO, "Start initlializing package product supplier user interface...");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(ProductsSuppliers productsSuppliers : poductsSuppliersCollection) {
            productSupplierVector.add(productsSuppliers);

        }

        for(PackagesProductsSuppliers packagesProductsSuppliers : packagesProductsSuppliersCollection) {
            packagesProductsSuppliersVector.add(packagesProductsSuppliers);
            
        }

        productSupplierDataTableModel = new ProductSupplierDataTableModel(productSupplierHeaders, productSupplierVector);
        packagesProductsSuppliersDataTableModel = new PackagesProductsSuppliersDataTableModel(packageProductSupplierHeaders, packagesProductsSuppliersVector);

        for(Packages packages : packagesCollection) {
            packagesModel.addElement(packages);
        }

        packagesList = new PackageDragList(packagesModel);
        packagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		packagesList.setLayoutOrientation(JList.VERTICAL);
		packagesList.setVisibleRowCount(-1);

        JScrollPane packagesListScroller = new JScrollPane(packagesList);
		packagesListScroller.setPreferredSize(new Dimension(250, 80));

        productSupplierTable = new JTable(productSupplierDataTableModel);
        productSupplierTable.setFillsViewportHeight(true);
        productSupplierTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        productSupplierTable.setDropMode(DropMode.USE_SELECTION); //hightlight the entire row when drop an item

        productSupplierTable.setTransferHandler(new TableTransferHandler(packagesList, delegate, PackagesProductsSuppliersGUI.this,
                productSupplierDataTableModel, packagesProductsSuppliersDataTableModel));

        ListSelectionModel rowSelectionMode = productSupplierTable.getSelectionModel();
        rowSelectionMode.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();
                if(! listSelectionModel.isSelectionEmpty()) {
                    int row = productSupplierTable.getSelectedRow();
                    selectedProductsSupplier = productSupplierDataTableModel.getSelectedRow(row);
                }
            }
        });

        JScrollPane productSupplierScrollPane = new JScrollPane(productSupplierTable);

        JPanel listPanel = new JPanel(new SpringLayout());
        listPanel.add(packagesListScroller);
        listPanel.add(productSupplierScrollPane);
        listPanel.add(new JLabel(""));
        listPanel.add(relationshipButton);

        SpringUtilities.makeCompactGrid(listPanel,
				2, 2,
				6, 6,
				6, 6);

        //generate the package product supplier data table
        JTable packageProductSupplierTable = new JTable(packagesProductsSuppliersDataTableModel);
        packageProductSupplierTable.setFillsViewportHeight(true);
        packageProductSupplierTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane packageProductSupplierScrollPane = new JScrollPane(packageProductSupplierTable);

        this.getContentPane().add(new NavigationButtonPanel(TravelParts.PACKAGEPRODUCTSUPPLIER), BorderLayout.NORTH);
        this.getContentPane().add(listPanel, BorderLayout.CENTER);
        this.getContentPane().add(packageProductSupplierScrollPane, BorderLayout.SOUTH);

    }

    @Override
    protected void adjustSize(int width, int height) {
        
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(ADD)) {
            if(packagesList.getSelectedIndex() != -1 && productSupplierTable.getSelectedRow() != -1) {
                try {
                    PackagesProductsSuppliers packagesProductsSuppliers = delegate.makePackagesProductsSuppliersRelationship(
                            (Packages)packagesList.getSelectedValue(), selectedProductsSupplier);

                    packagesProductsSuppliersDataTableModel.addRelationship(packagesProductsSuppliers);

                } catch (TEBusinessException ex) {
                    Logger.getLogger(PackagesProductsSuppliersGUI.class.getName()).log(Level.SEVERE, null, ex);
                    
                }

            } else {
                JOptionPane.showMessageDialog(this,
                        "You need to select valid package and product supplier to make the relationship",
                        "Make new Relationship",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    /*
     * from TransferHandler.java
     *
     *
     *         private void autoscroll(JComponent c, Point pos) {
            if (c instanceof Scrollable) {
                Scrollable s = (Scrollable) c;
                if (pos.y < inner.y) {
                    // scroll upward
                    int dy = s.getScrollableUnitIncrement(outer, SwingConstants.VERTICAL, -1);
                    Rectangle r = new Rectangle(inner.x, outer.y - dy, inner.width, dy);
                    c.scrollRectToVisible(r);
                } else if (pos.y > (inner.y + inner.height)) {
                    // scroll downard
                    int dy = s.getScrollableUnitIncrement(outer, SwingConstants.VERTICAL, 1);
                    Rectangle r = new Rectangle(inner.x, outer.y + outer.height, inner.width, dy);
                    c.scrollRectToVisible(r);
                }

                if (pos.x < inner.x) {
                    // scroll left
                    int dx = s.getScrollableUnitIncrement(outer, SwingConstants.HORIZONTAL, -1);
                    Rectangle r = new Rectangle(outer.x - dx, inner.y, dx, inner.height);
                    c.scrollRectToVisible(r);
                } else if (pos.x > (inner.x + inner.width)) {
                    // scroll right
                    int dx = s.getScrollableUnitIncrement(outer, SwingConstants.HORIZONTAL, 1);
                    Rectangle r = new Rectangle(outer.x + outer.width, inner.y, dx, inner.height);
                    c.scrollRectToVisible(r);
                }
            }
        }
     */

}
