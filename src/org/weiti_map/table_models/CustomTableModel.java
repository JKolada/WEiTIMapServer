package org.weiti_map.table_models;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.weiti_map.db.AbstractTableObject;

public class CustomTableModel<TableObjectClass extends AbstractTableObject> extends AbstractTableModel implements TableModel {	
	
	private static final long serialVersionUID = -5198952496046156405L;
	private final int JTABLE_ROW_NUM;		
	private final int JTABLE_COLS_NUM;
	
	private String[][] data;
	//asdas
	
	private static String[] COLUMNS;
	
	public CustomTableModel(int ColCount, String[] ColNames, TableObjectClass tabObj) {
		super();
		JTABLE_COLS_NUM = ColCount;
		JTABLE_ROW_NUM = tabObj.getRowCount();
		COLUMNS = new String[ColCount + 1];
		System.arraycopy(ColNames, 0, COLUMNS, 0, ColNames.length);
		COLUMNS[ColCount] = " ";
		data = new String[JTABLE_ROW_NUM][JTABLE_COLS_NUM];
		tabObj.fillData(data);
	}

	public String getColumnName(int col) {
		return COLUMNS[col];
    }	

	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
    @Override
    public boolean isCellEditable(int row, int column) {
	    return false;
    }
	
	@Override
	public int getRowCount() {
		return JTABLE_ROW_NUM;
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length - 1;
	}

	@Override
	public Object getValueAt(int row, int col) {
        return data[row][col];
	}	
	
	public void setValueAt(String text, int row, int col) {
		data[row][col] = text;
        fireTableCellUpdated(row, col);
    }

}
