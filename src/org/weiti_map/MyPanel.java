package org.weiti_map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169492090332131771L;
	private MyDatabase mDatabase;
	private JPanel groupJPanel = new JPanel();
    private JTable planTable = new MyJTable();
	private JTextArea groupNameJTextArea = new JTextArea("Nazwa grupy:");
	private JTextField groupNameJTextField = new JTextField("wprowadü nazwÍ");
	private JButton insertJButton = new JButton("Wprowadü");
	private MyControlPanel controlPanel;

	private JTextField logJTextField = new JTextField("Log programu");
	
    public MyPanel(MyDatabase mDB) {
		super();
		mDatabase = mDB;
		controlPanel = new MyControlPanel(mDatabase);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	setOpaque(true);
		
    	
		groupJPanel.setLayout(new GridLayout(1, 2));
		
		groupNameJTextArea.setOpaque(true);
		groupNameJTextArea.setEditable(false);

		groupNameJTextField.setForeground(Color.GRAY);
		
		logJTextField.setEditable(false);
		
		
		
		groupJPanel.add(groupNameJTextArea);
		groupJPanel.add(groupNameJTextField);
		
		add(controlPanel);
		add(groupJPanel);
		

		insertJButton.addActionListener(this);
		insertJButton.setPreferredSize(new Dimension(50, 20));
		insertJButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(new JScrollPane(planTable));
		add(insertJButton);
		add(logJTextField);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
