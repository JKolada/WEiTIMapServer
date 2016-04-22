package org.weiti_map.db;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupPlanObject {
	
	private char parzystosc_tabeli;
	private final String nazwa_grupy;	
	private final List<LectureObj> zajecia = new ArrayList<LectureObj>();
	
	GroupPlanObject(String nazwa_gr) {
		nazwa_grupy = nazwa_gr;	     
	}
	
	GroupPlanObject() {
		nazwa_grupy = "error";
	}

	public String getGroupName() {
		return nazwa_grupy;
	}

	void add(LectureObj myLecture) {
		zajecia.add(myLecture);
	}
	
	char getParzystosc() {
		return parzystosc_tabeli;				
	}	

	public void fillData(String[][] data, GroupPlanObject plan, char parzystosc) {
		
		parzystosc_tabeli = parzystosc;
		String[] poj_zaj_info = new String[6];
		int row_no, col_no = 0;
		char classesAreEven = 'X';
		
		for (LectureObj poj_zaj: zajecia) {		
			Boolean tmp = poj_zaj.isEven();
			
			if (tmp == null) {
				classesAreEven = 'X';
			} else {
				boolean b = tmp.booleanValue();
				if (b == true) {
					classesAreEven = 'P';					
				} else {
					classesAreEven = 'N';					
				}
			}
							
			if (classesAreEven == parzystosc_tabeli || classesAreEven == 'X') {
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