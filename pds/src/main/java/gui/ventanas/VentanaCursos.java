package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GestorGUI;
import gui.componentes.contenidos.ComponenteCurso;
import gui.emergentes.EmergenteFichero;
import gui.info.contenidos.InfoCurso;
import gui.scroll.ScrollCursos;

@SuppressWarnings("serial")
public class VentanaCursos extends VentanaMenu {
	
	/* Componentes de organización */
	JLabel etiquetaBienvenida;
	JButton botonCrearCurso;
	
	/* Atributos a recabar */
	private String rutaJSON;
	
	/* Tipo de rol */
	protected int rol;
	
	public VentanaCursos(SelectorVentana selector, int rol) {
	       super(selector);
	       this.rol = rol;
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
	    
	    etiquetaBienvenida = new JLabel("Bienvenido, a continuación puedes elegir tu curso.");
	    GestorGUI.configurarEtiqueta(etiquetaBienvenida, false, GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getFuenteTexto());
	    panelInicial.add(etiquetaBienvenida, BorderLayout.WEST);
	    
	    if(rol == 0) {
		    panelInicial.add(Box.createHorizontalGlue());
		    
		    botonCrearCurso = GestorGUI.getBotonPredeterminado("Crear curso");
		    manejadorCrearCurso();
		    
		    panelInicial.add(botonCrearCurso, BorderLayout.EAST);
		    
		    ///
		    panelInicial.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
		    ///
	    }
    }
    
    @Override
	protected void construirScrollMenu() {
		
		//controlador
		
    	ComponenteCurso[] componentes = new ComponenteCurso[5];
		for (int i = 0; i < 5; i++) {
	        componentes[i] = new ComponenteCurso(new InfoCurso(
	                "Historia del arte", "Explora la evolución del arte desde la prehistoria hasta la actualidad, analizando sus estilos, movimientos y contextos históricos. A través de imágenes, análisis de obras y debates, descubrirás cómo el arte refleja la cultura y el pensamiento de cada época. Ideal para amantes del arte y la historia.", 
	                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Pistachio_vera.jpg/800px-Pistachio_vera.jpg" ,true));
	    }
		
                
		scroll = new ScrollCursos(VentanaCursos.this, selector, componentes);
		
	}
    
	private void manejadorCrearCurso() {
			botonCrearCurso.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {		
					// Lanzar la ventana emergente
					EmergenteFichero emergente = new EmergenteFichero(VentanaCursos.this);
					emergente.mostrar();

					// Obtener resultado
					emergente.obtenerFichero().ifPresentOrElse( (ruta) -> {
						rutaJSON = ruta;
///////////////////// AQUÍ TENGO QUE AÑADIR CURSO POR EL CONTROLADOR ///////////////////////
						//Controlador.crearCurso(rutaJSON);
					},
							() ->  {return;} 
					);
				}
			});
	}
		
}
