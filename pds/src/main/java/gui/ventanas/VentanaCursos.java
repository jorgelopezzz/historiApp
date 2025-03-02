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
		for (int i = 1; i < 5; i++) {
	        componentes[i] = new ComponenteCurso(new InfoCurso(
	                "Historia del arte", "Explora la evolución del arte desde la prehistoria hasta la actualidad, analizando sus estilos, movimientos y contextos históricos. A través de imágenes, análisis de obras y debates, descubrirás cómo el arte refleja la cultura y el pensamiento de cada época. Ideal para amantes del arte y la historia.", 
	                //"C:/Users/aleja/git/historiApp/pds/resources/" + (new Random().nextInt(5)+1) + ".png", true));
	                "C:\\Users\\jorge\\git\\repository\\proyectoPDS\\historiApp\\pds\\resources\\" + (new Random().nextInt(5)+1) + ".png", true));
	    }
		componentes[0] = new ComponenteCurso(new InfoCurso(
                "Historia de España",  
                "Un recorrido por los acontecimientos clave que han marcado la historia de España, desde la antigüedad hasta la actualidad. Analizaremos sus civilizaciones, monarquías, conflictos y transformaciones sociales para comprender su impacto en el mundo. Ideal para quienes desean profundizar en el pasado y presente de España.",
                "C:\\Users\\jorge\\git\\repository\\proyectoPDS\\historiApp\\pds\\resources\\" + (new Random().nextInt(5)+1) + ".png", true));
                
		scroll = new ScrollCursos(VentanaCursos.this, selector, componentes);
		
	}
		
}
