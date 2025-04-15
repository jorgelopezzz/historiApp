package gui.emergentes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.GestorGUI;

@SuppressWarnings("serial")
public class EmergenteSiNo extends EmergenteBotones {

	protected JPanel panelBotones;

	/* Botones */
	private JButton botonNo;
	private JButton botonSi;
	
	/* Atributos a obtener */
	private boolean opcion;

	/* Constructor */
	public EmergenteSiNo(JFrame ventanaMadre, String mensaje) {
		super(ventanaMadre, mensaje);
		
		opcion = false;
		
		construir();
	}

	
	public Optional<Boolean> obtenerRespuesta() {
		return Optional.ofNullable(opcion);
	}
	
	@Override
	protected void botones_montaje() {
		/* Panel Botones */
		panelBotones = construirPanelSiNo();
		panelTexto.add(panelBotones);
	}
	
	protected JPanel construirPanelSiNo() {
		JPanel panel = new JPanel();
		GestorGUI.configurarPanel(panel, new BoxLayout(panel, BoxLayout.X_AXIS), false);	
		
		/* Botón No*/
		botonNo = GestorGUI.getBotonPredeterminado("No");
		botonNo.setAlignmentX(Component.CENTER_ALIGNMENT);
		manejadorNo();
		
		/* Botón Sí*/
		botonSi = GestorGUI.getBotonPredeterminado("Sí");
		botonSi.setAlignmentX(Component.CENTER_ALIGNMENT);
		manejadorSi();

		/* Montaje */
		panel.add(botonNo);
		panel.add(Box.createHorizontalStrut(MARGEN));
		panel.add(botonSi);
		return panel;
		
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
