
public final class MyDatabaseUtilities {	
	
	// 'CREATE TABLES' STATEMENTS // // // // // // // //
	
	private final static String CREATE_TB_PRACOWNICY =
	"CREATE TABLE IF NOT EXISTS tb_pracownicy (" +
	"	pracownik_id INTEGER PRIMARY KEY," +
	"	imie TEXT NOT NULL," +
	"	nazwisko TEXT NOT NULL" +
	")";

	private final static String CREATE_TB_GRUPY =
	"CREATE TABLE IF NOT EXISTS tb_grupy (" +
	"	grupa_id INTEGER PRIMARY KEY," +
	"	nazwa_grupy TEXT NOT NULL UNIQUE" +
	")";

	private final static String CREATE_TB_DNI_TYG =
	"CREATE TABLE IF NOT EXISTS tb_dni_tyg (" +
	"	dzien_tyg_id INTEGER PRIMARY KEY," +
	"	nazwa_dnia TEXT NOT NULL UNIQUE" +
	")";

	private final static String CREATE_TB_GODZINY =
	"CREATE TABLE IF NOT EXISTS tb_godziny (" +
	"	godz_id INTEGER PRIMARY KEY," +
	"	godziny TEXT NOT NULL" +
	")";

	private final static String CREATE_TB_SALE =
	"CREATE TABLE IF NOT EXISTS tb_sale (" +
	"	sala_id INTEGER PRIMARY KEY," +
	"	nazwa_sali TEXT NOT NULL UNIQUE, " +
	"	pietro_sali INTEGER NOT NULL," +
	"	mapa_x INTEGER NOT NULL," +
	"	mapa_y INTEGER NOT NULL" +
	")";			

	private final static String CREATE_TB_ZAJECIA =
	"CREATE TABLE IF NOT EXISTS tb_zajecia (" +
	"	id_zajec INTEGER PRIMARY KEY," +
	"	skrot_nazwy_zajec TEXT NOT NULL," +
	"	nazwa_zajec TEXT NOT NULL," +
	"	id_wykladowca TEXT," +
	"	FOREIGN KEY (id_wykladowca) REFERENCES tb_pracownicy(pracownik_id)" +
	")";

	private final static String CREATE_TB_PLAN =
	"CREATE TABLE IF NOT EXISTS tb_plan (" +
	"	grupa_id INTEGER NOT NULL,"  +
	"	dzien_tyg_id INTEGER NOT NULL,"  +
	"	godz_id INTEGER NOT NULL,"  +
	"	id_zajec INTEGER NOT NULL,"  +	
	"	rodz_zajec VARCHAR(1) NOT NULL DEFAULT 'X',"  +	
	"	sala_id INTEGER NOT NULL," +
	"	parzystosc VARCHAR(1) NOT NULL DEFAULT 'X',"  +
	"	CHECK (parzystosc = 'P' OR parzystosc = 'N' OR parzystosc = 'X'),"  +	
	"	CHECK (rodz_zajec IN ('W', 'L', 'C', 'R')),"  +
	"	FOREIGN KEY (grupa_id) REFERENCES tb_grupy(grupa_id),"  +
	"	FOREIGN KEY (sala_id) REFERENCES tb_sale(sala_id)," +
	"	FOREIGN KEY (dzien_tyg_id) REFERENCES tb_dni_tyg(dzien_tyg_id),"  +
	"	FOREIGN KEY (godz_id) REFERENCES tb_godziny(godz_id),"  +
	"	FOREIGN KEY (id_zajec) REFERENCES tb_zajecia(id_zajec)"  +
	")";

	private final static String CREATE_TB_PLAN_KONSUL =
	"CREATE TABLE IF NOT EXISTS tb_plan_konsul (" +
	"	dzien_tyg_id INTEGER NOT NULL," +
	"	sala_id INTEGER NOT NULL," +
	"	godz_id INTEGER NOT NULL," +
	"	pracownik_id INTEGER NOT NULL," +
	"	FOREIGN KEY (dzien_tyg_id) REFERENCES tb_dni_tyg(dzien_tyg_id)," +
	"	FOREIGN KEY (sala_id) REFERENCES tb_sale(sala_id)," +
	"	FOREIGN KEY (godz_id) REFERENCES tb_godziny(godz_id)," +
	"	FOREIGN KEY (pracownik_id) REFERENCES tb_pracownicy(pracownik_id)" +
	")";
	
	// INSERTS // // // // // // // // // // // // // // // // // // // //  // // //
			
	public final static String TB_DNI_TYG_INSERTS = 
	"INSERT INTO tb_dni_tyg (nazwa_dnia) VALUES " +
	"('poniedzia³ek'), " +
	"('wtorek')," +
	"('œroda'), " +
	"('czwartek'), " +
	"('pi¹tek')";			

 	public final static String TB_GODZINY_INSERTS = 
	"INSERT INTO tb_godziny (godz_id, godziny) VALUES " +
	 "(8, '08:15-09:00'), " + 	
	 "(9, '09:15-10:00'), " +	
	 "(10,'10:15-11:00'), " +	
	 "(11,'11:15-12:00'), " +	
	 "(12,'12:15-13:00'), " +	
	 "(13,'13:15-14:00'), " +	
	 "(14,'14:15-15:00'), " +	
	 "(15,'15:15-16:00'), " +	
	 "(16,'16:15-17:00'), " +	
	 "(17,'17:15-18:00'), " +	
	 "(18,'18:15-19:00'), " +	
	 "(19,'19:15-20:00')";	

			// TEST 'INSERT INTO' STATEMENTS
			
	public final static String TB_GRUPY_TEST_INSERT =
	"INSERT INTO tb_grupy (nazwa_grupy) VALUES ('1E1')";

	public final static String TB_ZAJECIA_TEST_INSERTS =
	"INSERT INTO tb_zajecia (skrot_nazwy_zajec, nazwa_zajec) VALUES " +
	"('ALGTM', 'Algebra i teoria mnogoœci'), " +
	"('ANL1', 'Analiza 1'), " +
	"('ORM', 'Orientacja - M'), " +
	"('PPOM', 'Podstawy pomiarów'), " +
	"('PRAWO', 'Podstawy prawa - ochrona w³asnoœci intelektualnej'), " +
	"('PRM', 'Podstawy programowania (M)'), " +
	"('ULOG', 'Uk³ady logiczne'), " +
	"('WF1', 'Wychowanie fizyczne'), " +
	
	"('WFI', 'Wstêp do fizyki')";

	public final static String TB_SALE_TEST_INSERT =
	"INSERT INTO tb_sale (nazwa_sali, pietro_sali, mapa_x, mapa_y) VALUES" +
	"('105-AR', '1', 0, 0)," +
	"('011', '0', 0, 0)," +
	"('106', '1', 0, 0)," +
	"('DS202', '2', 0, 0)," +
	"('117', '1', 0, 0)," +
	"('120', '1', 0, 0)," +
	"('161', '0', 0, 0)," +
	"('118-AL', '0', 0, 0)";	

	public final static String TB_PLAN_TEST_INS_0 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, rodz_zajec, sala_id, parzystosc) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, 'W', e.sala_id, \'N\' " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id = 8 " +
	"AND d.nazwa_zajec = \'WFI\' AND e.nazwa_sali = \'105-AR\'";
	
	private final static String TB_PLAN_TEST_INS_1 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id, parzystosc) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id, \'N\' " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  9 " +
	"AND d.nazwa_zajec = \'WFI\' AND e.nazwa_sali = \'105-AR\'";
	

	private final static String TB_PLAN_TEST_INS_2 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  10 " +
	"AND d.nazwa_zajec = \'PRM\' AND e.nazwa_sali = \'011\'";
	
	private final static String TB_PLAN_TEST_INS_3 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  11 " +
	"AND d.nazwa_zajec = \'PRM\' AND e.nazwa_sali = \'011\'";
	
	private final static String TB_PLAN_TEST_INS_4 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id, parzystosc) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id, \'N\'" +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  12 " +
	"AND d.nazwa_zajec = \'WFI\' AND e.nazwa_sali = \'106\'";
	
	private final static String TB_PLAN_TEST_INS_5 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id, parzystosc) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id, \'N\' " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  13 " +
	"AND d.nazwa_zajec = \'WFI\' AND e.nazwa_sali = \'106\'";
	
	private final static String TB_PLAN_TEST_INS_6 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id, parzystosc) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id, \'P\'" +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  12 " +
	"AND d.nazwa_zajec = \'ULOG\' AND e.nazwa_sali = \'106\'";
	
	private final static String TB_PLAN_TEST_INS_7 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id, parzystosc) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id, \'P\' " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  13 " +
	"AND d.nazwa_zajec = \'ULOG\' AND e.nazwa_sali = \'106\'";
	
	private final static String TB_PLAN_TEST_INS_8 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  14 " +
	"AND d.nazwa_zajec = \'PPOM\' AND e.nazwa_sali = \'DS202\'";

	private final static String TB_PLAN_TEST_INS_9 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  15 " +
	"AND d.nazwa_zajec = \'PPOM\' AND e.nazwa_sali = \'DS202\'";

	private final static String TB_PLAN_TEST_INS_10 = 
	"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id) " +
	"SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id " +
	"FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e " +
	"WHERE	a.nazwa_grupy = \'1E1\' " +
	"AND b.nazwa_dnia = \'poniedzia³ek\' " +
	"AND c.godz_id =  16 " +
	"AND d.nazwa_zajec = \'PPOM\' AND e.nazwa_sali = \'DS202\'";




	public final static String[] TB_PLAN_TEST_INS_lIST = {
			"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id)  SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id  FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e  WHERE a.nazwa_grupy = \'1E1\'  AND b.nazwa_dnia = \'poniedzia³ek\'  AND c.godz_id =  09  AND d.nazwa_zajec = \'WFI\' AND e.nazwa_sali = \'105-AR\'",
			"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id)  SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id  FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e  WHERE a.nazwa_grupy = \'1E1\'  AND b.nazwa_dnia = \'poniedzia³ek\'  AND c.godz_id =  \15ND d.nazwa_zajec = \'ULOG\' AND e.nazwa_sali = \'105-AR\'",
			"INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, sala_id)  SELECT a.grupa_id, b.dzien_tyg_id, c.godz_id, d.id_zajec, e.sala_id  FROM tb_grupy a, tb_dni_tyg b, tb_godziny c, tb_zajecia d, tb_sale e  WHERE a.nazwa_grupy = \'1E1\'  AND b.nazwa_dnia = \'poniedzia³ek\'  AND c.godz_id =  09  AND d.nazwa_zajec = \'WFI\' AND e.nazwa_sali = \'105-AR\'"
			};



	public final static String[] TABLE_CREATES_STATEMENTS = {
								CREATE_TB_PRACOWNICY,
								CREATE_TB_GRUPY,
								CREATE_TB_DNI_TYG,
								CREATE_TB_GODZINY,
								CREATE_TB_SALE,
								CREATE_TB_ZAJECIA,
								CREATE_TB_PLAN,
								CREATE_TB_PLAN_KONSUL,
							};

	public final static String[] INSERT_INTO_STATEMENT_LIST = {
								TB_DNI_TYG_INSERTS,
								TB_GODZINY_INSERTS,
								TB_GRUPY_TEST_INSERT, 
								TB_ZAJECIA_TEST_INSERTS, 
								TB_SALE_TEST_INSERT, 
								TB_PLAN_TEST_INS_0,
								TB_PLAN_TEST_INS_1,
								TB_PLAN_TEST_INS_2,
								TB_PLAN_TEST_INS_3,
								TB_PLAN_TEST_INS_4,
								TB_PLAN_TEST_INS_5,
								TB_PLAN_TEST_INS_6,
								TB_PLAN_TEST_INS_7,
								TB_PLAN_TEST_INS_8,
								TB_PLAN_TEST_INS_9,
								TB_PLAN_TEST_INS_10
							};

	public final static String[] TABLE_NAMES = {
								"tb_pracownicy",
								"tb_grupy",
								"tb_dni_tyg",
								"tb_godziny",
								"tb_sale",
								"tb_zajecia",
								"tb_plan",
								"tb_plan_konsul"
							};

	public final static String[] INSERT_STATEMENT_NAMES = {
								"TB_DNI_TYG_INSERTS",
								"TB_GODZINY_INSERTS",
								"TB_GRUPY_TEST_INSERT", 
								"TB_ZAJECIA_TEST_INSERTS", 
								"TB_SALE_TEST_INSERT", 
								"TB_PLAN_TEST_INS_0",
								"TB_PLAN_TEST_INS_1",
								"TB_PLAN_TEST_INS_2",
								"TB_PLAN_TEST_INS_3",
								"TB_PLAN_TEST_INS_4",
								"TB_PLAN_TEST_INS_5",
								"TB_PLAN_TEST_INS_6",
								"TB_PLAN_TEST_INS_7",
								"TB_PLAN_TEST_INS_8",
								"TB_PLAN_TEST_INS_9",
								"TB_PLAN_TEST_INS_10"
							};
}

/*

//ALGTM		'Algebra i teoria mnogoœci'								W: Cz 8-10, C: Wt 8-10, Wt 8-10, Pn 14-16, Pn 12-14,
//ANL1		'Analiza 1'												W: Œr 12-15, C: Wt 10-12, Wt 12-14, Pt 10-12, Wt 10-12,
//ORM		'Orientacja - M'										W: Œr 15-17,
//PPOM		'Podstawy pomiarów'										W: Œr 10-12, R: Pt 8-10, L: Wt 12-15, Wt 9-12, Pn 11-14, Pn 14-17,
//PRAWO		'Podstawy prawa - ochrona w³asnoœci intelektualnej'		W: Œr 8-10,
//PRM		'Podstawy programowania (M)'							W: Cz 12-14, L: Pn 14-16, Pn 12-14, Pn 10-12, Pn 16-18,

//WF1		'Wychowanie fizyczne'									C: Pt 12-14,
//WFI		'Wstêp do fizyki'										W: PnN 8-10, C: PnN 10-12, PnN 12-14, WtN 14-16,


*/

