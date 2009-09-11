package ca.sait.oosd.components;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.listeners.CustomerDragGestureListener;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author duminda
 * @purpose This is the drag list for the customer to make the new agent assignment
 * 
 */
public class CustomerDragList extends JList{

    public CustomerDragList() {

    }

    public CustomerDragList(DefaultListModel listData) {
        super(listData);

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(this,
                DnDConstants.ACTION_MOVE,
                new CustomerDragGestureListener());
        
    }

    public TEObject getSelectedOption() {
        return (TEObject) this.getSelectedValue();

    }
    
}
