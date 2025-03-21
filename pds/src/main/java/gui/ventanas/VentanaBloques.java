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
import gui.emergentes.EmergenteClasificacion;
import gui.info.InfoBloque;
import gui.info.InfoCurso;
import gui.scroll.Scroll;
import gui.scroll.ScrollBloques;

@SuppressWarnings("serial")
public class VentanaBloques extends VentanaMenu {
	
	/* Componentes de organización */
	private ComponenteCurso cursoSeleccionado;
	private JLabel etiquetaBienvenida;
	private JButton botonVolver;
		
	public VentanaBloques(SelectorVentana selector, ComponenteCurso cursoSeleccionado) {
	        super(selector);
	        
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
		for (int i = 1; i < 5; i++) {
	        componentes[i] = new ComponenteBloque(new InfoBloque(
	                "Arte renacentista", "Descubre el esplendor del Renacimiento, una era de innovación artística y cultural que transformó la historia del arte. Exploraremos las obras maestras de artistas como Leonardo da Vinci, Miguel Ángel y Rafael, analizando sus técnicas, influencias y el contexto histórico que dio forma a este movimiento. Ideal para apasionados del arte y la historia.", 
	                //"C:/Users/aleja/git/historiApp/pds/resources/" + (new Random().nextInt(5)+1) + ".png", true));
	                "C:\\Users\\jorge\\git\\repository\\proyectoPDS\\historiApp\\pds\\resources\\" + (new Random().nextInt(5)+1) + ".png", true));
	    }
		componentes[0] = new ComponenteBloque(new InfoBloque(
                "Arte moderno", "Explora las revoluciones artísticas desde finales del siglo XIX hasta el siglo XX, con movimientos como el impresionismo, cubismo, surrealismo y expresionismo. Analizaremos las obras de artistas como Van Gogh, Picasso y Dalí, comprendiendo su impacto en la evolución del arte. Ideal para quienes desean adentrarse en la creatividad y las vanguardias modernas.", 
                //"C:/Users/aleja/git/historiApp/pds/resources/" + (new Random().nextInt(5)+1) + ".png", true));
                "C:\\Users\\jorge\\git\\repository\\proyectoPDS\\historiApp\\pds\\resources\\" + (new Random().nextInt(5)+1) + ".png", true));
		scroll = new ScrollBloques(VentanaBloques.this, selector, componentes);
		
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
