package com.example.kuba.weitimap.db;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LectureObj extends LectureParentObj implements Serializable {

	private static final long serialVersionUID = -4013067156404407589L;

	private String room_name;
	private String day_name;
	private String hour_id;
	private String parity;
	private String activity_kind;

	public LectureObj() {
	};

	public void setRoomName(String a) {
		room_name = a;
	}

	public void setDayName(String a) {
		day_name = a;
	}

	public void setHourId(String a) {
		hour_id = a;
	}

	public void setParity(String a) {
		parity = a;
	}

	public void setActivityKind(String a) {
		activity_kind = a;
	}

	public void setActivityName(String a) {
		activity_name = a;
	}

	@XmlAttribute
	public String getRoomName() {
		return room_name;
	}

	@XmlAttribute
	public String getDayName() {
		return day_name;
	}

	@XmlAttribute
	public String getHourId() {
		return hour_id;
	}

	@XmlAttribute
	public String getParity() {
		return parity;
	}

	@XmlAttribute
	public String getActivityKind() {
		return activity_kind;
	}

	@XmlAttribute
	public String getActivityName() {
		return activity_name;
	}

	public LectureObj(ArrayList<String> poj_zajêcia) {
		room_name = poj_zajêcia.get(0);
		day_name = poj_zajêcia.get(1);
		hour_id = poj_zajêcia.get(2);
		parity = poj_zajêcia.get(3);
		activity_name = poj_zajêcia.get(4);
		activity_kind = poj_zajêcia.get(5);
	}

	public String[] getLectureData() {
		final String[] lectureData = { room_name, day_name, hour_id, parity,
				activity_name, activity_kind };
		return lectureData;
	}

	Boolean isEven() {
		if (parity.equals("P")) {
			return true;
		} else if (parity.equals("N")) {
			return false;
		} else {
			return null;
		}
	}

	// woohoo, i know this is not a good looking code
	// just playing with generics with extended classes
	@Override
	protected
	String[] getData() {
		return getLectureData();
	}

}
