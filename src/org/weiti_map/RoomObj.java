package org.weiti_map;

import java.util.ArrayList;

class RoomObj {
	private String sala_id;
	private String nazwa_sali;
	private String piêtro_sali;
	private String mapa_x;
	private String mapa_y;
	
	
	RoomObj(ArrayList<String> poj_sala) {
		sala_id = poj_sala.get(0);
		nazwa_sali = poj_sala.get(1);
		piêtro_sali = poj_sala.get(2);
		mapa_x = poj_sala.get(3);
		mapa_y = poj_sala.get(4);
	}
	
	String[] getRoomData() {
		final String[] roomData = {sala_id, nazwa_sali, piêtro_sali, mapa_x, mapa_y};
		return roomData;
	}
	

}
