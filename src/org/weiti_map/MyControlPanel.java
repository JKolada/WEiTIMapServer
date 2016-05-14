package org.weiti_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.weiti_map.db.MyDatabase;

import com.example.kuba.weitimap.db.GroupPlanObject;
import com.example.kuba.weitimap.db.LectureObj;

import net.miginfocom.swing.MigLayout;

public class MyControlPanel extends JPanel {
	private static final long serialVersionUID = -204928193180502263L;

	private final String[] viewsNames = {"Plan zajêæ", "Zajêcia", "Sale" };
	
	private MyPanel parentJPanel;
	private MyDatabase mDatabase;
	
	private JPanel groupJPanel; 
	private JButton loadFileBtn;
	private JFileChooser fc;
	
	private GroupNameJPanel tableTypeJPanel; 	
	private MainViewsComboBox comboBox1; 

		
    MyControlPanel(MyPanel parent, MyDatabase mDB) {
		super();
		mDatabase = mDB;
		parentJPanel = parent;		
		configure();				
	}   

   
    private void configure() {      	
    	MigLayout layout = (new MigLayout(
    			 "fillx, debug", // Layout Constraints
    			 "[][][]", // Column constraints
    			 ""));
    	setLayout(layout);
    	fc = new JFileChooser();
		groupJPanel = new JPanel(new MigLayout("fillx, debug"));
		loadFileBtn = new JButton("Wczytaj plan grupy w postaci pliku XML");
		tableTypeJPanel = new GroupNameJPanel(mDatabase, parentJPanel);
		comboBox1 = new MainViewsComboBox(parentJPanel, this, tableTypeJPanel, viewsNames);
		
		
		loadFileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(MyControlPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				
				JAXBContext jaxbContext;
				try {
					jaxbContext = JAXBContext.newInstance(GroupPlanObject.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
					JAXBElement<GroupPlanObject> root = jaxbUnmarshaller.unmarshal(new StreamSource(file), GroupPlanObject.class);
					mDatabase.insertGroupObj(root.getValue());					
					parentJPanel.showGroupPlan();		
					tableTypeJPanel.restart();
					
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				}
			}
		});

		groupJPanel.add(loadFileBtn);	
		groupJPanel.add(comboBox1);		
		groupJPanel.add(tableTypeJPanel);
		
		add(groupJPanel);
		
    }    
	
}


