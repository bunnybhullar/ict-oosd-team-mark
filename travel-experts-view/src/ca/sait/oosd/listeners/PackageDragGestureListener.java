/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.sait.oosd.listeners;

import ca.sait.oosd.components.PackageDragList;
import ca.sait.oosd.hibernate.Packages;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

/**
 *
 * @author user1
 */
public class PackageDragGestureListener implements DragGestureListener{

    public void dragGestureRecognized(DragGestureEvent dge) {
        PackageDragList packageDragList = (PackageDragList) dge.getComponent();
        Packages packages = (Packages) packageDragList.getSelectedValue();

        StringSelection transferable = new StringSelection(Long.toString(packages.getPackageid()));
        dge.startDrag(DragSource.DefaultCopyDrop,
                transferable,
                new TEDragSourceListener());

    }

}
