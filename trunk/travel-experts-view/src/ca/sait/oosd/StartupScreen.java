package ca.sait.oosd;

import ca.sait.oosd.components.ImageButton;
import ca.sait.oosd.components.ImagePanel;
import ca.sait.oosd.components.TEJFrame;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;
import ca.sait.oosd.travel.packages.PackageGUI;
import ca.sait.oosd.travel.packages.PackagesProductsSuppliersGUI;
import ca.sait.oosd.travel.products.ProductSupplierGUI;
import ca.sait.oosd.travel.products.ProductsGUI;
import ca.sait.oosd.travel.suppliers.SuppliersGUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author duminda
 * @purpose This is the startup screen of the project. This screen can be used to navigate to other screens
 *          such as supplier, agent, product and packages. 
 */
public class StartupScreen extends TEJFrame implements ActionListener{

    private LoggerHelper helper = new LoggerHelper(StartupScreen.class.getName());
    private ImageButton exitButton;
    private ImageButton supplierButton;
    private ImageButton productButton;
    private ImageButton packageButton;
    private ImageButton agentButton;

    private final int WIDTH = 600;
	private final int HEIGHT = 500;

    //default constructor
    public StartupScreen() {
        super();

        //create the buttons with a image
        exitButton = new ImageButton("resources/cup.gif", "Exit", 75, 175);
        supplierButton = new ImageButton("resources/cup.gif", "Supplier", 75, 175);
        productButton = new ImageButton("resources/cup.gif", "Product", 75, 175);
        packageButton = new ImageButton("resources/cup.gif", "Package", 75, 175);
        agentButton = new ImageButton("resources/cup.gif", " Agent  ", 75, 175);

        this.initGUI();
        this.adjustSize(WIDTH, HEIGHT);
        this.alignFrameOnScreen(WIDTH, HEIGHT);
        
    }
    
    @Override
    protected void initGUI() {
        //initializing the user interface
        helper.log(LogLevel.INFO, "Start initlialize the first screen...");
        
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //the panel to hold exit button and the travel expert image
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        rightPanel.add(Box.createVerticalGlue());

        rightPanel.add(exitButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        ImagePanel imagePanel = new ImagePanel("resources/laptopjet.jpg", 250, 350);
        rightPanel.add(imagePanel);


        //supplier, products, package and agent buttons in a vertical row
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(productButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(supplierButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(packageButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(agentButton);

        JPanel mainCenterPanel = new JPanel();

        mainCenterPanel.add(leftPanel);
        mainCenterPanel.add(rightPanel);

        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SuppliersGUI();
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductsGUI();
            }
        });

        packageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PackageGUI();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(NORMAL);
            }
        });

        this.getContentPane().add(mainCenterPanel, BorderLayout.CENTER);
        JLabel coptrightLabel = new JLabel("Travel Experts 2009");
        coptrightLabel.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(coptrightLabel, BorderLayout.SOUTH);
        
    }

    @Override
    protected void adjustSize(int width, int height) {
        this.setSize(new Dimension(width, height));
    }

    public void actionPerformed(ActionEvent e) {
        ImageButton button = (ImageButton)e.getSource();


    }

}
