
public final class MyDatabaseUtilities {		

			public final static String CREATE_TB_PRACOWNICY =
			"CREATE TABLE tb_pracownicy (" +
			"	pracownik_id INTEGER PRIMARY KEY," +
			"	imie TEXT NOT NULL," +
			"	nazwisko TEXT NOT NULL" +
			")";

			public final static String CREATE_TB_RODZ_ZAJEC =
			"CREATE TABLE tb_rodz_zajec (" +
			"	rodz_id INTEGER PRIMARY KEY," +
			"	rodzaj_zajec TEXT NOT NULL" +
			")";

			public final static String CREATE_TB_GRUPY =
			"CREATE TABLE tb_grupy (" +
			"	grupa_id INTEGER PRIMARY KEY," +
			"	nazwa_grupy TEXT NOT NULL UNIQUE" +
			")";

			public final static String CREATE_TB_DNI_TYG =
			"CREATE TABLE tb_dni_tyg (" +
			"	dzien_tyg_id INTEGER PRIMARY KEY," +
			"	nazwa_dnia TEXT NOT NULL UNIQUE" +
			")";

			public final static String CREATE_TB_GODZINY =
			"CREATE TABLE tb_godziny (" +
			"	godz_id INTEGER PRIMARY KEY," +
			"	godziny TEXT NOT NULL UNIQUE," +
			"	godziny_num INTEGER NOT NULL UNIQUE" +
			")";

			public final static String CREATE_TB_SALE =
			"CREATE TABLE tb_sale (" +
			"	sala_id INTEGER PRIMARY KEY," +
			"	nazwa_sali TEXT NOT NULL UNIQUE, " +
			"	pietro_sali INTEGER NOT NULL," +
			"	mapa_x INTEGER NOT NULL," +
			"	mapa_y INTEGER NOT NULL" +
			")";			

			public final static String CREATE_TB_ZAJECIA =
			"CREATE TABLE tb_zajecia (" +
			"	id_zajec INTEGER PRIMARY KEY," +
			"	nazwa_zajec TEXT NOT NULL," +
			"	rodz_id_zajec INTEGER, " +
			"	id_wykladowca TEXT,v" +
			"	FOREIGN KEY (rodz_id_zajec) REFERENCES tb_rodz_zajec(rodz_id)," +
			"	FOREIGN KEY (id_wykladowca) REFERENCES tb_pracownicy(pracownik_id)" +
			")";

			public final static String CREATE_TB_PLAN =
			"CREATE TABLE tb_plan (" +
			"	grupa_id INTEGER NOT NULL,"  +
			"	dzien_tyg_id INTEGER NOT NULL,"  +
			"	godz_id INTEGER NOT NULL,"  +
			"	id_zajec INTEGER NOT NULL,"  +
			"	parzystosc TEXT DEFAULT \"X\","  +
			"	CHECK (parzystosc = \"P\" OR parzystosc = \"N\" OR parzystosc = \"X\"),"  +
			"	FOREIGN KEY (grupa_id) REFERENCES tb_grupy(grupa_id),"  +
			"	FOREIGN KEY (dzien_tyg_id) REFERENCES tb_dni_tyg(nazwa_dnia),"  +
			"	FOREIGN KEY (godz_id) REFERENCES tb_godziny(godz_id),"  +
			"	FOREIGN KEY (id_zajec) REFERENCES tb_godziny(godz_id)"  +
			")";

			public final static String CREATE_TB_PLAN_KONSUL =
			"CREATE TABLE tb_plan_konsul (" +
			"	dzien_tyg_id INTEGER NOT NULL," +
			"	sala_id INTEGER NOT NULL," +
			"	godz_id INTEGER NOT NULL," +
			"	pracownik_id INTEGER NOT NULL," +
			"	FOREIGN KEY (dzien_tyg_id) REFERENCES tb_dni_tyg(nazwa_dnia)," +
			"	FOREIGN KEY (sala_id) REFERENCES tb_sale(sala_id)," +
			"	FOREIGN KEY (godz_id) REFERENCES tb_godziny(godz_id)," +
			"	FOREIGN KEY (pracownik_id) REFERENCES tb_pracownicy(pracownik_id)" +
			")";
			
			public final static String[] TABLE_CREATES_STATEMENTS = {
								CREATE_TB_PRACOWNICY,
								CREATE_TB_RODZ_ZAJEC,
								CREATE_TB_GRUPY,
								CREATE_TB_DNI_TYG,
								CREATE_TB_GODZINY,
								CREATE_TB_SALE,
								CREATE_TB_ZAJECIA,
								CREATE_TB_PLAN,
								CREATE_TB_PLAN_KONSUL,
							};

			public final static String[] TABLE_NAMES = {
								"tb_pracownicy",
								"tb_rodz_zajec",
								"tb_grupy",
								"tb_dni_tyg",
								"tb_godziny",
								"tb_sale",
								"tb_zajecia",
								"tb_plan",
								"tb_plan_konsul"
							};

}


