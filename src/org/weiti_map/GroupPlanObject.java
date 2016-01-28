package org.weiti_map;

import java.util.ArrayList;
import java.util.List;

public class GroupPlanObject {

	private final static int JTABLE_ROW_NUM = 12;
	private final static int JTABLE_COLS_NUM = 7;
	
	private String nazwa_grupy;	
	private List<MyLecture> zajecia = new ArrayList<MyLecture>();
	private boolean error = false;
	
	
	public GroupPlanObject(String nazwa_gr) {
		nazwa_grupy = nazwa_gr;	     
	}
	
	public GroupPlanObject() {
		nazwa_grupy = "error";
//		error = true;
	}

	public String getGroupName() {
		return nazwa_grupy;
	}

	public void add(MyLecture myLecture) {
		zajecia.add(myLecture);
	}

	public void fillData(String[][] data, GroupPlanObject plan,char parzystosc) {
		String[] poj_zaj_info = new String[6];

		boolean isP = true;
		if (parzystosc == 'N') {
			isP = false;
		}
		
		for (MyLecture poj_zaj: zajecia) {
			if (poj_zaj.isP() == isP) {
				poj_zaj_info = poj_zaj.getLectureData();
			} else {
				continue;
			}
			
			
		
		}
		
		
	}
	
	

}
