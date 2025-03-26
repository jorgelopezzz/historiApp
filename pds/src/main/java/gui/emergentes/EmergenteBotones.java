package gui.emergentes;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gui.GestorGUI;

@SuppressWarnings("serial")
public abstract class EmergenteBotones extends Emergente{
	// esta es la clase padre de emergenteMensaje y emergenteSiNo
	// hay que adaptarlas (emergenteMensaje ya está hecha y emergenteSiNo le falta añadir un Jpanel para meter los botones
	// basta con crear en ESTA CLASE una función abstracta añadir botones e implementarla en los hijos
	
	
	/* Componentes de organización */
	private JPanel panelEnvolvente;
	protected JPanel panelTexto;
	
	/* Componentes de información */
	private JLabel etiquetaImagen;
	private JTextArea areaTexto;
	
	/* Dimensiones */
	private static final int ANCHO_EMERGENTE = 400;
	private static final int ALTO_EMERGENTE = 300;
	
	private static final int ANCHO_CUADRO = 300;
	private static final int ALTO_CUADRO = 160;
	protected static final int MARGEN = 5;
	
	private static final int LADO_IMAGEN = 50; 
	private static final int NUMERO_COLUMNAS_TEXTO = 25;
	
	/* Rutas imágenes */
	private static final String RUTA_ALERTA = "/alerta.png";
	
	/* Constructor */
	public EmergenteBotones(JFrame ventanaMadre, String mensaje) {
		super(GestorGUI.NOMBRE_APP + " Mensaje", GestorGUI.getInstancia().getColorClaro(), ventanaMadre, ANCHO_EMERGENTE, ALTO_EMERGENTE);
		
		areaTexto.setText(mensaje);
		panelTexto.revalidate();
	}
	
	/* Métodos */
	
	@Override
	protected void construir() {
		
		/* Panel envolvente */
		panelEnvolvente = new JPanel();
		GestorGUI.centrarPanel(panelEnvolvente, this);
		GestorGUI.fijarTamano(ANCHO_CUADRO, ALTO_CUADRO, panelEnvolvente);
		
		/* Margen */
		panelEnvolvente.setBorder(new EmptyBorder(MARGEN, MARGEN, MARGEN, MARGEN));
		
		/* Panel texto */
		panelTexto = new JPanel();
		GestorGUI.configurarPanel(panelTexto, new BoxLayout(panelTexto, BoxLayout.Y_AXIS), false);
		
		/* Etiqueta imagen */
		etiquetaImagen = new JLabel();
		etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(RUTA_ALERTA, LADO_IMAGEN, LADO_IMAGEN));
		etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		/* Campo texto */
		areaTexto = GestorGUI.crearAreaTexto("", GestorGUI.getInstancia().getFuenteTexto(),
				GestorGUI.getInstancia().getColorOscuro(), NUMERO_COLUMNAS_TEXTO);	
		
		/* Montaje */
		panelTexto.add(etiquetaImagen);
		panelTexto.add(Box.createVerticalStrut(3*MARGEN));
		panelTexto.add(areaTexto);
		panelTexto.add(Box.createVerticalStrut(15));

		botones_montaje();
		
		panelEnvolvente.add(panelTexto);
		
	}
	
	protected abstract void botones_montaje();
	
}
