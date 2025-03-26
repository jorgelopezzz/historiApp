package gui.componentes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gui.GestorGUI;

public class Componente extends JPanel {

	public static final int ANCHO_COMPONENTE = 375;
	public static final int ALTO_COMPONENTE = 400;

	public Componente() {
		super();
		/* Configuración del panel */
		GestorGUI.configurarPanel(this, new BorderLayout(), GestorGUI.getInstancia().getColorMedio());
		GestorGUI.fijarTamano(ANCHO_COMPONENTE, ALTO_COMPONENTE, this);
		setBorder(new LineBorder(GestorGUI.getInstancia().getColorBlanco()));
	}

}
