package gui.scroll;

import javax.swing.JFrame;

import gui.componentes.Componente;
import gui.componentes.ComponenteCurso;
import gui.emergentes.EmergenteSiNo;
import gui.ventanas.SelectorVentana;
import gui.ventanas.VentanaBloques;

@SuppressWarnings("serial")
public class ScrollCursos extends Scroll {

	public ScrollCursos(JFrame ventanaMadre, SelectorVentana selector, Componente[] componentes) {
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
	                    ComponenteCurso seleccionado = (ComponenteCurso) listaComponentes.getModel().getElementAt(index);
	                    EmergenteSiNo emergente = new EmergenteSiNo(ventanaMadre, "¿Deseas acceder a este curso?");
	    				emergente.mostrar();
	    				if(emergente.obtenerRespuesta().orElse(false))
	    					selector.cambiarVentana(new VentanaBloques(selector, seleccionado));
	                }
	            }
	        }
	    });
	}

}
