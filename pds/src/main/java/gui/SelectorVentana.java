package gui;

public class SelectorVentana {
	private Ventana ventanaActual;
	
	public SelectorVentana() {
		ventanaActual = new VentanaLogin(this);
		ventanaActual.mostrar();
	}
	
	public void cambiarVentana(Ventana ventana) {
		ventanaActual.cerrar();
		ventanaActual = ventana;
		ventanaActual.mostrar();
	}
	
}
