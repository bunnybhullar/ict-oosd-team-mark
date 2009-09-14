package ca.sait.oosd.listeners;

import ca.sait.oosd.components.CustomerDragList;
import ca.sait.oosd.hibernate.Customers;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

/**
 *
 * @author duminda
 * @purpose This is the drag listner for the customer list box
 * 
 */
public class CustomerDragGestureListener implements DragGestureListener{

    public void dragGestureRecognized(DragGestureEvent dge) {
        CustomerDragList dragList = (CustomerDragList)dge.getComponent();
        Customers customer = (Customers)dragList.getSelectedValue();

        if(customer != null) {
            StringSelection transferable = new StringSelection(Long.toString(customer.getCustomerid()));
            dge.startDrag(DragSource.DefaultCopyDrop,
                    transferable,
                    new TEDragSourceListener());        	
        }
        
    }

}
