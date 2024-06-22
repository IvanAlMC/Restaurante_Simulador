package views_body;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import controllers.Commands;
import views_header.JButtonsMenuAndDialogs;

public class JpanelBody extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JtableElement table;
	private JtableConcineros tableConcineros;
	public static final int WIDTH_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	public JpanelBody(ActionListener actionListenner) {
		setBackground(new Color(17,0,47));
		FlowLayout flowLayout=new FlowLayout();
		flowLayout.setHgap(5);
//		setLayout(flowLayout);
		setVisible(true);
		initComponen(actionListenner);
	}
	
	 @SuppressWarnings("unused")
	private void initComponen(ActionListener actionListenner) {
	    	JPanel panelMenu = new JPanel();
	    	FlowLayout flowLayout=new FlowLayout();
			flowLayout.setHgap(10);
			panelMenu.setLayout(flowLayout); 
	    	setMinimumSize(new Dimension(500, 300));
	    	panelMenu.setBackground(new Color(17,0,47));
	    	panelMenu.setVisible(true);
	    
	    	table = new JtableElement(actionListenner);
	    	table.setBackground(new Color(17,0,47));
	    	add(table);
	    	
	    	tableConcineros = new JtableConcineros(actionListenner);
	    	tableConcineros.setBackground(new Color(17,0,47));
	    	add(tableConcineros);
	    	JButtonsMenuAndDialogs simButton = new JButtonsMenuAndDialogs("Simular");
	    	simButton.addActionListener(actionListenner);
	    	simButton.setActionCommand(Commands.SIMULATE1.toString());
	    	simButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	panelMenu.add(simButton);
	        
	    	JTextPane texto = new JTextPane(); 
	    	texto.setSize(500, 500);
	    	texto.setVisible(true);
//	        updatePantalla();
		}
	 
	 public void updatePantalla(){
			try {
				SwingUtilities.updateComponentTreeUI(this);
				this.validateTree();
			} catch (IllegalStateException e) {
				// TODO: handle exception
			}
		}
	 
		public void addElementTOtable(Object[] vector) {
			table.addElementTOtable(vector);
		}
		
		public void addElementTOtableConcineros(Object[] vector) {
			tableConcineros.addElementTOtable(vector);
		}
		
		public void cleanTable() {
			table.cleanRowsTable();
			tableConcineros.cleanRowsTable();

		}
}
