package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.ComponenteCurso;
import gui.info.InfoCurso;
import gui.scroll.Scroll;
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
    
    private void manejadorCurso(ComponenteCurso curso) {
    	 curso.addMouseListener(new MouseAdapter() {
	            private boolean seleccionado = false;

	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if(seleccionado) {
	                	curso.deseleccionar();
	                }
	                else
	                	curso.seleccionar();
	            }
	        });
    }

    @Override
	protected void construirScrollMenu() {
		
		//controlador
		
    	ComponenteCurso[] componentes = new ComponenteCurso[5];
		for (int i = 0; i < 5; i++) {
	        componentes[i] = new ComponenteCurso(new InfoCurso(
	                "Titulico", "Descripcioncica", 
	                "C:/Users/aleja/git/historiApp/pds/resources/" + (new Random().nextInt(5)+1) + ".png", true));
	    }
		scroll = new ScrollCursos(VentanaCursos.this, selector, componentes);
		
	}
		
}
