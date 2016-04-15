package org.weiti_map;

import javax.swing.JPanel;

import org.weiti_map.db.CustomTableObject;
import org.weiti_map.db.GroupPlanObject;
import org.weiti_map.db.MyDatabase;
import org.weiti_map.db.RoomObj;
import org.weiti_map.db.RoomsTableObject;
import org.weiti_map.db.WorkersTableObject;
import org.weiti_map.table_models.CustomTableModel;
import org.weiti_map.table_models.WorkersTableModel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MyShowPanel extends JPanel {

	private static final long serialVersionUID = -2823625770116916867L;

	public enum SHOW_PANEL_TYPES {GROUP_TABLES, WORKERS_TABLE, ROOMS_TABLE, LECTURES_TABLE};
	private SHOW_PANEL_TYPES panel_type;
	
	private MyDatabase mDatabase;
	private PlanTablePanel tablePanelP;
	private PlanTablePanel tablePanelN;
//	private CustomTablePanel<CustomTableModel<RoomsTableObject>, RoomsTableObject> roomsTable;

	private CustomTablePanel<CustomTableModel<CustomTableObject<RoomObj>>, CustomTableObject<RoomObj>> roomsTable;
	private CustomTablePanel<WorkersTableModel, WorkersTableObject> workersTable;
//	private LecturesTablePanel lecturesTable;
	
	   MyShowPanel(MyDatabase mDB, SHOW_PANEL_TYPES type) {
			super();			
			mDatabase = mDB;
			panel_type = type;	
			configure();
	   }	   	   	   
	   
	   void configure() {
			LC layoutConstraints = new LC();
			layoutConstraints.setFillX(true);
			setLayout(new MigLayout(layoutConstraints));
			
			switch (panel_type) {
			case GROUP_TABLES:
				tablePanelP = new PlanTablePanel('P');
				tablePanelN = new PlanTablePanel('N');	
				add(tablePanelP);
				add(tablePanelN);
				break;
			case WORKERS_TABLE:
				WorkersTableObject tabObject0 = mDatabase.getWorkersTableObject();
				workersTable = new CustomTablePanel<WorkersTableModel, WorkersTableObject>("Pracownicy", tabObject0, new WorkersTableModel(tabObject0));
				add(workersTable);
				break;
			case ROOMS_TABLE:
//				RoomsTableObject tabObject1 = mDatabase.getRoomsTableObject(); 
				CustomTableObject<RoomObj> roomTableObj = mDatabase.getRoomsTableObject();
				String[] ColNames = {"ID", "Nazwa Sali", "Piêtro", "X", "Y"};
//				CustomTableModel<RoomsTableObject> roomTableModel= new CustomTableModel<RoomsTableObject>(5, ColNames, roomTableObj);
//				roomsTable = new CustomTablePanel<CustomTableModel<RoomsTableObject>, RoomsTableObject>("Sale", tabObject1, roomTableModel);
				CustomTableModel<CustomTableObject<RoomObj>> roomTableModel= new CustomTableModel<CustomTableObject<RoomObj>>(5, ColNames, roomTableObj);
				roomsTable = new CustomTablePanel<CustomTableModel<CustomTableObject<RoomObj>>, CustomTableObject<RoomObj>>("Sale", roomTableObj, roomTableModel);
				add(roomsTable);
				break;
			case LECTURES_TABLE:
//				LecturesTableObject tabObject2 = mDatabase.getRoomsTableObject();
//				lecturesTable = new CustomTablePanel<LecturesTableModel, LecturesTableObject>("Sale", tabObject2, new LecturesTableModel(tabObject1));
//				add(lecturesTable);
				break;
			}		
		}
	   
	    void setGroupPlanObject(GroupPlanObject plan) {	    	
	    	if (panel_type == SHOW_PANEL_TYPES.GROUP_TABLES) {
	    		tablePanelP.setGroupPlan(plan);
    	    	tablePanelN.setGroupPlan(plan);
	    	} else {
	    		System.out.println("BAD SHOW PANEL TYPE");
	    		return;
	    	}
	    	
	    }

		void resetTable() {
			tablePanelP.resetTable();
	    	tablePanelN.resetTable();  			
		}
	    
		SHOW_PANEL_TYPES getPanelType() {
			return panel_type; 
		}

		public void showWorkersTable(WorkersTableObject table) {
			// TODO Auto-generated method stub			
		}

		public void showRoomsTable(RoomsTableObject table) {
			// TODO Auto-generated method stub			
		}
}
