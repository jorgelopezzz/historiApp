package gui.ventanas;

import java.awt.GridLayout;

import javax.swing.JPanel;

import gui.GestorGUI;

@SuppressWarnings("serial")
public class VentanaBloques extends VentanaMenu {

	 public VentanaBloques(SelectorVentana selector) {
	        super(selector);
	    }

    @Override
    protected void construir() {
        super.construir();
    }
    
    @Override
    protected void construirPanelMenu() {
		panelMenu = new JPanel();
		GestorGUI.configurarPanel(panelMenu, new GridLayout(0,3),
				GestorGUI.getInstancia().getColorClaro(), ANCHO_VENTANA, ALTO_MENU);
    }
    
}
