package ca.sait.oosd.listeners;

import ca.sait.oosd.components.DragList;
import ca.sait.oosd.hibernate.Products;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

/**
 *
 * @author duminda
 * 
 */
public class ListDragGestureListener implements DragGestureListener{

    public void dragGestureRecognized(DragGestureEvent dge) {
        DragList dragList = (DragList)dge.getComponent();
        Products products = (Products) dragList.getSelectedValue();

        StringSelection transferable = new StringSelection(Long.toString(products.getProductid()));
        dge.startDrag(DragSource.DefaultCopyDrop,
                transferable,
                new TEDragSourceListener());
    }

}
