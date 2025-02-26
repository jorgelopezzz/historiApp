package gui.ventanas;

import java.awt.GridLayout;

import javax.swing.JPanel;

import gui.GestorGUI;
import gui.componentes.Componente;

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
    	
    	int columnas = 3;
    	int numElementos = 14;
    	
    	int ANCHO_MENU = ANCHO_VENTANA - 50; // Tamaño estimado de scroll
    	int ALTO_MENU = Componente.ALTO_COMPONENTE*((numElementos+columnas-1)/columnas);
    	
		panelMenu = new JPanel();
		GestorGUI.configurarPanel(panelMenu, new GridLayout(0,3),
				GestorGUI.getInstancia().getColorClaro(), ANCHO_MENU, ALTO_MENU);
    }

	@Override
	protected void construirScrollMenu() {
		// TODO Auto-generated method stub
		
	}
    
}
