package org.weiti_map;

import javax.swing.JPanel;

import org.weiti_map.db.MyDatabase;

import net.miginfocom.swing.MigLayout;

public class MyControlPanel extends JPanel {
	private static final long serialVersionUID = -204928193180502263L;

	private final String[] viewsNames = { "Plan zajêæ", "Zajêcia", "Pracownicy", "Sale" };
	
	private MyPanel parentJPanel;
	private MyDatabase mDatabase;
	
	private JPanel groupJPanel; 
	
	private GroupNameJPanel tableTypeJPanel; 	
	private MainViewsComboBox comboBox1; 
	
//	private GroupComboBox comboBox2; 	
		
    MyControlPanel(MyPanel parent, MyDatabase mDB) {
		super();
		mDatabase = mDB;
		parentJPanel = parent;		
		configure();				
	}   

   
    private void configure() {      	
		
		groupJPanel = new JPanel(new MigLayout());
		
		tableTypeJPanel = new GroupNameJPanel(mDatabase, parentJPanel);
		comboBox1 = new MainViewsComboBox(parentJPanel, this, tableTypeJPanel, viewsNames);
		
		groupJPanel.add(comboBox1);		
		groupJPanel.add(tableTypeJPanel);
		
		add(groupJPanel);
		
    }    
	
}


