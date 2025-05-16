package gui;

import java.util.List;

import javax.swing.JFrame;

import controlador.HistoriApp;
import modelo.MetodoAprendizaje;

@SuppressWarnings("serial")
public class ScrollCursos extends Scroll {

	public ScrollCursos(JFrame ventanaMadre, SelectorVentana selector, List<Componente> componentesCursos) {
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
	                    EmergenteTriple emergente;
	                    if(HistoriApp.INSTANCE.usuarioMatriculado(cursoSeleccionado.getTitulo())) {
	                    	emergente = new EmergenteTriple(ventanaMadre, cursoSeleccionado.getTitulo(), "¿Deseas acceder a este curso?");
	                    	emergente.mostrar();
	                    	if(emergente.obtenerRespuesta().orElse(false)) {
	                    		HistoriApp.INSTANCE.realizarCurso(cursoSeleccionado.getTitulo());
		    					selector.cambiarVentana(new VentanaBloques(selector));
	                    	}
	                    } else {
	                    	emergente = new EmergenteTriple(ventanaMadre, cursoSeleccionado.getTitulo(), "¿Deseas matricularte en este curso?");
	                    	emergente.mostrar();
	                    	if(emergente.obtenerRespuesta().orElse(false)) {	                    		
	                    		EmergenteMetodoAprendizaje emergenteMetodo = new EmergenteMetodoAprendizaje(ventanaMadre);
	    						emergenteMetodo.mostrar();
	                    		MetodoAprendizaje metodoAprendizaje = emergenteMetodo.obtenerRespuesta().orElse(null);
	                    		HistoriApp.INSTANCE.matricularCurso(cursoSeleccionado.getTitulo(), metodoAprendizaje);
	                    	}
	                    }
	                    
	               }
	            }
	        }
	    });
	}

}
