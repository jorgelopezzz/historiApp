package gui.emergentes;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.GestorGUI;

public class EmergenteTriple extends EmergenteSiNo{

	public EmergenteTriple(JFrame ventanaMadre, String mensaje) {
		super(ventanaMadre, mensaje);
	}

	@Override
	protected JPanel construirPanelEnvolvente() {
		JPanel panel = super.construirPanelEnvolvente();
		GestorGUI.fijarTamano(250, 200, panel);
		return panel;
	}
	
	@Override
	protected void botones_montaje() {
		/* Panel SiNo */
		panelBotones = new JPanel();
		GestorGUI.configurarPanel(panelBotones, new BoxLayout(panelBotones, BoxLayout.Y_AXIS), false);

		JPanel panelSuperior = construirPanelSiNo();
		panelSuperior.setAlignmentX(Component.CENTER_ALIGNMENT);

		/* Botón ver valoraciones */
		JButton botonVer = GestorGUI.getBotonPredeterminadoLargo("Ver valoraciones");
		botonVer.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		/* Montaje */
		panelBotones.add(panelSuperior);
		panelBotones.add(Box.createVerticalStrut(MARGEN));
		panelBotones.add(botonVer);
		
		GestorGUI.fijarTamano(350, 300, this);
		panelTexto.add(panelBotones);
	}
	
}
