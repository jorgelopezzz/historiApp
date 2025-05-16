package gui;

import java.util.List;

import javax.swing.JFrame;

import controlador.HistoriApp;


@SuppressWarnings("serial")
public class ScrollBloques extends Scroll {
	

	public ScrollBloques(JFrame ventanaMadre, SelectorVentana selector, List<Componente> componentes) {
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
	                    ComponenteBloque bloqueSeleccionado = (ComponenteBloque) listaComponentes.getModel().getElementAt(index);
	                    EmergenteSiNo emergente = new EmergenteSiNo(ventanaMadre, "¿Deseas acceder a este bloque de contenidos?");
	    				emergente.mostrar();
	    				if(emergente.obtenerRespuesta().orElse(false)) {
	    					///
	    					HistoriApp.INSTANCE.realizarBloque(bloqueSeleccionado.getTitulo());
	    					///
	    					selector.cambiarVentana(new VentanaTareas(selector));
	    				}
	    				
	                }
	            }
	        }
	    });
	}

}
