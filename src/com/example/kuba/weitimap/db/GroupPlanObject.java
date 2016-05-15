package com.example.kuba.weitimap.db;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ LectureObj.class })
public class GroupPlanObject implements Serializable {

	private static final long serialVersionUID = 2366854110722067579L;

	private char parzystosc_tabeli;
	private String nazwa_grupy;

	private ArrayList<LectureObj> zajecia = new ArrayList<LectureObj>();

	public GroupPlanObject(String nazwa_gr) {
		nazwa_grupy = nazwa_gr;
	}

	GroupPlanObject() {
		nazwa_grupy = "error";
	}

	public void setGroupName(String group) {
		nazwa_grupy = group;
	}

	@XmlAttribute(name = "lectures")
	public String getGroupName() {
		return nazwa_grupy;
	}

	public void add(LectureObj myLecture) {
		zajecia.add(myLecture);
	}

	public void setLectureArray(ArrayList<LectureObj> lectures) {
		zajecia = lectures;
	}

	@XmlElementRef
	public ArrayList<LectureObj> getLectureArray() {
		return zajecia;
	}

	char getParzystosc() {
		return parzystosc_tabeli;
	}

	public void fillData(String[][] data, GroupPlanObject plan,
			char parzystosc) {

		parzystosc_tabeli = parzystosc;
		String[] poj_zaj_info = new String[6];
		int row_no, col_no = 0;
		char classesAreEven = 'X';

		for (LectureObj poj_zaj : zajecia) {
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
				// "nazwa_sali", "nazwa_dnia", "id_godziny", "parzystoœæ",
				// "skrot_nazwy_zajec", "rodz_zajec"
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
			data[row_no][col_no] = poj_zaj_info[4] + " " + poj_zaj_info[5] + " "
					+ poj_zaj_info[0];
		}

	}
}