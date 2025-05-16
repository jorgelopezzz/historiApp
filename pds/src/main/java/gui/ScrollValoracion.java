package gui;

import java.util.List;

import javax.swing.JFrame;

public class ScrollValoracion extends Scroll {

	public ScrollValoracion(JFrame ventanaMadre, List<Componente> componentes) {
		super(ventanaMadre, componentes);
	}
	
	@Override
	protected void detectarDobleClick() {
		// No hacer nada
	}

}
