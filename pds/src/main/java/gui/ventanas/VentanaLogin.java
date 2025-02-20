package gui.ventanas;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.GestorGUI;

public class VentanaLogin extends Ventana {

	/* Componentes de organización */
	private JPanel panelCentral;
	
	/* Componentes de datos */
	private JTextField campoNombre;
	private JPasswordField campoContrasena; 
	
	/* 
	
	/* Atributos a recabar */
	private String nombre;
	private String contrasena;
	
	
	
	public VentanaLogin(SelectorVentana selector) {
		super(selector, GestorGUI.NOMBRE_APP + " Login", GestorGUI.getInstancia().getColorMedio());
		construir();
	}
	
	protected void construir() {
		
		/* Configuración del panel central */
		panelCentral = new JPanel();
		GestorGUI.configurarPanel(panelCentral, GestorGUI.getInstancia().getColorBlanco(), 
				new BoxLayout(panelCentral, BoxLayout.Y_AXIS), (int) ANCHO_VENTANA / 2, (int) ALTO_VENTANA / 2);
		GestorGUI.centrarPanel(panelCentral, this);
		
		/* Etiqueta título */
		JLabel etiquetaTitulo = new JLabel(GestorGUI.NOMBRE_APP);
		GestorGUI.configurarEtiqueta(etiquetaTitulo, 
				false, GestorGUI.getInstancia().getColorMedio(), GestorGUI.getInstancia().getFuenteTitulo());
		panelCentral.add(etiquetaTitulo);
		
		
	}
	
}
