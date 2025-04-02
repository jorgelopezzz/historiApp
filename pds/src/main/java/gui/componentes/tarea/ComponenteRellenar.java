package gui.componentes.tarea;

import java.util.Optional;

import javax.swing.JLabel;

import dominio.info.Info;
import gui.campos.CampoTexto;

public class ComponenteRellenar extends ComponentePregunta {

	/* Cadenas por defecto */
	private static final String CABECERA_RELLENAR = "Introduce tu respuesta:";
	
	/* Componentes gráficos */
	private JLabel cabeceraRellenar;
	private CampoTexto campoRespuesta;
	
	public ComponenteRellenar(Info info) {
		super(info);
		
		/* Construcción de etiquetas */
		super.construir(CABECERA);
		
		cabeceraRellenar = construirEtiquetaCabecera(CABECERA_RELLENAR);
		add(cabeceraRellenar);
		
		/* Construcción del campo */
		campoRespuesta = new CampoTexto("", ANCHO_COMPONENTE - 2*MARGEN, 2*MARGEN);
		
		add(campoRespuesta.getPanel());
		
	}

	@Override
	public Optional<String> getRespuesta() {
		return Optional.ofNullable(campoRespuesta.getTexto());
	}
	
}
