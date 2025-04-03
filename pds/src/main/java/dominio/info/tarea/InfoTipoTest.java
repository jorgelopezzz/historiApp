package dominio.info.tarea;

import java.util.List;

import dominio.info.Info;
import dominio.tarea.PreguntaTipoTest;

public class InfoTipoTest extends Info {

	private final List<String> opciones;
	
	public InfoTipoTest(String titulo, List<String> opciones) {
		super(titulo);
		this.opciones = List.copyOf(opciones);
	}

	public InfoTipoTest(PreguntaTipoTest preguntaTipoTest) {
		this(preguntaTipoTest.getEnunciado(), preguntaTipoTest.getOpciones());
	}

	public List<String> getOpciones() {
		return List.copyOf(opciones);
	}

}
