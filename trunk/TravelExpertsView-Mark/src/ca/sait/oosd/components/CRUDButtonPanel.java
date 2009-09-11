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
 * @purpose This is the button panel that can be used in the add, delete and edit buttons
 * 
 */
public class CRUDButtonPanel extends JPanel {

    private ImageButton addButton;
    private ImageButton deleteButton;
    private ImageButton updateButton;
    private ImageButton clearButton;

    public CRUDButtonPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.add(Box.createHorizontalGlue());

        addButton = new ImageButton("resources/cup.gif", "Add", 70, 120);
        deleteButton = new ImageButton("resources/cup.gif", "Delete", 70, 120);
        updateButton = new ImageButton("resources/cup.gif", "Update", 70, 120);
        clearButton = new ImageButton("resources/cup.gif", "Clear", 70, 120);

        this.initPanel();

    }

    private void initPanel() {

        this.add(addButton);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(updateButton);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(clearButton);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(deleteButton);
    }

    public void doActionPerform(CRUDButtons buttonType, TravelParts formType) {
        
    }

}
