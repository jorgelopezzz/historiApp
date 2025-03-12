package gui.scroll;

import javax.swing.JFrame;

import gui.componentes.Componente;
import gui.componentes.ComponenteCurso;
import gui.emergentes.EmergenteSiNo;
import gui.ventanas.SelectorVentana;
import gui.ventanas.VentanaBloques;
import gui.ventanas.VentanaCursos;

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
	                    //ComponenteCurso seleccionado = (ComponenteCurso) listaComponentes.getModel().getElementAt(index);
	                    //EmergenteSiNo emergente = new EmergenteSiNo(ventanaMadre, "ey");
	    				//emergente.mostrar();
	    				//if(emergente.obtenerRespuesta().orElse(false))
	                		int rol = 0;
	    					selector.cambiarVentana(new VentanaCursos(selector, rol)); // ESTO ES SOLO POR QUE SE VEA ALGO
	    				// AHORA BIEN, A LO MEJOR ES INCORRECTO QUE SE CREE UNA NUEVA VENTANA CURSOS SIEMPRE
	                }
	            }
	        }
	    });
	}

}
