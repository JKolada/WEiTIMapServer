package org.weiti_map;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;

public class MyControlPanel extends JPanel {
	private static final long serialVersionUID = -204928193180502263L;

	private final String[] viewsNames = { "Plan zajêæ", "Zajêcia", "Pracownicy", "Sale" };
	
	private MyPanel parentJPanel;
	private MyDatabase mDatabase;
	
	private JPanel groupJPanel; 
	private JPanel radioJPanel; 
	private GroupNameJPanel tableTypeJPanel; 	
	
	private JRadioButton showDataRadioButton; 
	private JRadioButton insertRadioButton; 

	private MainViewsComboBox comboBox1; 
	private GroupComboBox comboBox2; 
	
    MyControlPanel(MyPanel parent, MyDatabase mDB) {
		super();
		mDatabase = mDB;
		parentJPanel = parent;
		
		groupJPanel = new JPanel();
		radioJPanel = new JPanel(new MigLayout());
		
		tableTypeJPanel = new GroupNameJPanel(mDatabase, parentJPanel);
		
		showDataRadioButton = new JRadioButton("wyœwietlanie danych");
		insertRadioButton = new JRadioButton("wprowadzanie danych");
		
		comboBox1 = new MainViewsComboBox(parentJPanel, this, tableTypeJPanel, viewsNames);
//		comboBox1.setSelectedIndex(0);	
		comboBox2 = new GroupComboBox(parent, mDatabase.getGroupNames());
		
		configure();				
	}
    
   public JRadioButton getShowDataRadioButton() {
	   return showDataRadioButton;
   }
   
   public JRadioButton getInsertDataRadioButton() {
	   return insertRadioButton;
   }
   
    private void configure() {  	
//    	setOpaque(true);
		showDataRadioButton.setSelected(true);
		
		insertRadioButton.setFont(new Font("Calibri", Font.HANGING_BASELINE , 15));
		showDataRadioButton.setFont(new Font("Calibri", Font.HANGING_BASELINE , 15));							
	
		
		insertRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertRadioButton.setSelected(true);
				showDataRadioButton.setSelected(false);
				tableTypeJPanel.setVisible(false);
				parentJPanel.insertRadioButtonClicked();
				comboBox1.setSelectedIndex(0);
			}
		});
		showDataRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDataRadioButton.setSelected(true);
				insertRadioButton.setSelected(false);
				tableTypeJPanel.setVisible(true);
				parentJPanel.showRadioButtonClicked();
				comboBox1.setSelectedIndex(0);
			}
		});	
		
		radioJPanel.add(new JLabel("Wybierz tryb aplikacji:"), "wrap");
		radioJPanel.add(showDataRadioButton, "wrap");
		radioJPanel.add(insertRadioButton);				

		groupJPanel.add(radioJPanel);		
		groupJPanel.add(comboBox1);		
		groupJPanel.add(tableTypeJPanel);
		
		add(radioJPanel);
		add(groupJPanel);
		
    }
    
    
    
	
}


