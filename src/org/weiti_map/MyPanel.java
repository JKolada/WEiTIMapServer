package org.weiti_map;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169492090332131771L;
	private MyDatabase mDatabase;
	private MyTablePanel tablePanelP = new MyTablePanel('P');
	private MyTablePanel tablePanelN = new MyTablePanel('N');
	private JTextArea groupNameJTextArea = new JTextArea("Nazwa grupy:");
	private JTextField groupNameJTextField = new JTextField("wprowadü nazwƒô");
	private JButton insertJButton = new JButton("Wprowadü dane");
	private MyControlPanel controlPanel;

	private JTextField logJTextField = new JTextField("Log programu");
	
    public MyPanel(MyDatabase mDB) {
		super();
		mDatabase = mDB;
		controlPanel = new MyControlPanel(this, mDatabase);

		LC layoutConstraints = new LC();
		layoutConstraints.setFillX(true);
		setLayout(new MigLayout(layoutConstraints));
//    	setOpaque(true);
		
		groupNameJTextArea.setOpaque(true);
		groupNameJTextArea.setEditable(false);

		groupNameJTextField.setForeground(Color.GRAY);

		logJTextField.setEditable(false);
		
		showGroupPlan("1E1");
//		mDB.temp();			
		
//		insertJButton.addActionListener(this);
//		insertJButton.setPreferredSize(new Dimension(50, 20));
//		insertJButton.setHorizontalAlignment(SwingConstants.CENTER);
		

		add(controlPanel, "wrap");
//		add(groupJPanel);
		add(tablePanelP);
		add(tablePanelN, "wrap");
		add(insertJButton, "wrap");
		add(logJTextField);
		
		
    }

    static public void showWorkers() {
    	
    }
    
   
    public void showLectures(String group_name) {

//		Map<String, String> zajecia_data = new HashMap<String, String>();
//		String query = "SELECT nazwa_zajec, id_wykladowcy FROM tb_zajecia";	
//		String query2 = "SELECT imie, nazwisko FROM tb_pracownicy WHERE pracownik_id = ";
//		String nazwa_zajec, skrot_nazwy_zajec, id_wykladowcy;
//		try {					
//			ResultSet zajecia_rs = mStatement.executeQuery(query);
//		    while (zajecia_rs.next() ) {	    	
//		    	nazwa_zajec = zajecia_rs.getString("nazwa_zajec");
//		    	skrot_nazwy_zajec = zajecia_rs.getString("skrot_nazwy_zajec");		    	
//		    	id_wykladowcy = zajecia_rs.getString("id_wykladowcy");	
//				ResultSet pracownicy_rs = mStatement.executeQuery(query2 + id_wykladowcy);
//				pracownicy_rs.getString("imie"));
//	    	}		    
//		    
//	    } catch (SQLException e) {
//			e.printStackTrace();
//		}
    	
    }
    
    public void showGroupPlan(String groupName) {
    	GroupPlanObject plan = mDatabase.getGroupPlanObject(groupName);
    	System.out.println(groupName);
//    	tablePanelP.setGroupPlan(plan);
    	tablePanelN.setGroupPlan(plan);    	   	    	
    }
    
    

}
