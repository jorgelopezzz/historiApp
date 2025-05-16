package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.HistoriApp;
import modelo.InfoCurso;
import utils.GestorGUI;

@SuppressWarnings("serial")
public class VentanaCursos extends VentanaMenu {
	
	/* Componentes de organización */
	JLabel etiquetaBienvenida;
	JButton botonCrearCurso;
	
	/* Atributos a recabar */
	private String rutaJSON;
	
	/* Tipo de rol */
	protected int rol;
	
	public VentanaCursos(SelectorVentana selector) {
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
	    
	    etiquetaBienvenida = new JLabel("Bienvenido, a continuación puedes elegir tu curso.");
	    GestorGUI.configurarEtiqueta(etiquetaBienvenida, false, GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getFuenteTexto());
	    panelInicial.add(etiquetaBienvenida, BorderLayout.WEST);
	    
	    if(HistoriApp.INSTANCE.esProfesor()) {
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
		
		List<InfoCurso> infoCursos = HistoriApp.INSTANCE.getCursos();
    	
		List<Componente> componentesCursos = infoCursos.stream()
			    .map(ComponenteCurso::new)
			    .collect(Collectors.toList());
                
		scroll = new ScrollCursos(VentanaCursos.this, selector, componentesCursos);
	}
    
	
	protected void actualizarScroll() {
		panelPrincipal.remove(scroll);
		construirScrollMenu();
		panelPrincipal.add(scroll, BorderLayout.SOUTH);
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
						//Añadimos el curso a resources
						HistoriApp.INSTANCE.crearCurso(rutaJSON);
						//Actualizamos VentanaCursos para que muestre el nuevo curso
						actualizarScroll();						 
					},
							() ->  {return;} 
					);
				}
			});
	}
		
}
