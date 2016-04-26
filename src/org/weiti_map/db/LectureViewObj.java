package org.weiti_map.db;

import java.util.ArrayList;

public class LectureViewObj extends LectureParentObj {

	private String id_zajêæ;
	private String nazwa_zajêæ;
	private String id_wyk³adowcy;
	private String imie_wyk³adowcy;
	private String nazwisko_wyk³adowcy;
	

	LectureViewObj(ArrayList<String> poj_zajêcia) {
		id_zajêæ = poj_zajêcia.get(0);
		skrót_nazwy_zajêæ = poj_zajêcia.get(1);
		nazwa_zajêæ = poj_zajêcia.get(2);
		id_wyk³adowcy = poj_zajêcia.get(3);
		imie_wyk³adowcy = poj_zajêcia.get(4);
		nazwisko_wyk³adowcy = poj_zajêcia.get(5);
	}
	
	protected String[] getLectureData() {
		final String[] lectureData = {id_zajêæ, skrót_nazwy_zajêæ, nazwa_zajêæ, id_wyk³adowcy, imie_wyk³adowcy, nazwisko_wyk³adowcy}; 
		return lectureData;
	}

}
