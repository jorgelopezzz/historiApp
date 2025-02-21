package gui.emergentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GestorGUI;

public abstract class Emergente extends JDialog {
	
	protected JFrame ventanaMadre;
	
	/* Dimensiones */
	protected static final int ANCHO_EMERGENTE = 500;
	protected static final int ALTO_EMERGENTE = 600;
	
	public Emergente(String tituloEmergente, Color colorFondo, JFrame ventanaMadre) {
		super(ventanaMadre, GestorGUI.NOMBRE_APP + " " + tituloEmergente, true);
		
		/* Aspectos gráficos */
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(colorFondo);
		GestorGUI.fijarTamano(ANCHO_EMERGENTE, ALTO_EMERGENTE, this);
		
		/* Para superposición de emergentes */
		this.ventanaMadre = ventanaMadre;
	}
	
	public void cerrar() {
		super.dispose();
	}

	public void mostrar() {
		super.setLocationRelativeTo(ventanaMadre);
		super.setVisible(true);
	}
	
	protected void manejadorSalir(JButton botonSalir) {
		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
	}
	
	protected static JPanel construirPanelImagen(String ruta) {
		JPanel panelImagen = new JPanel();
		/*
		etiquetaImagen = new JLabel();
		etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
		etiquetaImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
		*/
		return panelImagen;
	}
	
}
