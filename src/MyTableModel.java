import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableModel extends AbstractTableModel {
	
		private final static int JTABLE_ROW_NUM = 12;
		private final static int JTABLE_COLS_NUM = 7;
		
		private static String[][] data = new String[JTABLE_ROW_NUM][JTABLE_COLS_NUM];
		
		private final static String[] DNI_TYGODNIA =
				{" ",
				"poniedzia³ek",
				"wtorek",
				"œroda",
				"czwartek",
				"pi¹tek",
				" "};
		
		private final static String[] GODZINY = 
				{"08:15-09:00",
				 "09:15-10:00",
				 "10:15-11:00",
				 "11:15-12:00",
				 "12:15-13:00",
				 "13:15-14:00",
				 "14:15-15:00",
				 "15:15-16:00",
				 "16:15-17:00",
				 "17:15-18:00",
				 "18:15-19:00",
				 "19:15-20:00"};
		
		public MyTableModel() {
			super();
			for (int i = 0; i < 12; i++) {
				data[i][0] = GODZINY[i];		
			}
			
		}
		
		public String getColumnName(int col) {
			return DNI_TYGODNIA[col];
	    }
		
		public Class getColumnClass(int c) {
            return String.class;
        }
		
	    @Override
	    public boolean isCellEditable(int row, int column) {
	    	if ((column == 0) && (row < 13)) {
		       return false;
	    	}
	    	return true;
	    }

		@Override
		public int getRowCount() {
			return GODZINY.length;
		}

		@Override
		public int getColumnCount() {
			return DNI_TYGODNIA.length - 1;
		}

		@Override
		public Object getValueAt(int row, int col) {
            return data[row][col];
		}
		
		public void setValueAt(String text, int row, int col) {
			data[row][col] = text;
            fireTableCellUpdated(row, col);
        }
		
}
