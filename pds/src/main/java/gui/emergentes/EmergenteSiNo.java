package gui.emergentes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gui.GestorGUI;

public class EmergenteSiNo extends EmergenteBotones {

	/* Botones */
	private JButton botonNo;
	private JButton botonSi;
	
	/* Atributos a obtener */
	private boolean opcion;

	/* Constructor */
	public EmergenteSiNo(JFrame ventanaMadre, String mensaje) {
		super(ventanaMadre, mensaje);
		
		opcion = false;
	}

	
	public Optional<Boolean> obtenerRespuesta() {
		return Optional.ofNullable(opcion);
	}
	
	@Override
	protected void botones_montaje() {	
		
		/* Botón No*/
		botonNo = GestorGUI.getBotonPredeterminado("No");
		botonNo.setAlignmentX(Component.CENTER_ALIGNMENT);
		manejadorNo();
		
		/* Botón Sí*/
		botonSi = GestorGUI.getBotonPredeterminado("Sí");
		botonSi.setAlignmentX(Component.CENTER_ALIGNMENT);
		manejadorSi();
		
		/* Montaje */
		panelTexto.add(botonNo);
		panelTexto.add(botonSi);
	}
	
	private JButton manejadorNo() {
		botonNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcion = false;
				dispose();
			}
		});
		
		return botonNo;
	}
	
	private JButton manejadorSi() {
		botonSi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcion = true;
				dispose();
			}
		});
		
		return botonSi;
	}
	
	
}
