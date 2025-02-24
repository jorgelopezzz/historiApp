package gui.ventanas;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.GestorGUI;

@SuppressWarnings("serial")
public abstract class Ventana extends JFrame {
	
	/* Gestión de comportamiento */
	protected final SelectorVentana selector;
	
	/* Dimensiones */
	protected static final int ANCHO_VENTANA = 800;
	protected static final int ALTO_VENTANA = 600;
	
	public Ventana(SelectorVentana selector, String tituloVentana, Color colorFondo) {
		super(); 
		this.selector = selector; 
		
		/* Configuración de aspectos gráficos */
		setTitle(tituloVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(colorFondo);
		GestorGUI.fijarTamano(ANCHO_VENTANA, ALTO_VENTANA, this);
		
		construir();
	}

	public void cerrar() {
		super.dispose();
	}
	public void mostrar() {
		super.setVisible(true);
	}
	
	protected abstract void construir();
	
}
