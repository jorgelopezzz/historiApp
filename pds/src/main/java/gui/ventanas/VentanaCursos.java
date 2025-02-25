package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    	int numElementos = 14;
    	
    	int ANCHO_MENU = ANCHO_VENTANA - 50; // Tamaño estimado de scroll
    	int ALTO_MENU = Componente.ALTO_COMPONENTE*((numElementos+columnas-1)/columnas);
    	
		panelMenu = new JPanel();
		GestorGUI.configurarPanel(panelMenu, new GridLayout(0, columnas, 5, 5),
				GestorGUI.getInstancia().getColorClaro(), ANCHO_MENU, ALTO_MENU);
		
		for (int i = 0; i < numElementos; i++) {
	        ComponenteCurso curso = new ComponenteCurso(new InfoCurso(
	                "Titulico", "Descripcioncica", 
	                "C:\\Users\\aleja\\git\\historiApp\\pds\\resources\\racha.png", true));

	        // Agregar evento de clic
	        manejadorCurso(curso);

	        panelMenu.add(curso);
	    }
		
        
    }
    
    private void manejadorCurso(ComponenteCurso curso) {
    	 curso.addMouseListener(new MouseAdapter() {
	            private boolean seleccionado = false;

	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if(seleccionado)
	                	curso.deseleccionar();
	                else
	                	curso.seleccionar();
	            }
	        });
    }

	@Override
	protected void construirScrollMenu() {
		scrollMenu = new ScrollCursos(panelMenu);
		
	}
    
}
