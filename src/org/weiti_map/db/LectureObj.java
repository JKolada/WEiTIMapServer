package org.weiti_map.db;

import java.util.ArrayList;

import org.weiti_map.LectureParentObj;

public class LectureObj extends LectureParentObj{ 	
	
	private String nazwa_sali;
	private String nazwa_dnia;
	private String id_godziny;
	private String parzysto��;
	private String rodz_zaj��;
	 
	LectureObj(ArrayList<String> poj_zaj�cia) {	
		nazwa_sali = poj_zaj�cia.get(0);
		nazwa_dnia = poj_zaj�cia.get(1);
		id_godziny = poj_zaj�cia.get(2);
		parzysto�� = poj_zaj�cia.get(3);
		skr�t_nazwy_zaj�� = poj_zaj�cia.get(4);
		rodz_zaj�� = poj_zaj�cia.get(5);
	}
	
	protected String[] getLectureData() {
		final String[] lectureData = {nazwa_sali, nazwa_dnia, id_godziny, parzysto��, skr�t_nazwy_zaj��, rodz_zaj��}; 
		return lectureData;
	}
	
	char isEven() {
		if (parzysto��.charAt(0) == 'P') {
			return 'Y';
		} else if (parzysto��.charAt(0) == 'N') {
			return 'N';
		} else {
			return 'X';
		}
	}

}
