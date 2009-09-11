package ca.sait.oosd.travel.products;

import ca.sait.oosd.hibernate.Products;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author duminda
 * Encapsulate product data
 */
public class TransferableProducts implements Transferable{

    private Products products;

    public TransferableProducts(Products products) {
        this.products = products;
    }

    public DataFlavor[] getTransferDataFlavors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
	public String toString() {
		return products.getProdname();
	}
		
}
