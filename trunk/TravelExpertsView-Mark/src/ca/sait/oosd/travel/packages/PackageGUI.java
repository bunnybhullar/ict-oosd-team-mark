package ca.sait.oosd.travel.packages;

import ca.sait.oosd.SpringUtilities;
import ca.sait.oosd.TravelParts;
import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.business.TEBusinessDelegateImpl;
import ca.sait.oosd.business.TEBusinessException;
import ca.sait.oosd.components.NavigationButtonPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JDateChooser;

import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.logger.LoggerHelper;
import ca.sait.oosd.hibernate.Packages;
import ca.sait.oosd.logger.LogLevel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Collection;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author 
 *
 */
public class PackageGUI extends TEJFrame {

	private static final long serialVersionUID = 1L;
    private LoggerHelper helper = new LoggerHelper(PackageGUI.class.getName());
    private Collection<Packages> packageCollection;
    private TEBusinessDelegate delegate;
    private Packages packages;
    private DefaultListModel model;

    final JTextField packageIdTextField = new JTextField(15);
    final JTextField packageNameTextField = new JTextField(15);
    final JDateChooser startDate = new JDateChooser(new Date());
    final JDateChooser endDate = new JDateChooser(new Date());
    final JTextField descriptionTextField = new JTextField(15);
    final JTextField basePriceTextField = new JTextField(15);
    final JTextField commissionTextField = new JTextField(15);


	public PackageGUI() {
        super();

        delegate = new TEBusinessDelegateImpl();
        packageCollection = delegate.getPackageCollection();

        model = new DefaultListModel();

		initGUI();
		super.alignFrameOnScreen(this);
        
	}
	
	protected void initGUI() {
        helper.log(LogLevel.INFO, "Start initlializing Package user interface...");

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel dataEntryPane = new JPanel(new SpringLayout());
        dataEntryPane.add(new JLabel("Package ID", JLabel.TRAILING));
        packageIdTextField.setEditable(false);
        dataEntryPane.add(packageIdTextField);
        dataEntryPane.add(new JLabel("Package Name", JLabel.TRAILING));
        dataEntryPane.add(packageNameTextField);
        dataEntryPane.add(new JLabel("Package Start Date", JLabel.TRAILING));
        dataEntryPane.add(startDate);
        dataEntryPane.add(new JLabel("Package End Date", JLabel.TRAILING));
        dataEntryPane.add(endDate);
        dataEntryPane.add(new JLabel("Description", JLabel.TRAILING));
        dataEntryPane.add(descriptionTextField);
        dataEntryPane.add(new JLabel("Base Price", JLabel.TRAILING));
        dataEntryPane.add(basePriceTextField);
        dataEntryPane.add(new JLabel("Agency Commission", JLabel.TRAILING));
        dataEntryPane.add(commissionTextField);

        SpringUtilities.makeCompactGrid(dataEntryPane,
				7, 2,
				6, 6,
				6, 6);


        //list to display the data
        for(Packages pack : packageCollection) {
            model.addElement(pack);
        }
        
		final JList list = new JList(model);
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

		GridLayout grid = new GridLayout(2, 1);
		JPanel centerPane = new JPanel();
		centerPane.setLayout(grid);

		centerPane.add(dataEntryPane);
		centerPane.add(southPane, JPanel.BOTTOM_ALIGNMENT);

        //add the listener to the list
        ListSelectionModel selectionModel = list.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                packages = (Packages)list.getSelectedValue();
                packageIdTextField.setText(Long.toString(packages.getPackageid()));
                packageNameTextField.setText(packages.getPkgname());
                descriptionTextField.setText(packages.getPkgdesc());
                basePriceTextField.setText(packages.getPkgbaseprice().toString());
                commissionTextField.setText(packages.getPkgagencycommission().toString());
                startDate.setDate(packages.getPkgstartdate());
                endDate.setDate(packages.getPkgenddate());
            }
        });

        //clear the content of the data enty screen
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearComponents();

            }
        });

        //add new record to the database
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    packages = new Packages();
                    packages.setPkgname(packageNameTextField.getText().trim());
                    packages.setPkgdesc(descriptionTextField.getText().trim());
                    packages.setPkgbaseprice(new BigDecimal(basePriceTextField.getText().trim()));
                    packages.setPkgagencycommission(new BigDecimal(commissionTextField.getText().trim()));
                    packages.setPkgstartdate(startDate.getDate());
                    packages.setPkgenddate(endDate.getDate());

                    packages = (Packages)delegate.save(packages);
                    model.addElement(packages);

                    clearComponents();
                    
                } catch (TEBusinessException ex) {
                    helper.log(LogLevel.ERROR, "Exception occured while saving data..." + ex.getMessage());
                    
                }
            }
        });

        //delete the record from the database
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(packages != null) {
                    try {
                        int position = model.indexOf(packages, 0);
                        delegate.delete(packages);

                        clearComponents();
                        removeFromListModel(position);

                    } catch (TEBusinessException ex) {
                        helper.log(LogLevel.ERROR, "Exception occured while deleting data..." + ex.getMessage());
                        
                    }
                }
            }
        });

        //update a record from the user interface
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    packages = new Packages();
                    packages.setPackageid(Long.parseLong(packageIdTextField.getText()));
                    packages.setPkgname(packageNameTextField.getText().trim());
                    packages.setPkgdesc(descriptionTextField.getText().trim());
                    packages.setPkgbaseprice(new BigDecimal(basePriceTextField.getText().trim()));
                    packages.setPkgagencycommission(new BigDecimal(commissionTextField.getText().trim()));
                    packages.setPkgstartdate(startDate.getDate());
                    packages.setPkgenddate(endDate.getDate());

                    packages = (Packages)delegate.update(packages);
                    updateListModel(packages);

                } catch (TEBusinessException ex) {
                    helper.log(LogLevel.ERROR, "Exception occured while saving data..." + ex.getMessage());

                }
            }
        });

        //pass the product type enum to the NavigationButtonPanel, so that it disable the particular button
        this.getContentPane().add(new NavigationButtonPanel(TravelParts.PACKAGE), BorderLayout.NORTH);
        this.getContentPane().add(listScroller, BorderLayout.EAST);
        this.getContentPane().add(centerPane, BorderLayout.CENTER);

		
	}

	@Override
	protected void adjustSize(int width, int height) {
		
	}

    //update the list model when user update a record from the user interface
    private void updateListModel(Packages packages) {
        for(Packages pack : packageCollection) {
            if(pack.getPackageid() == packages.getPackageid()) {
                int position = model.indexOf(pack, 0);
                model.set(position, packages);
            }
        }
        
    }

    private void removeFromListModel(int position) {
        model.removeElementAt(position);
        
    }

    private void clearComponents() {

        packageIdTextField.setText("");
        packageNameTextField.setText("");
        descriptionTextField.setText("");
        basePriceTextField.setText("");
        commissionTextField.setText("");
        startDate.setDate(new Date());
        endDate.setDate(new Date());

    }
	
}
