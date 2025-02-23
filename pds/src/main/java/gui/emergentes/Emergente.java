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
	protected static final int ANCHO_EMERGENTE_PREDET = 700;
	protected static final int ALTO_EMERGENTE_PREDET = 550;
	
	public Emergente(String tituloEmergente, Color colorFondo, JFrame ventanaMadre, int ancho, int alto) {
		super(ventanaMadre, GestorGUI.NOMBRE_APP + " " + tituloEmergente, true);
		
		/* Aspectos gráficos */
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(colorFondo);
		GestorGUI.fijarTamano(ancho, alto, this);
		
		/* Para superposición de emergentes */
		this.ventanaMadre = ventanaMadre;
		construir();
	}
	
	public Emergente(String tituloEmergente, Color colorFondo, JFrame ventanaMadre) {
		this(tituloEmergente, colorFondo, ventanaMadre, ANCHO_EMERGENTE_PREDET, ALTO_EMERGENTE_PREDET);
	}
	
	public void cerrar() {
		super.dispose();
	}
	
	protected abstract void construir();
	
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
	

	
}
