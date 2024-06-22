package views;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MyJFramePpal extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyJPanelPrincipal myJPanelPrincipal;

	public MyJFramePpal(ActionListener actionListenner) {
		try {
			setMinimumSize(new Dimension(700, 400));
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setExtendedState(MAXIMIZED_BOTH);
//	    	JScrollPane jScrollPane = new JScrollPane();
//			this.add( jScrollPane);
			setVisible(true);
			initComponents(actionListenner); 
			updatePantalla();
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

	}
	
	private void initComponents(ActionListener actionListenner) {
		myJPanelPrincipal = new MyJPanelPrincipal(actionListenner);
		myJPanelPrincipal.setVisible(true);
		add(myJPanelPrincipal);
		
	}
	
	public void addElementTOtable(Object[] vector) {
		myJPanelPrincipal.addElementTOtable(vector);
	}

	public void addElementTOtableConcineros(Object[] vector) {
		myJPanelPrincipal.addElementTOtableConcineros(vector);
	}

	public void visibleSimButton(Boolean estado) {
		myJPanelPrincipal.visibleSimButton(estado);
	}
	
	public void setTextFooter(String messager) {
		myJPanelPrincipal.setTextFooter(messager);
	}
	
	public void resert() {
		myJPanelPrincipal.resert();

	}
	
	public void updatePantalla(){
		try {
			SwingUtilities.updateComponentTreeUI(this);
			this.validateTree();
		} catch (IllegalStateException e) {
			// TODO: handle exception
		}
	}
}
