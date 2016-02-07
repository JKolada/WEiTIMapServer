package org.weiti_map;

import java.util.ArrayList;
import java.util.List;

class GroupPlanObject {
	
	private final String nazwa_grupy;	
	private final List<LectureViewObj> zajecia = new ArrayList<LectureViewObj>();
	
	GroupPlanObject(String nazwa_gr) {
		nazwa_grupy = nazwa_gr;	     
	}
	
	GroupPlanObject() {
		nazwa_grupy = "error";
	}

	String getGroupName() {
		return nazwa_grupy;
	}

	void add(LectureViewObj myLecture) {
		zajecia.add(myLecture);
	}

	void fillData(String[][] data, GroupPlanObject plan, char parzystosc) {
		String[] poj_zaj_info = new String[6];
		int row_no, col_no = 0;
		char classesAreEven = 'X';
		
		for (LectureViewObj poj_zaj: zajecia) {			
			switch (poj_zaj.isEven()) {
				case 'Y':
					classesAreEven = 'P';
					break;
				case 'N':
					classesAreEven = 'N';
					break;
				case 'X':
					classesAreEven = 'X';
					break;
			}			
			if (classesAreEven == parzystosc || classesAreEven == 'X') {
				poj_zaj_info = poj_zaj.getLectureData();
				// "nazwa_sali", "nazwa_dnia", "id_godziny", "parzystoœæ", "skrot_nazwy_zajec", "rodz_zajec"
			} else {
				continue;
			}
			
			row_no = Integer.parseInt(poj_zaj_info[2]) - 8;
			switch (poj_zaj_info[1]) {
				case "poniedzia³ek":
					col_no = 1;
					break;
				case "wtorek":
					col_no = 2;
					break;
				case "œroda":
					col_no = 3;
					break;
				case "czwartek":
					col_no = 4;
					break;
				case "pi¹tek":
					col_no = 5;
					break; 
			}			
			data[row_no][col_no] = poj_zaj_info[4] + " " + poj_zaj_info[5] + " " + poj_zaj_info[0];	
//			System.out.println(row_no + " " + col_no + " " + data[row_no][col_no]); //TO DELETE		
		}		
		
	}
}