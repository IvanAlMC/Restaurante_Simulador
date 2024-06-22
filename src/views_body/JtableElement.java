package views_body;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import views.Constants;

public class JtableElement extends JPanel{

	
	public static final int WIDTH_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements;
	private JTable jtElements;
	private JScrollPane jsTable;
    private Panel jPanel;
	
	public JtableElement(ActionListener actionListenner) {
		jPanel = new Panel();
		add(jPanel);
		initComponents(); 
	}
	

	
	private void initComponents() {
		this.setBackground(Color.white);
		dtmElements=new DefaultTableModel();
		changeLanguageColunmJtable();
		Font fontHeader =new Font(Constants.FONT_RUBIK, Font.BOLD,10);
		jtElements = new JTable();
		jtElements.setModel(dtmElements);
		jtElements.getTableHeader().setReorderingAllowed(false);
		jtElements.getTableHeader().setBackground(new Color(0,0,0));
		jtElements.getTableHeader().setForeground(Color.WHITE);
		jtElements.getTableHeader().setFont(fontHeader);
		jtElements.setBackground(Constants.GRAY_LIGHT);
		jtElements.setForeground(Color.BLACK);
		jtElements.setRowHeight(35);
		jtElements.setFont(new Font(Constants.FONT_RUBIK, Font.BOLD,12));
		jtElements.setFillsViewportHeight(true);
		jtElements.setBorder(null);
		jsTable = new JScrollPane(jtElements);
		jsTable.setBorder(null);
		jsTable.setPreferredSize(new Dimension(450, 400));
		jsTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(jsTable);
		setBorder(null);
	}
	
	public void changeLanguageColunmJtable(){
		String[] headers = {"N","CLIENTES","ATENDIDO POR","Tiempo entre llegadas"
				+ ""};
		dtmElements.setColumnIdentifiers(headers);
		repaint();
	}
	
	public void addPondList(ArrayList<Object[]> info) {
		for (Object[] objects : info) {
			dtmElements.addRow(objects);
		}
	}
	
	public void addElementTOtable(Object[] vector) {
		dtmElements.addRow(vector);
	}
	
	public void cleanRowsTable() {
		dtmElements.setNumRows(0);
	}
	
	
	public void stringFormat(ArrayList<Object[]> info) {
		ArrayList<Object[]> arrayFormat = new ArrayList<Object[]>();
		for (Object[] objects : info) {
			int i = 0;
			arrayFormat.add(new Object[] {objects[i],objects[++i],objects[++i],objects[++i]});
		}
		addPondList(arrayFormat);
	}
	public JTable tableOriginal() {
		return jtElements;
	}
	
}
