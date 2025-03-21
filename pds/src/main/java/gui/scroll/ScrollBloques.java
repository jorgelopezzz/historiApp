package gui.scroll;

import javax.swing.JFrame;

import gui.componentes.Componente;
import gui.componentes.ComponenteBloque;
import gui.componentes.ComponenteCurso;
import gui.emergentes.EmergenteSiNo;
import gui.ventanas.SelectorVentana;
import gui.ventanas.VentanaBloques;
import gui.ventanas.VentanaCursos;
import gui.ventanas.VentanaTareas;

@SuppressWarnings("serial")
public class ScrollBloques extends Scroll {

	public ScrollBloques(JFrame ventanaMadre, SelectorVentana selector, Componente[] componentes) {
		super(ventanaMadre, selector, componentes);
	}

	@Override
	protected void detectarDobleClick() {
		listaComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            if (evt.getClickCount() == 2) {
	                int index = listaComponentes.locationToIndex(evt.getPoint());
	                if (index != -1) {
	                    ComponenteBloque seleccionado = (ComponenteBloque) listaComponentes.getModel().getElementAt(index);
	                    EmergenteSiNo emergente = new EmergenteSiNo(ventanaMadre, "¿Deseas acceder a este bloque de contenidos?");
	    				emergente.mostrar();
	    				if(emergente.obtenerRespuesta().orElse(false))
	    					selector.cambiarVentana(new VentanaTareas(selector, seleccionado)); // ESTO ES SOLO POR QUE SE VEA ALGO
	    				// AHORA BIEN, A LO MEJOR ES INCORRECTO QUE SE CREE UNA NUEVA VENTANA CURSOS SIEMPRE
	                }
	            }
	        }
	    });
	}

}
