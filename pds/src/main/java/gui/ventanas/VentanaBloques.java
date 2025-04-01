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
import gui.componentes.contenidos.ComponenteBloque;
import gui.componentes.contenidos.ComponenteCurso;
import gui.info.contenidos.InfoBloque;
import gui.scroll.ScrollBloques;

@SuppressWarnings("serial")
public class VentanaBloques extends VentanaMenu {
	
	/* Componentes de organización */
	private ComponenteCurso cursoSeleccionado;
	private JLabel etiquetaBienvenida;
	private JButton botonVolver;
		
	/* Método de aprendizaje */
	private String metodoAprendizaje;
	
	public VentanaBloques(SelectorVentana selector, ComponenteCurso cursoSeleccionado, String metodoAprendizaje) {
	        super(selector);
	        
	        this.cursoSeleccionado = cursoSeleccionado;
	        this.metodoAprendizaje = metodoAprendizaje;
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
	    
	    etiquetaBienvenida = new JLabel("A continuación, puedes elegir tu bloque de contenidos.");
	    GestorGUI.configurarEtiqueta(etiquetaBienvenida, false, GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getFuenteTexto());
	    panelInicial.add(etiquetaBienvenida, BorderLayout.WEST);
	    

	    panelInicial.add(Box.createHorizontalGlue());
	    
	    botonVolver = GestorGUI.getBotonPredeterminadoLargo("Volver a cursos");
	    manejadorVolver();
	    
	    panelInicial.add(botonVolver, BorderLayout.EAST);
	    
	    ///
	    panelInicial.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
	    ///
	}
    
	@Override
	protected void construirScrollMenu() {
		
		//controlador
		
		ComponenteBloque[] componentes = new ComponenteBloque[5];
		for (int i = 0; i < 5; i++) {
	        componentes[i] = new ComponenteBloque(new InfoBloque(
	                "Arte renacentista", "Descubre el esplendor del Renacimiento, una era de innovación artística y cultural que transformó la historia del arte. Exploraremos las obras maestras de artistas como Leonardo da Vinci, Miguel Ángel y Rafael, analizando sus técnicas, influencias y el contexto histórico que dio forma a este movimiento. Ideal para apasionados del arte y la historia.", 
	                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Pistachio_vera.jpg/800px-Pistachio_vera.jpg", true));
	    }
		scroll = new ScrollBloques(VentanaBloques.this, selector, componentes, cursoSeleccionado);
		
	}
	
	private void manejadorVolver() {
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rol = 1;
				selector.cambiarVentana(new VentanaCursos(selector, rol));
			}
		});
	}
		
}
