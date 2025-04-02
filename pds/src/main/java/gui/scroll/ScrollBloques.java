package gui.scroll;

import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;

import dominio.metodoAprendizaje.MetodoAprendizaje;
import gui.componentes.contenidos.ComponenteBloque;
import gui.componentes.contenidos.ComponenteContenido;
import gui.componentes.contenidos.ComponenteCurso;
import gui.emergentes.EmergenteMetodoAprendizaje;
import gui.emergentes.EmergenteSiNo;
import gui.ventanas.SelectorVentana;
import gui.ventanas.VentanaTareas;

import dominio.metodoAprendizaje.MetodoAprendizaje;


@SuppressWarnings("serial")
public class ScrollBloques extends Scroll {
	
	private ComponenteCurso cursoSeleccionado;
	
	/* Atributos a obtener */

    private Optional<MetodoAprendizaje> metodoSeleccionado;

	public ScrollBloques(JFrame ventanaMadre, SelectorVentana selector, List<ComponenteContenido> componentes, ComponenteCurso cursoSeleccionado) {
		super(ventanaMadre, selector, componentes);
		
		this.cursoSeleccionado = cursoSeleccionado;
	}

	@Override
	protected void detectarDobleClick() {
		listaComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            if (evt.getClickCount() == 2) {
	                int index = listaComponentes.locationToIndex(evt.getPoint());
	                if (index != -1) {
	                    ComponenteBloque bloqueSeleccionado = (ComponenteBloque) listaComponentes.getModel().getElementAt(index);
	                    EmergenteSiNo emergente = new EmergenteSiNo(ventanaMadre, "¿Deseas acceder a este bloque de contenidos?");
	    				emergente.mostrar();
	    				if(emergente.obtenerRespuesta().orElse(false)) {
	    						selector.cambiarVentana(new VentanaTareas(selector, bloqueSeleccionado, cursoSeleccionado));
	    				}
	    				
	                }
	            }
	        }
	    });
	}

}
