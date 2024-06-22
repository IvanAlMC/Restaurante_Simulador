package views_header;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controllers.*;
import views.Constants;


public class JPanelHeader extends JPanel{

    private static final long serialVersionUID = 1L;
    JButtonsMenuAndDialogs simButton;
    
	public static final int WIDTH_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT_SCREEN = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	
    public JPanelHeader(ActionListener actionListenner) {
    	setBackground(new Color(17,0,47));
    	setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    	initComponen(actionListenner);
        
    	setVisible(true);
    	
    }
    
    private void initComponen(ActionListener actionListenner) {
    	JPanel panelMenu = new JPanel();
    	
    	panelMenu.setBackground(new Color(17,0,47));
    	panelMenu.setVisible(true);
    	
    	JLabel titulo = new JLabel("SIMULADOR DE FILAS DE LÍNEAS DE ESPERA");
    	Font fontHeader =new Font(Constants.FONT_RUBIK, Font.BOLD,16);
    	titulo.setFont(fontHeader);
    	titulo.setForeground(Color.WHITE);
    	panelMenu.add(titulo);
    
    	simButton = new JButtonsMenuAndDialogs("Simular");
    	simButton.addActionListener(actionListenner);
    	simButton.setActionCommand(Commands.SIMULATE1.toString());
    	simButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	panelMenu.add(simButton);
        
    	JButtonsMenuAndDialogs reiniciarButton = new JButtonsMenuAndDialogs("Reiniciar");
    	reiniciarButton.addActionListener(actionListenner);
    	reiniciarButton.setActionCommand(Commands.STOP1.toString());
    	reiniciarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	reiniciarButton.setVisible(false);
    	panelMenu.add(reiniciarButton);
        add(panelMenu);
        updatePantalla();
        
//    	JButtonsMenuAndDialogs grafica_1 = new JButtonsMenuAndDialogs("Grafica: Rendimiento Meseros");
//    	grafica_1.addActionListener(actionListenner);
//        grafica_1.setActionCommand(Commands.STAT1.toString());
//    	panelMenu.add(grafica_1);
//        add(panelMenu);
//        updatePantalla();
        
        
    	JButtonsMenuAndDialogs grafica_2 = new JButtonsMenuAndDialogs("Grafica: Rendimiento Cocineros");
    	grafica_2.addActionListener(actionListenner);
        grafica_2.setActionCommand(Commands.STAT2.toString());
    	panelMenu.add(grafica_2);
        add(panelMenu);
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
	
	public void visibleSimButton(Boolean estado) {
		simButton.setVisible(estado);
	}
        
        
}    
