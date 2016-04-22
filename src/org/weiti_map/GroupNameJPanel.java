package org.weiti_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.weiti_map.db.MyDatabase;

import net.miginfocom.swing.MigLayout;

public class GroupNameJPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private GroupComboBox comboBox; 
	private MyDatabase mDB;
	private MyPanel grandparentPanel;
	private JLabel tableTypeLabel;
	private JButton addGroupBtn;
	private JButton removeGroupBtn;
	private JTextField newGroupName;
	
	private ListenForAction classListener;
	
	public GroupNameJPanel(MyDatabase DB, MyPanel gparentJPanel) {
		super(new MigLayout());		
		mDB = DB;
		grandparentPanel = gparentJPanel;
		
		classListener = new ListenForAction();
		addGroupBtn = new JButton("Dodaj grupê");
		addGroupBtn.addActionListener(classListener);
//		classListener = new ListenForAction();
		removeGroupBtn =  new JButton("Usuñ grupê");
		removeGroupBtn.addActionListener(classListener);
		tableTypeLabel = new JLabel("Wybierz grupê:  ");
		newGroupName = new JTextField("nazwa nowej grupy");
		comboBox = new GroupComboBox(grandparentPanel, mDB.getGroupNames());		
		restart();
	}
	
	public void restart() {		
		comboBox = new GroupComboBox(grandparentPanel, mDB.getGroupNames());		
		removeAll();
		add(tableTypeLabel);		
		add(comboBox);	
		add(removeGroupBtn, "wrap");
		add(newGroupName);
		add(addGroupBtn);	
		setVisible(true);	
		revalidate();
		repaint();
	}
	

	private class ListenForAction implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			String groupName;
			if(e.getSource() == addGroupBtn) { // If the user clicks Add Customer, add the information into the database			
				groupName = newGroupName.getText();
				Pattern pattern = Pattern.compile("\\s");
				Matcher matcher = pattern.matcher(groupName);
				boolean found = matcher.find();
				if(found) {
					try {
						throw new Exception("Bia³y znak!");		
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				} else {					
					mDB.addGroup(groupName);
				}
			} else if (e.getSource() == removeGroupBtn) {				
				groupName = comboBox.getSelectedItem();				

//				JOptionPane optionPane = new JOptionPane("Question?",  JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				
				//accept input and convert (parse) if needed
				String x = JOptionPane.showInputDialog(null,"enter num!");
//				int x = Integer.parseInt();
				//assign variable and accept input
//				name = JOptionPane.showInputDialog(	null, "enter your name");

				//calculations
//				x = x + 5;

				//output seperate with commas, dusplay message, 
				//welcome message
//				JOptionPane.showMessageDialog(null, "your name is " + name + ". The num plus 5 is " + x + ".");

				mDB.removeGroup(groupName);
			}
			restart();
		}
	}
	
}
