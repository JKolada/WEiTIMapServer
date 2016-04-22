package org.weiti_map.table_models;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.weiti_map.db.RoomsTableObject;

public class RoomTableModel extends AbstractTableModel implements TableModel {	
	
	private static final long serialVersionUID = 1L;
	
	private final int JTABLE_ROW_NUM;		
	private final int JTABLE_COLS_NUM = 5;
	
	private String[][] data;
	
	private final static String[] COLUMNS = 
		{"ID",
		 "Nazwa Sali",
		 "Piêtro",
		 "X",
		 "Y",
		 " "};
	
	
	public RoomTableModel(RoomsTableObject rooms) {
		super();
		JTABLE_ROW_NUM = rooms.getRowCount();
		data = new String[JTABLE_ROW_NUM][JTABLE_COLS_NUM];
		rooms.fillData(data);
	}

	public String getColumnName(int col) {
		return COLUMNS[col];
    }	

	public Class<?> getColumnClass(int c) {
        return String.class;
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
	
	void setValueAt(String text, int row, int col) {
		data[row][col] = text;
        fireTableCellUpdated(row, col);
    }

}
