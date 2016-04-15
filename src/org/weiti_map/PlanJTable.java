package org.weiti_map;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.weiti_map.db.GroupPlanObject;

public class PlanJTable extends JTable /* implements TableModelListener */{

	private static final long serialVersionUID = 8925549787570334079L;
	private TableModel tableModel;
	private FilledPlanTableModel filledTableModel;

	public PlanJTable() {
		super();
		tableModel = new PlanTableModel();
		resetTable();
		configure();
	}

	private void configure() {
		this.setFont(new Font("Arial", Font.BOLD, 15));
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		setPreferredScrollableViewportSize(new Dimension(500, 70));
		setFillsViewportHeight(true);
	}

	void setGroupPlan(GroupPlanObject plan, char parzystosc) {
		filledTableModel = new FilledPlanTableModel(plan, parzystosc);

		System.out.print("roftl");
		filledTableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
//				FilledPlanTableModel model = (FilledPlanTableModel) e.getSource();
				String columnName = tableModel.getColumnName(column);
				String data = (String) tableModel.getValueAt(row, column);
				System.out.print(data);
				
			}
		});

		setModel(filledTableModel);
	}
	
	@Override
	public	Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
        return component;
    }

	 void resetTable() {
		setModel(tableModel);
	}

}
