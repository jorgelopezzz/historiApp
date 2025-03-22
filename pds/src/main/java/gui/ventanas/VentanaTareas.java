package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.ComponenteBloque;
import gui.componentes.ComponenteCurso;
import gui.componentes.ComponenteTarea;
import gui.emergentes.EmergenteFichero;
import gui.emergentes.EmergenteSiNo;
import gui.info.InfoCurso;
import gui.scroll.Scroll;
import gui.scroll.ScrollCursos;

@SuppressWarnings("serial")
public class VentanaTareas extends VentanaMenu {
	
	/* Componentes de organización */
	JButton botonSalir;
	JButton botonSiguiente;
	JPanel panelSiguiente;
	
	/* Atributos a recabar */
	private String rutaJSON;
	
	/* Bloque de contenidos seleccionado */
	private ComponenteBloque bloqueSeleccionado; // Para botón SIGUIENTE
	private ComponenteCurso cursoSeleccionado; // Para botón SALIR
	
	public VentanaTareas(SelectorVentana selector, ComponenteBloque bloqueSeleccionado, ComponenteCurso cursoSeleccionado) {
	       super(selector);
	       this.bloqueSeleccionado = bloqueSeleccionado;
	       this.cursoSeleccionado = cursoSeleccionado;
	}

    @Override
    protected void construir() {
        super.construir();
    }

    @Override
    protected void construirInicio() {
	    panelInicial = new JPanel();
	    GestorGUI.configurarPanel(panelInicial, new BoxLayout(panelInicial, BoxLayout.X_AXIS),
	    		GestorGUI.getInstancia().getColorClaro(), ANCHO_VENTANA, ALTO_BARRA);
	    ///
	    panelInicial.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
	    ///
	        
	    panelInicial.add(Box.createHorizontalGlue());
		    
	    botonSalir = GestorGUI.getBotonPredeterminado("Salir");
	    manejadorSalir();
		    
	    panelInicial.add(botonSalir, BorderLayout.EAST);
	    
	    ///
	    panelInicial.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
	    ///
    }
    
    private void construirPanelSiguiente() {
    	panelSiguiente = new JPanel();
                     
        botonSiguiente = GestorGUI.getBotonPredeterminado("Siguiente");
        manejadorSalir();
        
        panelSiguiente.add(botonSiguiente);        
     
    }
    
    @Override
    protected void construirScrollMenu() {
        // Crear el panel general
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.setBackground(GestorGUI.getInstancia().getColorClaro());

        // Crear el componente del curso
        ComponenteTarea componente = new ComponenteTarea(new InfoCurso(
                "Historia de España",
                "Un recorrido por los acontecimientos clave que han marcado la historia de España, desde la antigüedad hasta la actualidad. Analizaremos sus civilizaciones, monarquías, conflictos y transformaciones sociales para comprender su impacto en el mundo. Ideal para quienes desean profundizar en el pasado y presente de España.",
                "C:\\Users\\jorge\\git\\repository\\proyectoPDS\\historiApp\\pds\\resources\\" + (new Random().nextInt(5) + 1) + ".png",
                true));
        
        // Agregar el componente al centro del panel general
        panelGeneral.add(componente);
        panelGeneral.add(Box.createVerticalGlue());
        
        // Crear panelSiguiente y configurarlo
        construirPanelSiguiente();
        
        // Agregar panelSiguiente en la parte inferior
        panelGeneral.add(panelSiguiente);
        
        // Crear el scroll que contiene el panelGeneral
        scroll = panelGeneral;
    }

    
	private void manejadorSalir() {
		botonSalir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {		
				// Lanzar la ventana emergente
				EmergenteSiNo emergente = new EmergenteSiNo(VentanaTareas.this, "¿Estás seguro de que quieres salir?\nNo se guardará tu progreso.");
				emergente.mostrar();
				if(emergente.obtenerRespuesta().orElse(false))
					selector.cambiarVentana(new VentanaBloques(selector, cursoSeleccionado, "Método de aprendizaje"));
			}
		});
	}
		
}
