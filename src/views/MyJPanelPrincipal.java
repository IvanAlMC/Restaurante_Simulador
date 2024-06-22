package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import views_body.JpanelBody;
import views_footer.JpanelFooter;
import views_header.JPanelHeader;

public class MyJPanelPrincipal extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	private JpanelBody bodyPanel;
	private JPanelHeader panelHeadel;
	private JpanelFooter footerPaner;
	
	public MyJPanelPrincipal(ActionListener actionListenner) {
		setBackground(Color.red);
		
		FlowLayout flowLayout=new FlowLayout(FlowLayout.CENTER);
		flowLayout.setHgap(30);
		setLayout(flowLayout); 
		setVisible(true);
		setLayout(new BorderLayout());
		
		initComponents(actionListenner);
//		updatePantalla();
    }
	
	private void initComponents(ActionListener actionListenner) {
		panelHeadel = new JPanelHeader(actionListenner);
		add(panelHeadel,BorderLayout.NORTH);
	
		bodyPanel = new JpanelBody(actionListenner);
		bodyPanel.setPreferredSize(new Dimension(450, 400));
		add(bodyPanel,BorderLayout.CENTER);
		
		footerPaner = new JpanelFooter(actionListenner);
		footerPaner.setPreferredSize(new Dimension(150, 150));
		add(footerPaner,BorderLayout.SOUTH);

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
		bodyPanel.addElementTOtable(vector);
	}
	
	public void addElementTOtableConcineros(Object[] vector) {
		bodyPanel.addElementTOtableConcineros(vector);
	}
	
	public void visibleSimButton(Boolean estado) {
		panelHeadel.visibleSimButton(estado);
	}
	
	public void setTextFooter(String messager) {
		footerPaner.setTextFooter(messager);
	}
	
	public void resert() {
		bodyPanel.cleanTable();
	}
}
