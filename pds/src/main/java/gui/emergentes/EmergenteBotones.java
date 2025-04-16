package gui.emergentes;

import java.awt.Component;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.GestorGUI;

@SuppressWarnings("serial")
public abstract class EmergenteBotones extends Emergente {

	/* Componentes de organización */
	private JPanel panelEnvolvente;
	protected JPanel panelTexto;

	/* Componentes de información */
	private JLabel etiquetaImagen;
	private JTextArea areaTexto;

	/* Dimensiones */
	private static final int ANCHO_EMERGENTE = 400;
	private static final int ALTO_EMERGENTE = 300;

	protected static final int ANCHO_CUADRO = 300;
	protected static final int ALTO_CUADRO = 160;
	protected static final int MARGEN = 5;

	protected static final int LADO_IMAGEN = 50;
	private static final int NUMERO_COLUMNAS_TEXTO = 25;

	/* Rutas imágenes */
	protected static final String RUTA_ALERTA = "/alerta.png";

	/* Mensaje temporal */
	private String mensaje;

	/* Constructor */
	public EmergenteBotones(JFrame ventanaMadre, String mensaje) {
		super(" Mensaje", GestorGUI.getInstancia().getColorClaro(), ventanaMadre,
				ANCHO_EMERGENTE, ALTO_EMERGENTE);
		this.mensaje = mensaje;
	}

	/* Métodos */

	protected JPanel construirPanelTexto() {
		JPanel panel = new JPanel();
		GestorGUI.configurarPanel(panel, new BoxLayout(panel, BoxLayout.Y_AXIS), false);
		return panel;
	}
	
	protected JPanel construirPanelEnvolvente() {
		/* Panel envolvente */
		JPanel panel = new JPanel();
		GestorGUI.centrarPanel(panel, this);
		panel.setBorder(new EmptyBorder(MARGEN, MARGEN, MARGEN, MARGEN));
		GestorGUI.fijarTamano(ANCHO_CUADRO, ALTO_CUADRO, panel);
		return panel;
	}
	
	@Override
	protected void construir() {

		/* Panel envolvente */
		panelEnvolvente = construirPanelEnvolvente();

		/* Panel texto */
		panelTexto = construirPanelTexto(); 

		/* Etiqueta imagen */
		etiquetaImagen = new JLabel();
		etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(RUTA_ALERTA, LADO_IMAGEN, LADO_IMAGEN));
		etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

		/* Campo texto */
		areaTexto = GestorGUI.crearAreaTexto(this.mensaje, GestorGUI.getInstancia().getFuenteTexto(),
				GestorGUI.getInstancia().getColorOscuro(), NUMERO_COLUMNAS_TEXTO);

		/* Montaje */
		panelTexto.add(etiquetaImagen);
		panelTexto.add(Box.createVerticalStrut(3 * MARGEN));
		panelTexto.add(areaTexto);
		panelTexto.add(Box.createVerticalStrut(15));

		botones_montaje();

		panelEnvolvente.add(panelTexto);
	}
	
	protected abstract void botones_montaje();
}
