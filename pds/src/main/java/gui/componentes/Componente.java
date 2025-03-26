package gui.componentes;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gui.GestorGUI;

public class Componente extends JPanel {

	/* Dimensiones */
	public static final int ANCHO_COMPONENTE = 375;
	public static final int ALTO_COMPONENTE = 400;
	
	protected static final int ANCHO_IMAGEN = 300;
	protected static final int ALTO_IMAGEN = 150;
	
	public Componente() {
		super();
		/* Configuración del panel */
		setBackground(GestorGUI.getInstancia().getColorMedio());
		GestorGUI.fijarTamano(ANCHO_COMPONENTE, ALTO_COMPONENTE, this);
		setBorder(new LineBorder(GestorGUI.getInstancia().getColorBlanco()));
	}
	
	public Componente(LayoutManager layout) {
		this();
		GestorGUI.configurarPanel(this, layout, true);
	}
	
}
