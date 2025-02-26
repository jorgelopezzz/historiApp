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

public class EmergenteSiNo extends Emergente {
	
	/* Componentes de organización */
	private JPanel panelEnvolvente;
	private JPanel panelTexto;
	
	/* Componentes de información */
	private JLabel etiquetaImagen;
	private JTextArea areaTexto;
	
	/* Botones */
	private JButton botonNo;
	private JButton botonSi;
	
	/* Dimensiones */
	private static final int ANCHO_EMERGENTE = 400;
	private static final int ALTO_EMERGENTE = 300;
	
	private static final int ANCHO_CUADRO = 300;
	private static final int ALTO_CUADRO = 160;
	private static final int MARGEN = 5;
	
	private static final int LADO_IMAGEN = 50; 
	private static final int NUMERO_COLUMNAS_TEXTO = 25;
	
	/* Rutas imágenes */
	private static final String RUTA_ALERTA = "/alerta.png";
	
	/* Atributos a obtener */
	private boolean opcion;
	
	public EmergenteSiNo(JFrame ventanaMadre, String mensaje) {
		super(GestorGUI.NOMBRE_APP + " Decisión", GestorGUI.getInstancia().getColorClaro(), ventanaMadre, ANCHO_EMERGENTE, ALTO_EMERGENTE);
		
		opcion = false;
		
		areaTexto.setText(mensaje);
		panelTexto.revalidate();
	}
	
	public Optional<Boolean> obtenerRespuesta() {
		return Optional.ofNullable(opcion);
	}
	
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
		
		/* Botón No*/
		botonNo = GestorGUI.getBotonPredeterminado("No");
		botonNo.setAlignmentX(Component.CENTER_ALIGNMENT);
		manejadorNo();
		
		/* Botón Sí*/
		botonSi = GestorGUI.getBotonPredeterminado("Sí");
		botonSi.setAlignmentX(Component.CENTER_ALIGNMENT);
		manejadorSi();
		
		/* Montaje */
		panelTexto.add(etiquetaImagen);
		panelTexto.add(Box.createVerticalStrut(3*MARGEN));
		panelTexto.add(areaTexto);
		panelTexto.add(Box.createVerticalStrut(15));
		panelTexto.add(botonNo);
		panelTexto.add(botonSi);
		
		panelEnvolvente.add(panelTexto);
		
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
