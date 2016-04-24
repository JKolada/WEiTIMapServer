package org.weiti_map.db;

import java.util.ArrayList;
import java.util.List;

public class CustomTableObject<RowObj extends AbstractRowObject> extends AbstractTableObject {
	
	private final List<RowObj> rowList;
	private final int columnCount;
		
	CustomTableObject(int colCount) {
		rowList = new ArrayList<RowObj>();
		columnCount = colCount;
	}
	
	void add(RowObj singleObj) {
		rowList.add(singleObj);
	}

	public void fillData(String[][] data) {
		String[] single_row = new String[columnCount];
		int row_no = 0;
		
		for (RowObj tempRowObj: rowList) {
			single_row = tempRowObj.getData();
			row_no = Integer.parseInt(single_row[0]) - 1;
			for (int k = 0; k < columnCount; k++) {
				data[row_no][k] = single_row[k];
			}
		}
	}

	public int getRowCount() {
		return rowList.size();
	}

}
