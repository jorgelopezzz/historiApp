package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import dominio.info.Info;
import gui.GestorGUI;
import gui.componentes.contenidos.ComponenteBloque;
import gui.componentes.contenidos.ComponenteCurso;
import gui.componentes.tarea.ComponenteRellenar;
import gui.componentes.tarea.ComponenteTarea;
import gui.emergentes.EmergenteSiNo;


@SuppressWarnings("serial")
public class VentanaTareas extends VentanaMenu {
	
	/* Dimensiones */
	private static final int MARGEN = 15;
	
	/* Componentes de organización */
	private JButton botonSalir;
	private JButton botonSiguiente;
	private JPanel panelSiguiente;
	
	private ComponenteTarea tareaActual;
	
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
    	GestorGUI.configurarPanel(panelSiguiente, new BoxLayout(panelSiguiente, BoxLayout.Y_AXIS), GestorGUI.getInstancia().getColorClaro());
                     
        botonSiguiente = GestorGUI.getBotonPredeterminado("Siguiente");
        botonSiguiente.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        manejadorSiguiente();
        
        panelSiguiente.add(Box.createVerticalStrut(MARGEN));
        panelSiguiente.add(botonSiguiente);
        panelSiguiente.add(Box.createVerticalStrut(MARGEN));
     
    }
    
    @Override
    protected void construirScrollMenu() {
        // Crear el panel general
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.setBackground(GestorGUI.getInstancia().getColorClaro());

        // Crear el componente del curso
        tareaActual = new ComponenteRellenar( new Info("Cuál es el mejor país del mundo") ); 
        tareaActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Agregar el componente al centro del panel general
        panelGeneral.add(tareaActual);
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
	
	private void manejadorSiguiente() {
		botonSiguiente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {		
				// Lanzar la ventana emergente

			}
		});
	}
		
}
