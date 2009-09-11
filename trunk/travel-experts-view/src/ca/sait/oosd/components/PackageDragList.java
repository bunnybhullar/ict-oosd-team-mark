/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.sait.oosd.components;

import ca.sait.oosd.business.TEObject;
import ca.sait.oosd.listeners.PackageDragGestureListener;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author user1
 */
public class PackageDragList extends JList{

    public PackageDragList() {

    }

    public PackageDragList(DefaultListModel listData) {
        super(listData);

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(this,
                DnDConstants.ACTION_MOVE,
                new PackageDragGestureListener());

    }

    public TEObject getSelectedOption() {
        return (TEObject) this.getSelectedValue();

    }
    
}
