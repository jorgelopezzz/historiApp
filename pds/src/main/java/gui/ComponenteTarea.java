package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import modelo.Info;
import utils.GestorGUI;

@SuppressWarnings("serial")
public abstract class ComponenteTarea extends Componente { 
	
	/* Dimensiones */
	private static final int NUM_COLUMNAS = 40;
	protected static final int MARGEN = 15;
	
	/* Atributos de información */
	private final String enunciado;
	
	/* Componentes gráficos */
	protected JLabel etiquetaCabecera;
	protected JTextArea areaContenido;

	public ComponenteTarea(Info info) {
		super();
		this.enunciado = info.getTitulo();
		
		/* Configuración gráfica */
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(MARGEN,MARGEN,MARGEN,MARGEN));
	}
	
	public ComponenteTarea(Info info, LayoutManager layout) {
		this(info);
		setLayout(layout);
	}
	
	public String getEnunciado() {
		return enunciado;
	}

	protected void construir(String cabecera) {
		/* Construcción */
		etiquetaCabecera = construirEtiquetaCabecera(cabecera);
		areaContenido = construirEtiquetaContenido(getEnunciado());
		
		/* Montaje */
		add(etiquetaCabecera);
		add(areaContenido);
	
	}
	
	protected JLabel getEtiquetaCabecera() {
		return etiquetaCabecera;
	}

	protected JLabel construirEtiquetaCabecera(String cabecera) {
		JLabel etiqueta = new JLabel(cabecera);
		GestorGUI.configurarEtiqueta(etiqueta, false, GestorGUI.getInstancia().getColorClaro(), 
				GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
		return etiqueta;
	}

	protected JTextArea getEtiquetaContenido() {
		return areaContenido;
	}

	protected JTextArea construirEtiquetaContenido(String contenido) {
		JTextArea areaContenido = GestorGUI.crearAreaTexto(contenido, GestorGUI.getInstancia().getFuenteGrande(), GestorGUI.getInstancia().getColorBlanco());
		areaContenido.setColumns(NUM_COLUMNAS);
		areaContenido.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
		GestorGUI.fijarTamano(ANCHO_COMPONENTE -2*MARGEN, areaContenido.getPreferredSize().height+areaContenido.getFont().getSize() + 6*MARGEN, areaContenido);
		return areaContenido;
	}
	
	public boolean esPregunta() {
		return this instanceof ComponentePregunta;
	}
}
