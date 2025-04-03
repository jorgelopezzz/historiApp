package gui.scroll;

import java.util.List;

import javax.swing.JFrame;

import dominio.HistoriApp;
import dominio.metodoAprendizaje.MetodoAprendizaje;
import gui.componentes.contenidos.ComponenteContenido;
import gui.componentes.contenidos.ComponenteCurso;
import gui.emergentes.EmergenteMetodoAprendizaje;
import gui.emergentes.EmergenteSiNo;
import gui.ventanas.SelectorVentana;
import gui.ventanas.VentanaBloques;

@SuppressWarnings("serial")
public class ScrollCursos extends Scroll {

	public ScrollCursos(JFrame ventanaMadre, SelectorVentana selector, List<ComponenteContenido> componentesCursos) {
		super(ventanaMadre, selector, componentesCursos);
	}

	@Override
	protected void detectarDobleClick() {
		listaComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            if (evt.getClickCount() == 2) {
	                int index = listaComponentes.locationToIndex(evt.getPoint());
	                if (index != -1) {
	                    ComponenteCurso cursoSeleccionado = (ComponenteCurso) listaComponentes.getModel().getElementAt(index);
	                    EmergenteSiNo emergente1 = new EmergenteSiNo(ventanaMadre, "¿Deseas acceder a este curso?");
	    				emergente1.mostrar();
	    				if(emergente1.obtenerRespuesta().orElse(false)) {
	    					EmergenteMetodoAprendizaje emergente2 = new EmergenteMetodoAprendizaje(ventanaMadre);
		    				emergente2.mostrar();
		    				MetodoAprendizaje metodoAprendizaje = emergente2.obtenerRespuesta().orElse(null);
		    				///
		    				HistoriApp.INSTANCE.realizarCurso(cursoSeleccionado.getTitulo(), metodoAprendizaje);
		    				///
	    					selector.cambiarVentana(new VentanaBloques(selector));
	    				}
	                }
	            }
	        }
	    });
	}

}
