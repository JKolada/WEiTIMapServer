package org.weiti_map;

import java.util.ArrayList;

public class WorkerObj extends AbstractRowObject {
	private String pracownik_id;
	private String imi�;
	private String nazwisko;
	
	WorkerObj(ArrayList<String> poj_pracownik) {
		pracownik_id = poj_pracownik.get(0);
		imi� = poj_pracownik.get(1);
		nazwisko = poj_pracownik.get(2);
	}
	
	String[] getData() {
		final String[] workerData = {pracownik_id, imi�, nazwisko}; 
		return workerData;
	}
}
