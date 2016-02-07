package org.weiti_map;

import java.util.ArrayList;

public class LectureViewObj extends LectureParentObj{ 	
	
	private String nazwa_sali;
	private String nazwa_dnia;
	private String id_godziny;
	private String parzystoœæ;
	private String rodz_zajêæ;
	 
	LectureViewObj(ArrayList<String> poj_zajêcia) {	
		nazwa_sali = poj_zajêcia.get(0);
		nazwa_dnia = poj_zajêcia.get(1);
		id_godziny = poj_zajêcia.get(2);
		parzystoœæ = poj_zajêcia.get(3);
		skrót_nazwy_zajêæ = poj_zajêcia.get(4);
		rodz_zajêæ = poj_zajêcia.get(5);
	}
	
	String[] getLectureData() {
		final String[] lectureData = {nazwa_sali, nazwa_dnia, id_godziny, parzystoœæ, skrót_nazwy_zajêæ, rodz_zajêæ}; 
		return lectureData;
	}
	
	char isEven() {
		if (parzystoœæ.charAt(0) == 'P') {
			return 'Y';
		} else if (parzystoœæ.charAt(0) == 'N') {
			return 'N';
		} else {
			return 'X';
		}
	}

}
