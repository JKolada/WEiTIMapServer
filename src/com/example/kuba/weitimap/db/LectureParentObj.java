package com.example.kuba.weitimap.db;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class LectureParentObj implements Serializable {

	private static final long serialVersionUID = -826240034398882484L;
	protected String activity_name;

	public abstract String[] getLectureData();
}
