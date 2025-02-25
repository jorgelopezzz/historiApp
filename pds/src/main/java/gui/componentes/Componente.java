package gui.componentes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.GestorGUI;

public abstract class Componente extends JPanel {
	
	/* Componentes de información */
	private JLabel etiquetaImagen;
	private JLabel etiquetaCabeceraTitulo;
	private JLabel etiquetaTitulo;
	private JLabel etiquetaCabeceraDescripcion;
	private JTextArea etiquetaDescripcion;
	private JLabel etiquetaCabeceraEstado;
	private JLabel etiquetaEstado;
	
	/* Botón */
	protected JButton boton;
	
	/* Componentes de organización */
	private JPanel panelImagen;
	private JPanel panelInformacion;
	
	/* Dimensiones */
	public static final int ANCHO_COMPONENTE = 240;
	public static final int ALTO_COMPONENTE = 320;
	
	private static final int ANCHO_IMAGEN = 240;
	private static final int ALTO_IMAGEN = 120;
	
	private static final int SEPARACION = 10;
	private static final int NUM_COLUMNAS = 40;
	
	public Componente(String cabeceraTitulo, String titulo, String descripcion, String rutaImagen, String estado) {
		super();
		
		/* Configuración del panel */
		GestorGUI.configurarPanel(this, new BorderLayout(), GestorGUI.getInstancia().getColorMedio());
		GestorGUI.fijarTamano(ANCHO_COMPONENTE, ALTO_COMPONENTE, this);
		setBorder(new LineBorder(GestorGUI.getInstancia().getColorBlanco()));
		
		/* Configuración panel imagen */
		panelImagen = new JPanel();
		GestorGUI.configurarPanel(panelImagen, new BoxLayout(panelImagen, BoxLayout.Y_AXIS), false);
		
		/* Etiqueta imagen */
		construirEtiquetaImagen(rutaImagen);
		
		/* Montaje panel superior */
		panelImagen.add(Box.createVerticalStrut(SEPARACION));
		panelImagen.add(etiquetaImagen);
		
		/* Configuración panel información */
		panelInformacion = new JPanel();
		GestorGUI.configurarPanel(panelInformacion, new BoxLayout(panelInformacion, BoxLayout.Y_AXIS), false);
		panelInformacion.setBorder(new EmptyBorder(0, SEPARACION, 0, 0));
		
		/* Etiquetas de texto */
		construirEtiquetasTitulo(cabeceraTitulo, titulo);
		construirEtiquetasDescripcion(descripcion);
		construirEtiquetasEstado(estado);
		
		/* Montaje panel inferior */
		panelInformacion.add(Box.createVerticalStrut(SEPARACION));
		
		panelInformacion.add(etiquetaCabeceraTitulo);
		panelInformacion.add(etiquetaTitulo);
		
		panelInformacion.add(Box.createVerticalStrut(SEPARACION));
		
		panelInformacion.add(etiquetaCabeceraEstado);
		panelInformacion.add(etiquetaEstado);
		
		panelInformacion.add(Box.createVerticalStrut(SEPARACION));
		panelInformacion.add(etiquetaCabeceraDescripcion);
		panelInformacion.add(etiquetaDescripcion);
				
		/* Montaje general */
		
		add(panelImagen, BorderLayout.NORTH);
		add(panelInformacion, BorderLayout.CENTER);
		
		
	}
	
	private void construirEtiquetaImagen(String rutaImagen) {
		etiquetaImagen = new JLabel();
		etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoAbsoluto(rutaImagen, ANCHO_IMAGEN, ALTO_IMAGEN));
		etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void construirEtiquetasEstado(String estado) {
		/* Cabecera */
		etiquetaCabeceraEstado = new JLabel("Estado:");
		GestorGUI.configurarEtiqueta(etiquetaCabeceraEstado, false, GestorGUI.getInstancia().getColorClaro(), 
				GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
		etiquetaCabeceraEstado.setAlignmentX(Componente.LEFT_ALIGNMENT);
		
		/* Estado */
		etiquetaEstado = new JLabel(estado);
		GestorGUI.configurarEtiqueta(etiquetaEstado, false, GestorGUI.getInstancia().getColorBlanco(), 
				GestorGUI.getInstancia().getFuenteGrande());
		etiquetaEstado.setAlignmentX(Componente.LEFT_ALIGNMENT);
		
	}

	private void construirEtiquetasDescripcion(String descripcion) {
		/* Cabecera */
		etiquetaCabeceraDescripcion = new JLabel("Descripción:");
		GestorGUI.configurarEtiqueta(etiquetaCabeceraDescripcion, false, GestorGUI.getInstancia().getColorClaro(), 
				GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
		etiquetaCabeceraDescripcion.setAlignmentX(Componente.LEFT_ALIGNMENT);
		
		/* Descripcion */
		etiquetaDescripcion = GestorGUI.crearAreaTexto(descripcion, GestorGUI.getInstancia().getFuenteGrande(), GestorGUI.getInstancia().getColorBlanco());
		etiquetaDescripcion.setColumns(NUM_COLUMNAS);
		etiquetaDescripcion.setAlignmentX(Componente.LEFT_ALIGNMENT);
	}
	
	private void construirEtiquetasTitulo(String cabecera, String titulo) {
		/* Cabecera */
		etiquetaCabeceraTitulo = new JLabel(cabecera);
		GestorGUI.configurarEtiqueta(etiquetaCabeceraTitulo, false, GestorGUI.getInstancia().getColorClaro(), 
				GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
		etiquetaCabeceraTitulo.setAlignmentX(Componente.LEFT_ALIGNMENT);
		
		/* Título */
		etiquetaTitulo = new JLabel(titulo);
		GestorGUI.configurarEtiqueta(etiquetaTitulo, false, GestorGUI.getInstancia().getColorBlanco(), 
				GestorGUI.getInstancia().getFuenteGrande());
		etiquetaTitulo.setAlignmentX(Componente.LEFT_ALIGNMENT);
	}
	
	public void seleccionar() {
		setBackground(GestorGUI.getInstancia().getColorOscuro());
	}
	
	public void deseleccionar() {
		setBackground(GestorGUI.getInstancia().getColorMedio());
	}
	
	protected void setBoton(JButton boton) {
		this.boton = boton;
	}
	
}
