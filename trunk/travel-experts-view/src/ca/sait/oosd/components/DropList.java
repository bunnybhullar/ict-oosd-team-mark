package ca.sait.oosd.components;

import ca.sait.oosd.business.TEBusinessDelegate;
import ca.sait.oosd.listeners.ListDropListener;
import ca.sait.oosd.models.ProductSupplierDataTableModel;
import java.awt.Insets;
import java.awt.Point;
import java.awt.dnd.Autoscroll;
import java.awt.dnd.DropTarget;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author duminda
 * 
 */
public class DropList extends JList implements Autoscroll{

    private Insets insets;

    public DropList() {

    }

    public DropList(TEJFrame parent, DefaultListModel listData, DragList dragList, TEBusinessDelegate delegate,
            ProductSupplierDataTableModel productSupplierDataTableModel) {
        super(listData);

         new DropTarget(this, new ListDropListener(this, dragList, delegate, parent, productSupplierDataTableModel));
         
    }
    
    public Insets getAutoscrollInsets() {
        return insets;
        
    }

    public void autoscroll(Point cursorLocn) {
        
    }

}
