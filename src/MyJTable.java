import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MyJTable extends JTable {


	public MyJTable() {
		super(new MyTableModel());		

		this.setFont(new Font("Arial", Font.BOLD, 15));
//		setLayout(new BorderLayout());
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < 6; i++) {
			getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
        
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);
//        getSelectionModel().addListSelectionListener(new RowListener());
//        getColumnModel().getSelectionModel().
//            addListSelectionListener(new ColumnListener());

	}

}
