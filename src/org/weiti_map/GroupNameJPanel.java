package org.weiti_map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class GroupNameJPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private GroupComboBox comboBox2; 
	private MyDatabase mDB;
	private MyPanel grandparentPanel;
	private JLabel tableTypeLabel;
	
	public GroupNameJPanel(MyDatabase DB, MyPanel gparentJPanel) {
		super(new MigLayout());		
		mDB = DB;
		grandparentPanel = gparentJPanel;
		tableTypeLabel = new JLabel("Wybierz grupê:");
		comboBox2 = new GroupComboBox(grandparentPanel, mDB.getGroupNames());

		add(tableTypeLabel, "wrap");
		add(comboBox2);
	}
	
	public void restart() {		
		comboBox2 = new GroupComboBox(grandparentPanel, mDB.getGroupNames());		
		removeAll();
		add(tableTypeLabel, "wrap");
		add(comboBox2);		
		setVisible(true);	
		revalidate();
		repaint();
	}
}
