package gui.ventanas;

import java.awt.Color;

import javax.swing.JFrame;

import gui.GestorGUI;

@SuppressWarnings("serial")
public abstract class Ventana extends JFrame {
	
	/* Gestión de comportamiento */
	protected final SelectorVentana selector;
	
	/* Dimensiones */
	protected static final int ANCHO_VENTANA = 800;
	protected static final int ALTO_VENTANA = 600;
	
	protected Ventana(SelectorVentana selector, String tituloVentana, Color colorFondo, int closeAction) {
		super(); 
		this.selector = selector; 
		
		/* Configuración de aspectos gráficos */
		setTitle(tituloVentana);
		setDefaultCloseOperation(closeAction);
		getContentPane().setBackground(colorFondo);
		GestorGUI.fijarTamano(ANCHO_VENTANA, ALTO_VENTANA, this);
		
		construir();
	}
	
	protected Ventana(SelectorVentana selector, String tituloVentana, Color colorFondo) {
		this(selector, tituloVentana, colorFondo, JFrame.DISPOSE_ON_CLOSE);
	}

	public void cerrar() {
		super.dispose();
	}
	public void mostrar() {
		super.setVisible(true);
	}
	
	protected abstract void construir();
	
}
