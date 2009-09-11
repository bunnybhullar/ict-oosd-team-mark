package ca.sait.oosd.components;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.listeners.ListDragGestureListener;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author duminda
 * 
 */
public class DragList extends JList{

    public DragList() {

    }

    public DragList(DefaultListModel listData) {
        super(listData);

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(this,
                DnDConstants.ACTION_MOVE,
                new ListDragGestureListener());
        
    }

    public TEObject getSelectedOption() {
        return (TEObject) this.getSelectedValue();
        
    }
    
}
