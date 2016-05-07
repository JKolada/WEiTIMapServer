package com.example.kuba.weitimap.db;

import java.io.Serializable;
import java.util.ArrayList;

public class LectureObj extends LectureParentObj implements Serializable { 	
	
	private static final long serialVersionUID = -4013067156404407589L;
	private String nazwa_sali;
	private String nazwa_dnia;
	private String id_godziny;
	private String parzystoœæ;
	private String rodz_zajêæ;
	
	
	public LectureObj(ArrayList<String> poj_zajêcia) {	
		nazwa_sali = poj_zajêcia.get(0);
		nazwa_dnia = poj_zajêcia.get(1);
		id_godziny = poj_zajêcia.get(2);
		parzystoœæ = poj_zajêcia.get(3);
		skrót_nazwy_zajêæ = poj_zajêcia.get(4);
		rodz_zajêæ = poj_zajêcia.get(5);
	}
	
	protected String[] getLectureData() {
		final String[] lectureData = {nazwa_sali, nazwa_dnia, id_godziny, parzystoœæ, skrót_nazwy_zajêæ, rodz_zajêæ}; 
		return lectureData;
	}
	
	Boolean isEven() {
		if (parzystoœæ.charAt(0) == 'P') {
			return true;
		} else if (parzystoœæ.charAt(0) == 'N') {
			return false;
		} else {
			return null;
		}
	}

}
