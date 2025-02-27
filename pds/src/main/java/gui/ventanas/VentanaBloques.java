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
import gui.componentes.ComponenteBloque;
import gui.componentes.ComponenteCurso;
import gui.info.InfoBloque;
import gui.info.InfoCurso;
import gui.scroll.Scroll;
import gui.scroll.ScrollBloques;

@SuppressWarnings("serial")
public class VentanaBloques extends VentanaMenu {

	private ComponenteCurso cursoSeleccionado;
		
	public VentanaBloques(SelectorVentana selector, ComponenteCurso cursoSeleccionado) {
	        super(selector);
	        
	        this.cursoSeleccionado = cursoSeleccionado;
	    }

    @Override
    protected void construir() {
        super.construir();
    }

	@Override
	protected void construirScrollMenu() {
		
		//controlador
		
		ComponenteBloque[] componentes = new ComponenteBloque[5];
		for (int i = 0; i < 5; i++) {
	        componentes[i] = new ComponenteBloque(new InfoBloque(
	                "Titulico", "Descripcioncica", 
	                "C:/Users/aleja/git/historiApp/pds/resources/" + (new Random().nextInt(5)+1) + ".png", true));
	    }
		scroll = new ScrollBloques(VentanaBloques.this, selector, componentes);
		
	}
		
}
