package gui.scroll;

import java.util.List;

import javax.swing.JFrame;

import gui.componentes.Componente;
import gui.ventanas.SelectorVentana;

public class ScrollValoracion extends Scroll {

	public ScrollValoracion(JFrame ventanaMadre, List<Componente> componentes) {
		super(ventanaMadre, componentes);
	}
	
	@Override
	protected void detectarDobleClick() {
		// No hacer nada
	}

}
