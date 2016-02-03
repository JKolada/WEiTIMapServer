package org.weiti_map;

import java.util.ArrayList;
import java.util.List;

public class WorkersTableObject {
	
	private final List<WorkerObj> pracownik;
		
	WorkersTableObject() {
		pracownik = new ArrayList<WorkerObj>();
	}
	
	void add(WorkerObj worker) {
		pracownik.add(worker);
	}

	void fillData(String[][] data, WorkersTableObject plan) {
		String[] poj_pracownik = new String[3];
		int row_no = 0;
		
		for (WorkerObj poj_prac_temp: pracownik) {			
			poj_pracownik = poj_prac_temp.getWorkerData();	
			row_no = Integer.parseInt(poj_pracownik[0]);						
			for (int k = 0; k < 3; k++) {
				data[row_no][k] = poj_pracownik[0];				
			}
		}
		
		
	}

	

}
