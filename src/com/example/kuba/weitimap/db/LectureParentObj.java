package com.example.kuba.weitimap.db;

import java.io.Serializable;

public abstract class LectureParentObj implements Serializable {
	
	private static final long serialVersionUID = -826240034398882484L;
	protected String skr�t_nazwy_zaj��;	
	
	protected abstract String[] getLectureData();
}
