package gui.ventanas;

import java.awt.EventQueue;

public class SelectorVentana {
	private Ventana ventanaActual;
	
	public SelectorVentana() {
		//ventanaActual = new VentanaLogin(this);
		ventanaActual = new VentanaCursos(this);
		ventanaActual.mostrar();
	}
	
	public void cambiarVentana(Ventana ventana) {
		ventanaActual.cerrar();
		ventanaActual = ventana;
		ventanaActual.mostrar();
	}
	
}
