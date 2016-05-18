package org.weiti_map;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.weiti_map.db.MyDatabase;
import org.weiti_map.table_models.FilledPlanTableModel;
import org.weiti_map.table_models.PlanTableModel;

import com.example.kuba.weitimap.db.GroupPlanObject;

public class PlanJTable extends JTable /* implements TableModelListener */ {

	private static final long serialVersionUID = 8925549787570334079L;
	private TableModel tableModel;
	private FilledPlanTableModel filledTableModel;
	private MyDatabase myDatabase;
	char parzystosc;

	public PlanJTable(MyDatabase mDB) {
		super();
		myDatabase = mDB;
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
		filledTableModel = new FilledPlanTableModel(myDatabase, plan,
				parzystosc);
		setModel(filledTableModel);
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row,
			int column) {
		Component component = super.prepareRenderer(renderer, row, column);
		int rendererWidth = component.getPreferredSize().width;
		TableColumn tableColumn = getColumnModel().getColumn(column);
		tableColumn.setPreferredWidth(
				Math.max(rendererWidth + getIntercellSpacing().width + 10,
						tableColumn.getPreferredWidth()));
		return component;
	}

	void resetTable() {
		setModel(tableModel);
	}
}
