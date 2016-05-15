package org.weiti_map.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.sqlite.SQLiteDataSource;

import com.example.kuba.weitimap.db.GroupPlanObject;
import com.example.kuba.weitimap.db.LectureObj;

public class MyDatabase extends SQLiteDataSource {

	private Boolean isSet = false;
	private Connection mConnection = null;
	private int db_errors_num = 0;

	public MyDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");

		} catch (SQLException e) {
			db_errors_num++;
			e.printStackTrace();
			db_errors_num++;
		}

		resetDB();
		checkTables(true);
		setDatabase();
		if (db_errors_num == 0) {
			isSet = true; // TODO
		}

	}

	public void close() {
		if (mConnection != null) {
			try {
				mConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	Boolean isSet() {
		return isSet;
	}

	private int setDatabase() {

		int k = 0;
		for (String i : MyDatabaseUtilities.CREATE_TABLE_STATEMENTS) {

			try {
				mConnection.createStatement().executeUpdate(i);
				System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[k]
						+ " succesfully created or it exists.");
			} catch (SQLException e) {
				System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[k]
						+ " creation failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++;
			}
			k++;
		}

		k = 0;
		for (String i : MyDatabaseUtilities.CREATE_VIEW_STATEMENTS) {

			try {
				mConnection.createStatement().executeUpdate(i);
				System.out.println("View " + MyDatabaseUtilities.VIEW_NAMES[k]
						+ " succesfully created or it exists.");
			} catch (SQLException e) {
				System.out.println("View " + MyDatabaseUtilities.VIEW_NAMES[k]
						+ " creation failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++;
			}
			k++;
		}

		k = 0;
		for (String i : MyDatabaseUtilities.INSERT_INTO_STATEMENT_LIST) {
			try {
				mConnection.createStatement().executeUpdate(i);
				System.out.println(
						"\'" + MyDatabaseUtilities.INSERT_STATEMENT_NAMES[k]
								+ "\' insert statement succesfully executed.");
			} catch (SQLException e) {
				System.out.println(
						"\'" + MyDatabaseUtilities.INSERT_STATEMENT_NAMES[k]
								+ "\' insert statement failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++;
			}
			k++;
			if (k >= MyDatabaseUtilities.INSERT_INTO_STATEMENT_LIST.length) {
				break;
			}
		}
		if (db_errors_num == 0) {
			System.out.println("Database is set properly.\n");
		} else {
			System.out.println(
					"Database has initialization failures. Number of SQLException caught: "
							+ db_errors_num);
		}

		try {
			if (!mConnection.isClosed())
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			db_errors_num++;
		}
		return 0;
	}

	private int checkTables(boolean reset) {
		return checkTables(reset, "ALL");
	}

	private int checkTables(boolean reset, String table_name_to_drop) {
		ResultSet result;
		int table_num = 0;
		String table_name_temp;
		try {
			result = mConnection.createStatement().executeQuery(
					"SELECT name FROM sqlite_master WHERE type='table' ORDER BY name");
			while (result.next()) {
				table_name_temp = result.getString("name");
				System.out.println(table_name_temp + " exists.");
				table_num++;
				if (reset = true) {
					if ((table_name_to_drop.equals(table_name_temp))
							|| (table_name_to_drop.equals("ALL")))
						mConnection.createStatement()
								.execute("DROP TABLE " + table_name_temp);
					System.out.println(table_name_temp + " has been dropped.");
					table_num--;
				}
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db_errors_num++;
		}

		if (table_num > 0) {
			System.out.println("Error: Some tables still exist.");
		}
		return table_num;

	}

	private void resetDB() {
		for (String i : MyDatabaseUtilities.TABLE_NAMES) {
			try {
				mConnection.createStatement().execute("DROP TABLE " + i);
				System.out.println(
						"Table '" + i + "' has been dropped succesfully");
			} catch (SQLException e) {
				System.out.println("Error: SQL Statement 'DROP TABLE '" + i
						+ "' failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++;
			}
		}

		for (String i : MyDatabaseUtilities.VIEW_NAMES) {
			try {
				mConnection.createStatement().execute("DROP VIEW " + i);
				System.out.println(
						"View '" + i + "' has been dropped succesfully");
			} catch (SQLException e) {
				System.out.println("Error: SQL Statement 'DROP VIEW '" + i
						+ "' failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++;
			}
		}

	}

	public String[] getGroupNames() {
		String query = "SELECT nazwa_grupy FROM tb_grupy";
		List<String> nazwy_grup = new ArrayList<String>();
		try {
			ResultSet nazwy_grup_rs = mConnection.createStatement()
					.executeQuery(query);
			while (nazwy_grup_rs.next()) {
				nazwy_grup.add(nazwy_grup_rs.getString("nazwa_grupy"));
			}
			nazwy_grup_rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nazwy_grup.toArray(new String[nazwy_grup.size()]);
	}

	public int checkGroupNameEx(String groupName) {
		String query = "SELECT grupa_id FROM tb_grupy WHERE nazwa_grupy = '"
				+ groupName + "'";
		String groupId;
		try {
			ResultSet groupIdRS = mConnection.createStatement()
					.executeQuery(query);
			groupIdRS.next();
			if (!groupIdRS.isClosed()) {
				groupId = groupIdRS.getString("grupa_id");
				groupIdRS.close();
				return Integer.parseInt(groupId);
			} else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public GroupPlanObject getGroupPlanObject(String group_name) {
		String query = "SELECT * FROM vw_plan WHERE nazwa_grupy = '"
				+ group_name + "'";
		GroupPlanObject groupObject = null;
		// List<String> pojedyncze_zajecia = new ArrayList<String>();
		try {
			ResultSet zajeciaRS = mConnection.createStatement()
					.executeQuery(query);
			groupObject = new GroupPlanObject(group_name);
			if (!zajeciaRS.isClosed()) {
				while (zajeciaRS.next()) {
					List<String> pojedyncze_zajecia = new ArrayList<String>();
					for (int k = 2; k <= MyDatabaseUtilities.PLAN_VIEW_COL_NAMES.length; k++) {
						pojedyncze_zajecia.add(zajeciaRS.getString(k));
						// System.out.println(zajeciaRS.getString(k)); //TO
						// DELETE
					}
					// String[] temp = new LectureObj((ArrayList<String>)
					// pojedyncze_zajecia).getLectureData(); //TO DELETE
					// System.out.println(temp[0] + " " + temp[1] + " " +
					// temp[2] + " " + temp[3] + " " + temp[4] + " " + temp[5]);
					groupObject.add(new LectureObj(
							(ArrayList<String>) pojedyncze_zajecia));
				}

			} else {
				System.out.println("Result set is null"); // TO DELETE
			}
			zajeciaRS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			File file = new File("" + group_name + ".xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(GroupPlanObject.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(groupObject, file);
			// jaxbMarshaller.marshal(groupObject, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return groupObject;
	}

	public WorkersTableObject getWorkersTableObject() {
		String query = "SELECT * FROM tb_pracownicy ORDER BY 1";
		WorkersTableObject workersTable = null;
		try {
			ResultSet pracownicyRS = mConnection.createStatement()
					.executeQuery(query);
			workersTable = new WorkersTableObject();
			if (!pracownicyRS.isClosed()) {
				while (pracownicyRS.next()) {
					List<String> pojedynczy_pracownik = new ArrayList<String>();
					for (int k = 0; k < 3; k++) {
						pojedynczy_pracownik.add(pracownicyRS.getString(k + 1));
						// System.out.println(pracownicyRS.getString(k+1)); //TO
						// DELETE
					}
					workersTable.add(new WorkerObj(
							(ArrayList<String>) pojedynczy_pracownik));
				}

			} else {
				System.out.println("Result set is null"); // TO DELETE
			}
			pracownicyRS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workersTable;
	}
	//
	// RoomsTableObject getRoomsTableObject() {
	// String query = "SELECT * FROM tb_sale ORDER BY 1";
	// RoomsTableObject roomsTable = null;
	// try {
	// ResultSet saleRS = mConnection.createStatement().executeQuery(query);
	// roomsTable = new RoomsTableObject();
	// if (!saleRS.isClosed()) {
	// while (saleRS.next()) {
	// List<String> pojedyncza_sala = new ArrayList<String>();
	// for (int k = 0; k < 5; k++) {
	// pojedyncza_sala.add(saleRS.getString(k+1));
	//// System.out.println(saleRS.getString(k+1)); //TO DELETE
	// }
	// roomsTable.add(new RoomObj((ArrayList<String>) pojedyncza_sala));
	// }
	//
	// } else {
	// System.out.println("Result set is null"); //TO DELETE
	// }
	// saleRS.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return roomsTable;
	// }

	public CustomTableObject<RoomObj> getRoomsTableObject() {
		String query = "SELECT * FROM tb_sale ORDER BY 1";
		CustomTableObject<RoomObj> roomsTable = null;
		try {
			ResultSet saleRS = mConnection.createStatement()
					.executeQuery(query);
			roomsTable = new CustomTableObject<RoomObj>(5);
			if (!saleRS.isClosed()) {
				while (saleRS.next()) {
					List<String> pojedyncza_sala = new ArrayList<String>();
					for (int k = 0; k < 5; k++) {
						pojedyncza_sala.add(saleRS.getString(k + 1));
						// System.out.println(saleRS.getString(k+1)); //TO
						// DELETE
					}
					roomsTable.add(
							new RoomObj((ArrayList<String>) pojedyncza_sala));
				}

			} else {
				System.out.println("Result set is null"); // TO DELETE
			}
			saleRS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roomsTable;
	}

	public void addGroup(String groupName) {
		String query = "INSERT INTO tb_grupy (nazwa_grupy) VALUES ('"
				+ groupName + "')";
		executeQuery(query);
	}

	public void removeGroup(String groupName) {
		String query = "DELETE FROM tb_plan WHERE grupa_id = (SELECT grupa_id FROM tb_grupy WHERE nazwa_grupy = '"
				+ groupName + "')";
		executeQuery(query);
		query = "DELETE FROM tb_grupy WHERE nazwa_grupy = '" + groupName + "'";
		executeQuery(query);
	}

	public void updatePlanCell(int row, int col, GroupPlanObject planObject,
			char p, String cellValue) {

		if (cellValue == null) { //
			String nazwa_grupy = planObject.getGroupName();

			String query = "DELETE FROM tb_plan WHERE 1=1 "
					+ "AND grupa_id = (SELECT grupa_id FROM tb_grupy WHERE nazwa_grupy = '"
					+ nazwa_grupy + "') " + "AND dzien_tyg_id  = " + (col) + " "
					+ "AND godz_id = " + (row + 8) + " "
					+ "AND parzystosc IN ('" + p + "', 'X')";
			executeQuery(query);

		} else {
			Pattern pattern = Pattern
					.compile("([A-Z]+)[ ]([WLCR])[ ]([0-9A-Z-]+)");
			Matcher m = pattern.matcher(cellValue);
			m.matches();

			String nazwa_zajec = m.group(1);
			String rodzaj_zajec = m.group(2);
			String nazwa_sali = m.group(3);

			String query = "INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, rodz_zajec, sala_id, parzystosc) "
					+ "SELECT a.grupa_id, " + (col) + ", " + (row + 8)
					+ ", d.id_zajec, '" + rodzaj_zajec + "', e.sala_id, '" + p
					+ "' " + "FROM tb_grupy a, tb_zajecia d, tb_sale e "
					+ "WHERE a.nazwa_grupy = '" + planObject.getGroupName()
					+ "' " + "AND d.skrot_nazwy_zajec = '" + nazwa_zajec + "' "
					+ "AND e.nazwa_sali = '" + nazwa_sali + "'";

			executeQuery(query);
		}
		;
	}

	public void insertGroupObj(GroupPlanObject groupPlan) {
		String groupName = groupPlan.getGroupName();
		removeGroup(groupName);
		addGroup(groupName);

		ArrayList<LectureObj> lectures = groupPlan.getLectureArray();

		String[] data = new String[6];
		for (int i = 0; i < lectures.size(); i++) {
			data = (lectures.get(i)).getLectureData();
			String query = "INSERT INTO tb_plan (grupa_id, dzien_tyg_id, godz_id, id_zajec, rodz_zajec, sala_id, parzystosc) "
					+ "SELECT a.grupa_id, b.dzien_tyg_id, " + data[2]
					+ ", d.id_zajec, '" + data[5] + "', e.sala_id, '" + data[3]
					+ "' "
					+ "FROM tb_grupy a, tb_dni_tyg b, tb_zajecia d, tb_sale e "
					+ "WHERE a.nazwa_grupy = '" + groupName + "' "
					+ "AND d.skrot_nazwy_zajec = '" + data[4] + "' "
					+ "AND e.nazwa_sali = '" + data[0] + "' "
					+ "AND b.nazwa_dnia = '" + data[1] + "'";
			executeQuery(query);
		}
	}

	private void executeQuery(String query) {
		try {
			mConnection.createStatement().executeUpdate(query);
			System.out.println("\'" + query + "\' succesfully executed.");
		} catch (SQLException e) {
			System.out.println("\'" + query + "\' failed. StackTrace:");
			e.printStackTrace();
		}
	}

	// LecturesTableObject getLectureTableObject() { //TODO
	// String query = "SELECT * FROM VW_LECTURES ORDER BY 1";
	// LecturesTableObject lecturesTable = null;
	// try {
	// ResultSet lecturesRS = mConnection.createStatement().executeQuery(query);
	// lecturesTable = new LecturesTableObject();
	// if (!lecturesRS.isClosed()) {
	// while (lecturesRS.next()) {
	// List<String> pojedyncze_zajecia = new ArrayList<String>();
	// for (int k = 0; k < 6; k++) {
	// pojedyncze_zajecia.add(lecturesRS.getString(k+1));
	//// System.out.println(lecturesRS.getString(k+1)); //TO DELETE
	// }
	// lecturesTable.add(new RoomObj((ArrayList<String>) pojedyncze_zajecia );
	// }
	//
	// } else {
	// System.out.println("Result set is null"); //TO DELETE
	// }
	// lecturesRS.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return lecturesTable;
	// }

	// KonsulTableObject getKonsulTableObject() { //TODO
	// String query = "SELECT * FROM VW_KONSUL ORDER BY 1";
	// KonsulTableObject konsulTable = null;
	// try {
	// ResultSet konsulRS = mConnection.createStatement().executeQuery(query);
	// konsulTable = new KonsulTableObject();
	// if (!konsulRS.isClosed()) {
	// while (konsulRS.next()) {
	// List<String> pojedyncze_konsul = new ArrayList<String>();
	// for (int k = 0; k < 6; k++) {
	// pojedyncze_konsul.add(konsulRS.getString(k+1));
	//// System.out.println(konsulRS.getString(k+1)); //TO DELETE
	// }
	// konsulTable.add(new KonsulObj((ArrayList<String>) pojedyncze_konsul );
	// }
	//
	// } else {
	// System.out.println("Result set is null"); //TO DELETE
	// }
	// konsulRS.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return konsulTable;
	// }

}