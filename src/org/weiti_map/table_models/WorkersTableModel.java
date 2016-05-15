package org.weiti_map.table_models;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.weiti_map.db.WorkersTableObject;

public class WorkersTableModel extends AbstractTableModel
		implements TableModel {

	private static final long serialVersionUID = 4812024452486877443L;
	private final int JTABLE_ROW_NUM;
	private final int JTABLE_COLS_NUM = 3;

	private String[][] data;

	private final static String[] COLUMNS = { "ID", "Imiê", "Nazwisko", " " };

	public WorkersTableModel(WorkersTableObject workers) {
		super();
		JTABLE_ROW_NUM = workers.getRowCount();
		data = new String[JTABLE_ROW_NUM][JTABLE_COLS_NUM];
		workers.fillData(data);
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
