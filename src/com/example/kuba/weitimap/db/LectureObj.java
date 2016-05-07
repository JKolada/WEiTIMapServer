package com.example.kuba.weitimap.db;

import java.io.Serializable;
import java.util.ArrayList;

public class LectureObj extends LectureParentObj implements Serializable { 	
	
	private static final long serialVersionUID = -4013067156404407589L;
	private String nazwa_sali;
	private String nazwa_dnia;
	private String id_godziny;
	private String parzysto��;
	private String rodz_zaj��;
	
	
	public LectureObj(ArrayList<String> poj_zaj�cia) {	
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
	
	Boolean isEven() {
		if (parzysto��.charAt(0) == 'P') {
			return true;
		} else if (parzysto��.charAt(0) == 'N') {
			return false;
		} else {
			return null;
		}
	}

}
