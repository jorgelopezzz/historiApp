package gui.emergentes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.GestorGUI;

@SuppressWarnings("serial")
public class EmergenteMensaje extends EmergenteBotones {
	
	/* Botones */
	private JButton botonAceptar;

	
	public EmergenteMensaje(JFrame ventanaMadre, String mensaje) {
		super(ventanaMadre, mensaje);
		
		construir();
	}
	
	@Override
	protected void botones_montaje() {
		
		/* Botón */
		botonAceptar = GestorGUI.getBotonPredeterminado("Aceptar");
		botonAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		manejadorAceptar();
		
		/* Montaje */
		panelTexto.add(botonAceptar);
		
	}
	

	private JButton manejadorAceptar() {
		botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		return botonAceptar;
	}
	
	
}
