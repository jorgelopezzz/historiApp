package gui.componentes.tarea;

import javax.swing.JLabel;

import gui.campos.CampoTexto;
import gui.info.tarea.InfoRellenar;

public class ComponenteRellenar extends ComponentePregunta {

	/* Cadenas por defecto */
	private static final String CABECERA_RELLENAR = "Introduce tu respuesta:";
	
	/* Atributos de información */
	private final String solucion;
	
	/* Componentes gráficos */
	private JLabel cabeceraRellenar;
	private CampoTexto campoRespuesta;
	
	public ComponenteRellenar(InfoRellenar info) {
		super(info);
		this.solucion = info.getRespuesta();
		
		/* Construcción de etiquetas */
		super.construir(CABECERA);
		
		cabeceraRellenar = construirEtiquetaCabecera(CABECERA_RELLENAR);
		add(cabeceraRellenar);
		
		/* Construcción del campo */
		campoRespuesta = new CampoTexto("", ANCHO_COMPONENTE - 2*MARGEN, 2*MARGEN);
		
		add(campoRespuesta.getPanel());
		
	}

	@Override
	public boolean evaluar() {
		return campoRespuesta.getTexto().equals(solucion);
	}
	
}
