package org.weiti_map.db;

import java.util.ArrayList;

public class LectureViewObj extends LectureParentObj {

	private String id_zaj��;
	private String nazwa_zaj��;
	private String id_wyk�adowcy;
	private String imie_wyk�adowcy;
	private String nazwisko_wyk�adowcy;
	

	LectureViewObj(ArrayList<String> poj_zaj�cia) {
		id_zaj�� = poj_zaj�cia.get(0);
		skr�t_nazwy_zaj�� = poj_zaj�cia.get(1);
		nazwa_zaj�� = poj_zaj�cia.get(2);
		id_wyk�adowcy = poj_zaj�cia.get(3);
		imie_wyk�adowcy = poj_zaj�cia.get(4);
		nazwisko_wyk�adowcy = poj_zaj�cia.get(5);
	}
	
	protected String[] getLectureData() {
		final String[] lectureData = {id_zaj��, skr�t_nazwy_zaj��, nazwa_zaj��, id_wyk�adowcy, imie_wyk�adowcy, nazwisko_wyk�adowcy}; 
		return lectureData;
	}

}
