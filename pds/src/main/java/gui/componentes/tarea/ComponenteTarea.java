package gui.componentes.tarea;

import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.contenidos.ComponenteContenido;
import gui.info.Info;

@SuppressWarnings("serial")
public abstract class ComponenteTarea extends Componente { 
	
	/* Dimensiones */
	private static final int NUM_COLUMNAS = 40;
	private static final int MARGEN = 15;
	
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
		construirEtiquetaCabecera(cabecera);
		construirEtiquetaContenido(getEnunciado());
	}
	
	protected JLabel getEtiquetaCabecera() {
		return etiquetaCabecera;
	}

	protected void construirEtiquetaCabecera(String cabecera) {
		etiquetaCabecera = new JLabel(cabecera);
		GestorGUI.configurarEtiqueta(etiquetaCabecera, false, GestorGUI.getInstancia().getColorClaro(), 
				GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
	}

	protected JTextArea getEtiquetaContenido() {
		return areaContenido;
	}

	protected void construirEtiquetaContenido(String contenido) {
		areaContenido = GestorGUI.crearAreaTexto(contenido, GestorGUI.getInstancia().getFuenteGrande(), GestorGUI.getInstancia().getColorBlanco());
		areaContenido.setColumns(NUM_COLUMNAS);
		areaContenido.setAlignmentX(ComponenteContenido.LEFT_ALIGNMENT);
	}
}
