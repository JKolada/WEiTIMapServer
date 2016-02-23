package org.weiti_map;

import java.util.ArrayList;
import java.util.List;

public class RoomsTableObject {
	
	private final List<RoomObj> sala;
		
	RoomsTableObject() {
		sala = new ArrayList<RoomObj>();
	}
	
	void add(RoomObj room) {
		sala.add(room);
	}

	void fillData(String[][] data) {
		String[] poj_sala = new String[5];
		int row_no = 0;
		
		for (RoomObj poj_prac_temp: sala) {			
			poj_sala = poj_prac_temp.getRoomData();	
			row_no = Integer.parseInt(poj_sala[0])-1;						
			for (int k = 0; k < 5; k++) {
				data[row_no][k] = poj_sala[k];				
			}
		}
		
		
	}

	int getRoomsCount() {
		return sala.size();
	}

	

}
