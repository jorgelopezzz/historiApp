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

import dominio.HistoriApp;
import dominio.info.Info;
import dominio.tarea.Tarea;
import gui.GestorGUI;
import gui.componentes.contenidos.ComponenteBloque;
import gui.componentes.contenidos.ComponenteCurso;
import gui.componentes.tarea.ComponentePregunta;
import gui.componentes.tarea.ComponenteRellenar;
import gui.componentes.tarea.ComponenteTarea;
import gui.componentes.tarea.FactoriaComponenteTarea;
import gui.emergentes.EmergenteBotones;
import gui.emergentes.EmergenteMensaje;
import gui.emergentes.EmergenteSiNo;


@SuppressWarnings("serial")
public class VentanaTareas extends VentanaMenu {
	
	/* Dimensiones */
	private static final int MARGEN = 15;
	
	private static final String RUTA_CHECK = "/check.png";
	
	/* Componentes de organización */
	JPanel panelGeneral;
	private JButton botonSalir;
	private JButton botonSiguiente;
	private JPanel panelSiguiente;
	
	private ComponenteTarea tareaActual;
	
	public VentanaTareas(SelectorVentana selector) {
	       super(selector);
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
        panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.setBackground(GestorGUI.getInstancia().getColorClaro());

        // Crear el componente del curso
        tareaActual = FactoriaComponenteTarea.crearTarea(HistoriApp.INSTANCE.siguiente(Optional.empty())); 
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
				EmergenteSiNo emergente = new EmergenteSiNo(VentanaTareas.this, "¿Estás seguro de que quieres salir?\nNo se guardará tu progreso.", EmergenteBotones.RUTA_ALERTA);
				emergente.mostrar();
				if(emergente.obtenerRespuesta().orElse(false)) {
					HistoriApp.INSTANCE.cerrarBloque();
					selector.cambiarVentana(new VentanaBloques(selector));
				}
			}
		});
	}
	
	private void manejadorSiguiente() {
		botonSiguiente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				/* Obtención de la respuesta */
				Optional<String> respuesta;
				
				if(tareaActual.esPregunta()) {
					// Si es Pregunta, es evaluable y obtenemos la respuesta
					respuesta = ((ComponentePregunta)tareaActual).getRespuesta();
					if(respuesta.isEmpty()) 
						return; // Si no hay respuesta, no podemos pasar a la siguiente
				} else { 
					respuesta = Optional.empty();
				}
				Info tareaSiguiente = HistoriApp.INSTANCE.siguiente(respuesta);
				if(tareaSiguiente != null) {
					panelGeneral.remove(tareaActual);
	                
	                tareaActual = FactoriaComponenteTarea.crearTarea(tareaSiguiente);
	                tareaActual.setAlignmentX(Component.CENTER_ALIGNMENT);
	                panelGeneral.add(tareaActual, 0);
	                
	                panelGeneral.revalidate();
	                panelGeneral.repaint();
				} else {
					double puntuacion = HistoriApp.INSTANCE.obtenerPuntuacion();
					String mensaje = "Tu puntuación es " + String.valueOf(puntuacion);
					EmergenteMensaje emergentePuntuacion = new EmergenteMensaje(VentanaTareas.this, mensaje, RUTA_CHECK);
					emergentePuntuacion.mostrar();
					if(HistoriApp.INSTANCE.cursoCompletado()) {
						EmergenteMensaje emergenteFinCurso = new EmergenteMensaje(VentanaTareas.this,
								"¡Enhorabuena! Has completado el curso: " + HistoriApp.INSTANCE.getCursoActual(), RUTA_CHECK);
						emergenteFinCurso.mostrar();
						selector.cambiarVentana(new VentanaCursos(selector));
					} else {						
						selector.cambiarVentana(new VentanaBloques(selector));					
					}
				}
			}
		});
	}
		
}
