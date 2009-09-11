package ca.sait.oosd.models;

import ca.sait.oosd.business.TEObject;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duminda
 * This is the abstract model of all data tables. 
 */
public abstract class TERelationshipDataTableModel extends AbstractTableModel{

    private String[] headerNames;

    public TERelationshipDataTableModel(String[] headerNames) {
        this.headerNames = headerNames;
    }

	@Override
	public int getColumnCount() {
		return headerNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return headerNames[column];
	}

    public abstract int getRowCount();
    public abstract Object getValueAt(int rowIndex, int columnIndex);
    public abstract void addRelationship(TEObject teObject);
    public abstract void removeRelationship(int rowNumber);

}
