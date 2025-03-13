package gui.emergentes;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.GestorGUI;

//@SuppressWarnings("serial")
public class EmergenteEstadisticas extends Emergente { 

	/* Componentes de organización */
	private JPanel panelEnvolvente;

	private JTextArea areaTexto;

	/* Dimensiones */
	private static final int ANCHO_ESTADISTICA = 600;
	private static final int ALTO_ESTADISTICA = 60;

	private static final int ANCHO_CUADRO = 650;
	private static final int ALTO_CUADRO = 530;

	private static final int NUMERO_COLUMNAS_TEXTO = 25;


	public EmergenteEstadisticas(JFrame ventanaMadre) {
		super("Estadísticas", GestorGUI.getInstancia().getColorClaro(), ventanaMadre);
		
	}

	@Override
	protected void construir() {
		
		/* Panel envolvente */
		panelEnvolvente = new JPanel();
		GestorGUI.centrarPanel(panelEnvolvente, this);
		GestorGUI.fijarTamano(ANCHO_CUADRO, ALTO_CUADRO, panelEnvolvente);


		GestorGUI.configurarPanel(panelEnvolvente, new BoxLayout(panelEnvolvente, BoxLayout.Y_AXIS), false);

		/* Panel */
		

		areaTexto = GestorGUI.crearAreaTexto("", GestorGUI.getInstancia().getFuenteTexto(),
				GestorGUI.getInstancia().getColorOscuro(), NUMERO_COLUMNAS_TEXTO);	
		
		areaTexto.setText("Estadísticas:");
		areaTexto.revalidate();

		panelEnvolvente.add(Box.createVerticalStrut(15));
		panelEnvolvente.add(areaTexto);
		panelEnvolvente.add(Box.createVerticalStrut(15));

		construirEstadistica("Cursos completados", 7);
		construirEstadistica("Bloques completados", 7);
		construirEstadistica("Puntuación", 7);
		construirEstadistica("Tiempo de uso", 7);
		construirEstadistica("Tiempo de uso diario", 7);
		construirEstadistica("Mejor racha", 7);

	}

	private void construirEstadistica(String estadistica, int valor){
		JPanel panel = new JPanel();
		GestorGUI.configurarPanel(panel, new BoxLayout(panel, BoxLayout.X_AXIS), GestorGUI.getInstancia().getColorBlanco(), ANCHO_ESTADISTICA, ALTO_ESTADISTICA);

		JTextArea text = GestorGUI.crearAreaTexto("", GestorGUI.getInstancia().getFuenteTexto(),
		GestorGUI.getInstancia().getColorOscuro(), NUMERO_COLUMNAS_TEXTO);
		
		text.setText(estadistica + " :     " + Integer.toString(valor));

		panel.add(Box.createVerticalStrut(10));
		panel.add(text);
		panelEnvolvente.add(panel);
		panelEnvolvente.add(Box.createVerticalStrut(15));
	}

}
