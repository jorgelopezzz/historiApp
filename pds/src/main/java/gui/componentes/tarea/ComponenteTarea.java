package gui.componentes.tarea;

import java.awt.Dimension;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dominio.info.Info;
import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.contenidos.ComponenteContenido;

@SuppressWarnings("serial")
public abstract class ComponenteTarea extends Componente { 
	
	/* Dimensiones */
	private static final int NUM_COLUMNAS = 40;
	protected static final int MARGEN = 15;
	
	/* Atributos de información */
	private final String enunciado;
	
	/* Componentes gráficos */
	private JLabel etiquetaCabecera;
	private JTextArea areaContenido;

	public ComponenteTarea(Info info) {
		super();
		this.enunciado = info.getTitulo();
		
		/* Configuración gráfica */
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(MARGEN,MARGEN,MARGEN,MARGEN));
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
		GestorGUI.fijarTamano(ANCHO_COMPONENTE -2*MARGEN, areaContenido.getPreferredSize().height+areaContenido.getFont().getSize() + MARGEN, areaContenido);
		return areaContenido;
	}
	
	public boolean esPregunta() {
		return this instanceof ComponentePregunta;
	}
}
