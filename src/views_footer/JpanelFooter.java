package views_footer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import views.Constants;

public class JpanelFooter extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2661314215416252591L;
	private TextArea  texto;

	public JpanelFooter(ActionListener actionListenner) {
		setBackground(new Color(17,0,47));
		FlowLayout flowLayout=new FlowLayout();
		flowLayout.setHgap(10);
//		setLayout(flowLayout);
		setVisible(true);
		initComponen(actionListenner);
	}
	
	private void initComponen(ActionListener actionListenner) {
		JLabel titulo = new JLabel("REPORTES: ");
		Font fontHeader =new Font(Constants.FONT_RUBIK, Font.BOLD,16);
    	titulo.setFont(fontHeader);
    	titulo.setForeground(Color.WHITE);
    	add(titulo);
		texto = new TextArea(8,100 );
		texto.setBackground(Color.WHITE);
    	texto.setSize(500, 50);
    	texto.setPreferredSize(new Dimension(100, 100));
    	texto.setVisible(true);
    	texto.getBaselineResizeBehavior();
    	add(texto);
	}
	
	public void setTextFooter(String messager) {
		texto.setText(messager);
		updatePantalla();
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
