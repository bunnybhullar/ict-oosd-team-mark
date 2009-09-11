package ca.sait.oosd.travel.suppliers;

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.components.NavigationButtonPanel;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.hibernate.Suppliers;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;
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
    Collection<Suppliers> supplierCollection;
    private TEBusinessDelegate delegate;

    public SuppliersGUI() {
        super();

        delegate = new TEBusinessDelegateImpl();
        supplierCollection = delegate.getSuppliersCollection();
        
        this.initGUI();
        this.alignFrameOnScreen(this);
        
    }
    
	@Override
	protected void adjustSize(int width, int height) {

	}

	@Override
	protected void initGUI() {
        helper.log(LogLevel.INFO, "Start initlializing Supplier user interface...");

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String[] labels = {"Supplier ID", "Supplier Name"};
		JPanel dataEntryPane = new JPanel(new SpringLayout());
		for (String label : labels) {
			JLabel l = new JLabel(label, JLabel.TRAILING);
			dataEntryPane.add(l);
			JTextField textField = new JTextField(10);
			l.setLabelFor(textField);
			dataEntryPane.add(textField);
			pack();
		}

		SpringUtilities.makeCompactGrid(dataEntryPane,
				labels.length, 2,
				6, 6,
				6, 6);

		//list to display the data
		JList list = new JList(supplierCollection.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
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
		mainPane.add(new NavigationButtonPanel(TravelParts.SUPPLIER), BorderLayout.NORTH);
		mainPane.add(centerPane, BorderLayout.CENTER);
		mainPane.add(listScroller, BorderLayout.EAST);

        this.getContentPane().add(mainPane, BorderLayout.CENTER);

	}



}
