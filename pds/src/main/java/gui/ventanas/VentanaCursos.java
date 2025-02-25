package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.ComponenteCurso;
import gui.info.InfoCurso;
import gui.scroll.ScrollCursos;

@SuppressWarnings("serial")
public class VentanaCursos extends VentanaMenu {

	 public VentanaCursos(SelectorVentana selector) {
	        super(selector);
	    }

    @Override
    protected void construir() {
        super.construir();
    }
    
    @Override
    protected void construirPanelMenu() {
    	
    	int columnas = 3;
    	int numElementos = 30;
    	
    	int ALTO_MENU = Componente.ALTO_COMPONENTE*(numElementos/columnas);
    	
		panelMenu = new JPanel();
		GestorGUI.configurarPanel(panelMenu, new GridLayout(0, columnas, 5, 5),
				GestorGUI.getInstancia().getColorClaro(), ANCHO_VENTANA, ALTO_MENU);
		
		for (int i = 0; i < numElementos; i++) {
		    panelMenu.add(new ComponenteCurso(new InfoCurso("Titulico", "Descripcioncica", "C:\\Users\\aleja\\git\\historiApp\\pds\\resources\\racha.png", true)));
		}
		
        
    }

	@Override
	protected void construirScrollMenu() {
		scrollMenu = new ScrollCursos(panelMenu);
		
	}
    
}
