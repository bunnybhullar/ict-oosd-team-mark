
package ca.sait.oosd.components;

import ca.sait.oosd.TravelParts;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author duminda
 * @purpose This define the navigation button panel at the top of each user interface. 
 */
public class NavigationButtonPanel extends JPanel{

    private ImageButton supplierButton;
    private ImageButton productButton;
    private ImageButton packageButton;
    private ImageButton agentButton;
    private ImageButton productSupplierButton;
    private ImageButton packageProductSuppierButton;

    public NavigationButtonPanel(TravelParts current) {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.add(Box.createHorizontalGlue());

        supplierButton = new ImageButton("resources/cup.gif", "Supplier", 70, 120);
        productButton = new ImageButton("resources/cup.gif", "Product", 70, 120);
        packageButton = new ImageButton("resources/cup.gif", "Package", 70, 120);
        agentButton = new ImageButton("resources/cup.gif", " Agent  ", 70, 120);
        productSupplierButton = new ImageButton("resources/cup.gif", "Product Supplier", 70, 120);
        packageProductSuppierButton = new ImageButton("resources/cup.gif", "<html>Package Product<br /> Supplier</html>", 70, 120);
        
        initPanel(current);
        
    }

    private void initPanel(TravelParts current) {
        
        //disable the current page's button
        switch(current) {
            case PACKAGE:
                packageButton.setEnabled(false);
                break;
            case SUPPLIER:
                supplierButton.setEnabled(false);
                break;
            case PRODUCT:
                productButton.setEnabled(false);
                break;
            case AGENT:
                agentButton.setEnabled(false);
                break;
            default:
                break;
        }

        this.add(productButton);
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(supplierButton);
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(packageButton);
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(agentButton);
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(productSupplierButton);
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(packageProductSuppierButton);

    }
    
}
