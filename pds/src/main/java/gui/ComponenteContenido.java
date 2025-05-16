package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public abstract class ComponenteContenido extends Componente {
	
	/* Componentes de información */
	private String titulo;
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
	private static final int SEPARACION = 10;
	private static final int NUM_COLUMNAS = 40;
	
	public ComponenteContenido(String cabeceraTitulo, String titulo, String descripcion, String rutaImagen, String estado) {
		super(new BorderLayout());
		
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
		this.titulo = titulo;
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
		etiquetaCabeceraEstado.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
		
		/* Estado */
		etiquetaEstado = new JLabel(estado);
		GestorGUI.configurarEtiqueta(etiquetaEstado, false, GestorGUI.getInstancia().getColorBlanco(), 
				GestorGUI.getInstancia().getFuenteGrande());
		etiquetaEstado.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
		
	}

	private void construirEtiquetasDescripcion(String descripcion) {
		/* Cabecera */
		etiquetaCabeceraDescripcion = new JLabel("Descripción:");
		GestorGUI.configurarEtiqueta(etiquetaCabeceraDescripcion, false, GestorGUI.getInstancia().getColorClaro(), 
				GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
		etiquetaCabeceraDescripcion.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
		
		/* Descripcion */
		etiquetaDescripcion = GestorGUI.crearAreaTexto(descripcion, GestorGUI.getInstancia().getFuenteGrande(), GestorGUI.getInstancia().getColorBlanco());
		etiquetaDescripcion.setColumns(NUM_COLUMNAS);
		etiquetaDescripcion.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
	}
	
	private void construirEtiquetasTitulo(String cabecera, String titulo) {
		/* Cabecera */
		etiquetaCabeceraTitulo = new JLabel(cabecera);
		GestorGUI.configurarEtiqueta(etiquetaCabeceraTitulo, false, GestorGUI.getInstancia().getColorClaro(), 
				GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
		etiquetaCabeceraTitulo.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
		
		/* Título */
		etiquetaTitulo = new JLabel(titulo);
		GestorGUI.configurarEtiqueta(etiquetaTitulo, false, GestorGUI.getInstancia().getColorBlanco(), 
				GestorGUI.getInstancia().getFuenteGrande());
		etiquetaTitulo.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
	}
	
	protected void setBoton(JButton boton) {
		this.boton = boton;
	}

	public String getTitulo() {
		return titulo;
	}
	
}
