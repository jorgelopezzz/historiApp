package dominio.info.tarea;

import java.util.List;

import dominio.info.Info;

public class InfoTipoTest extends Info {

	private final List<String> opciones;
	
	public InfoTipoTest(String titulo, List<String> opciones) {
		super(titulo);
		this.opciones = List.copyOf(opciones);
	}

	public List<String> getOpciones() {
		return List.copyOf(opciones);
	}

}
