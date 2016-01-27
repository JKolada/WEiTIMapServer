package org.weiti_map;

import java.util.ArrayList;

public class MyLecture {
// "nazwa_sali", "nazwa_dnia", "godziny", "parzystosc", "nazwa_zajec", "rodz_zajec"

	private String[] lectureData = new String[6];	

	public MyLecture(ArrayList<String> poj_zajecia) {
		for (int i = 0; i < 6; i++) {
			lectureData[i] = poj_zajecia.get(i);
		}
	}
	
	public String[] getLectureData() {
		return lectureData;
	}

}
