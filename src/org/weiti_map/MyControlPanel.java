package org.weiti_map;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;

public class MyControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -204928193180502263L;

	private final String[] mainViewColNames = { "Plan zajêæ", "Zajêcia", "Pracownicy", "Sale" };
	
	private MyPanel parentJPanel;
	private MyDatabase mDatabase;
	
	private JPanel groupJPanel; 
	private JPanel radioJPanel; 
	private JPanel tableTypeJPanel; 
	
//	private JTextField insertTypeTextField;
	private JLabel tableTypeLabel; 
	
	private JRadioButton showDataRadioButton; 
	private JRadioButton insertRadioButton; 

	private JComboBox<String> comboBox1; 
	private JComboBox<String> comboBox2; 

	private String comboBox1String = new String("error");
	
    MyControlPanel(MyPanel parent, MyDatabase mDB) {
		super();
		mDatabase = mDB;
		parentJPanel = parent;
		
		groupJPanel = new JPanel();
		radioJPanel = new JPanel(new MigLayout());
		
		tableTypeJPanel = new JPanel(new MigLayout());
		tableTypeLabel = new JLabel("wybierz grupê");
		
		showDataRadioButton = new JRadioButton("wyœwietlanie danych");
		insertRadioButton = new JRadioButton("wprowadzanie danych");
		
		comboBox1 = new JComboBox<String>(mainViewColNames);
		comboBox2 = new JComboBox<String>(mDatabase.getGroupNames());
		
		configure();				
	}
    
    private void configure() {  	

    	setOpaque(true);
		showDataRadioButton.setSelected(true);
		
		insertRadioButton.setFont(new Font("Calibri", Font.HANGING_BASELINE , 15));
		showDataRadioButton.setFont(new Font("Calibri", Font.HANGING_BASELINE , 15));
		
		tableTypeLabel.setSize(new Dimension(50, 20));
					

		comboBox2 = new JComboBox<String>(mDatabase.getGroupNames());
		comboBox2.setPreferredSize(new Dimension(100, 25));
		
		comboBox1.setSelectedIndex(0);
		
		
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
		
		comboBox1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox1String = String.valueOf(comboBox1.getSelectedItem());
				switch (comboBox1String) {
					case "Plan zajêæ":
						tableTypeLabel.setVisible(true);
						comboBox2 = new JComboBox<String>(mDatabase.getGroupNames());
						comboBox2.setVisible(true);
						tableTypeLabel.setText("wybierz grupê");
						break;
					case "Zajêcia":
						tableTypeLabel.setVisible(false);
						comboBox2.setVisible(false);
						if (showDataRadioButton.isSelected()) {
							parentJPanel.showLectures();
						}
						break;
					case "Pracownicy":
						tableTypeLabel.setVisible(false);
						comboBox2.setVisible(false);
						if (showDataRadioButton.isSelected()) {
							parentJPanel.showWorkers();
						}
						break;
					case "Sale":
						tableTypeLabel.setVisible(false);
						comboBox2.setVisible(false);
						if (showDataRadioButton.isSelected()) {
							parentJPanel.showRooms();
						}
						break;
				}
			}
		});
		
		comboBox2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				parentJPanel.showGroupPlan(String.valueOf(comboBox2.getSelectedItem()));				
			}
		});		
		
		radioJPanel.add(new JLabel("Wybierz tryb aplikacji:"), "wrap");
		radioJPanel.add(showDataRadioButton, "wrap");
		radioJPanel.add(insertRadioButton);				

		tableTypeJPanel.add(tableTypeLabel, "wrap");
		tableTypeJPanel.add(comboBox2);
		
		groupJPanel.add(radioJPanel);		
		groupJPanel.add(comboBox1);		
		groupJPanel.add(tableTypeJPanel);
		
		add(groupJPanel);
		
    }
    
	
}


