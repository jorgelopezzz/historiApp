package gui.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dominio.HistoriApp;
import dominio.curso.Curso;
import dominio.info.contenidos.InfoBloque;
import dominio.info.contenidos.InfoCurso;
import dominio.metodoAprendizaje.MetodoAprendizaje;
import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.contenidos.ComponenteBloque;
import gui.componentes.contenidos.ComponenteContenido;
import gui.componentes.contenidos.ComponenteCurso;
import gui.emergentes.EmergenteValoracion;
import gui.scroll.ScrollBloques;

@SuppressWarnings("serial")
public class VentanaBloques extends VentanaMenu {
	
	/* Componentes de organización */
	//private ComponenteCurso cursoSeleccionado;
	private JLabel etiquetaBienvenida;
	private JButton botonVolver;
	private JButton botonValorar;
		
	/* Método de aprendizaje */
	//private MetodoAprendizaje metodoAprendizaje;
	
	public VentanaBloques(SelectorVentana selector) {
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
	    
	    etiquetaBienvenida = new JLabel("Elige un bloque de contenidos.");
	    GestorGUI.configurarEtiqueta(etiquetaBienvenida, false, GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getFuenteTexto());
	    panelInicial.add(etiquetaBienvenida);
	    panelInicial.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_GRANDE));

	    botonValorar = GestorGUI.getBotonPredeterminadoLargo("Valorar curso");
	    manejadorValorar();
	    
	    panelInicial.add(botonValorar);
	    panelInicial.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
	    
	    botonVolver = GestorGUI.getBotonPredeterminadoLargo("Volver a cursos");
	    manejadorVolver();
	    
	    panelInicial.add(botonVolver);
	    
	    ///
	    panelInicial.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
	    ///
	}
    
	@Override
	protected void construirScrollMenu() {
		
		List<InfoBloque> infoBloques = HistoriApp.INSTANCE.getBloques();
    	
		List<Componente> componentesBloques = infoBloques.stream()
			    .map(ComponenteBloque::new)
			    .collect(Collectors.toList());
		
		scroll = new ScrollBloques(VentanaBloques.this, selector, componentesBloques);
		
	}
	
	private void manejadorValorar() {
		botonValorar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmergenteValoracion emergenteValoracion = new EmergenteValoracion(VentanaBloques.this);
				emergenteValoracion.mostrar();
			}
		});
	}
	
	private void manejadorVolver() {
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selector.cambiarVentana(new VentanaCursos(selector));
			}
		});
	}
		
}
