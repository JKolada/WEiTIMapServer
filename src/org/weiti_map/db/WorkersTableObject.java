package org.weiti_map.db;

import java.util.ArrayList;
import java.util.List;

public class WorkersTableObject extends AbstractTableObject {
	
	private final List<WorkerObj> pracownik;
		
	WorkersTableObject() {
		pracownik = new ArrayList<WorkerObj>();
	}
	
	void add(WorkerObj worker) {
		pracownik.add(worker);
	}

	public void fillData(String[][] data) {
		String[] poj_pracownik = new String[3];
		int row_no = 0;
		
		for (WorkerObj poj_prac_temp: pracownik) {			
			poj_pracownik = poj_prac_temp.getData();	
			row_no = Integer.parseInt(poj_pracownik[0]);						
			for (int k = 0; k < 3; k++) {
				data[row_no][k] = poj_pracownik[0];				
			}
		}
	}

	public int getRowCount() {
		return pracownik.size();
	}

}
