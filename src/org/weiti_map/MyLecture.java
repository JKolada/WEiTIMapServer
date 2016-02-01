package org.weiti_map;

import java.util.ArrayList;

public class MyLecture {
// "nazwa_sali", "nazwa_dnia", "id_godziny", "parzystoœæ", "skrot_nazwy_zajec", "rodz_zajec"

	private String[] lectureData;	

	public MyLecture(ArrayList<String> poj_zajecia) {
		lectureData = new String[6];	
		for (int i = 0; i < 6; i++) {
			lectureData[i] = poj_zajecia.get(i);
		}
	}
	
	public String[] getLectureData() {
		return lectureData;
	}
	
	public char isEven() {
		if (lectureData[3].charAt(0) == 'P') {
			return 'Y';
		} else if (lectureData[3].charAt(0) == 'N') {
			return 'N';
		} else {
			return 'X';
		}
	}

}
