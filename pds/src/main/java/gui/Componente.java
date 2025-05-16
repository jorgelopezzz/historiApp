package gui;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import utils.GestorGUI;

public class Componente extends JPanel {

	/* Dimensiones */
	public static final int ANCHO_COMPONENTE = 375;
	public static final int ALTO_COMPONENTE = 400;
	
	protected static final int ANCHO_IMAGEN = 300;
	protected static final int ALTO_IMAGEN = 150;
	
	public Componente(int ancho, int alto) {
		super();
		/* Configuración del panel */
		setBackground(GestorGUI.getInstancia().getColorMedio());
		GestorGUI.fijarTamano(ancho, alto, this);
		setBorder(new LineBorder(GestorGUI.getInstancia().getColorBlanco()));
	}
	
	public Componente() {
		this(ANCHO_COMPONENTE, ALTO_COMPONENTE);
	}
	
	public Componente(LayoutManager layout) {
		this();
		GestorGUI.configurarPanel(this, layout, true);
	}
	
	public void seleccionar() {
		setBackground(GestorGUI.getInstancia().getColorOscuro());
	}

	public void deseleccionar() {
		setBackground(GestorGUI.getInstancia().getColorMedio());
	}
	
}
