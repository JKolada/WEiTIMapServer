package org.weiti_map;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class MyControlPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -204928193180502263L;

	private MyDatabase mDatabase;
	private JPanel groupJPanel = new JPanel();
	private JPanel radioJPanel = new JPanel(new MigLayout());
	private JPanel tableTypeJPanel = new JPanel(new MigLayout());
	
	private JTextField insertTypeTextField= new JTextField();
	private JLabel tableTypeLabel = new JLabel("error");
	private JButton printJButton = new JButton("Poka¿");
	
	private JRadioButton showDataRadioButton = new JRadioButton("wyœwietlanie danych");
	private JRadioButton insertRadioButton = new JRadioButton("wprowadzanie danych");

	private String[] petStrings = { "Plan zajêæ", "Zajêcia", "Pracownicy", "Sale" };

	private JComboBox<String> comboBox1 = new JComboBox<String>(petStrings);
	private JComboBox<String> comboBox2 = new JComboBox<String>(); //TODO

	private String comboBox1String = new String("error");
	
    public MyControlPanel(MyDatabase mDB) {
		super();
		mDatabase = mDB;
		configure();
				
	}
    
    private void configure() {  	

    	setOpaque(true);
		showDataRadioButton.setSelected(true);
		
		insertRadioButton.setFont(new Font("Calibri", Font.HANGING_BASELINE , 15));
		showDataRadioButton.setFont(new Font("Calibri", Font.HANGING_BASELINE , 15));
		
		tableTypeLabel.setSize(new Dimension(50, 20));
				
		insertTypeTextField.setOpaque(true);
		insertTypeTextField.setPreferredSize(new Dimension(150, 20) );
		comboBox2.setPreferredSize(new Dimension(100, 25));
		comboBox1.setSelectedIndex(0);
		
		insertRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertRadioButton.setSelected(true);
				showDataRadioButton.setSelected(false);
				insertTypeTextField.setVisible(false);
			}
		});
		showDataRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDataRadioButton.setSelected(true);
				insertRadioButton.setSelected(false);
				insertTypeTextField.setVisible(true);
			}
		});
		
		
		comboBox1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox1String = String.valueOf(comboBox1.getSelectedItem());
				switch (comboBox1String) {
					case "Plan zajêæ":
						
					case "Zajêcia":
						
					case "Pracownicy": 
						
					case "Sale":
						
				}
			}
		});
		
//		comboBox1.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		radioJPanel.add(new JLabel("Wybierz tryb aplikacji:"), "wrap");
		radioJPanel.add(insertRadioButton, "wrap");
		radioJPanel.add(showDataRadioButton);				

		tableTypeJPanel.add(tableTypeLabel, "wrap");
		tableTypeJPanel.add(comboBox2);
		
		groupJPanel.add(radioJPanel);		
		groupJPanel.add(comboBox1);		
		groupJPanel.add(tableTypeJPanel);
		groupJPanel.add(insertTypeTextField);
		groupJPanel.add(printJButton);
		
		add(groupJPanel);
		
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
			
		
//		searchRadioButton 

	}

	
}


