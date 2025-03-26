package gui.info.tarea;

import java.util.List;

import gui.info.Info;

public class InfoTipoTest extends Info {

	private final List<String> opciones;
	private final int correcta;
	
	public InfoTipoTest(String titulo, List<String> opciones, int correcta) {
		super(titulo);
		this.opciones = List.copyOf(opciones);
		this.correcta = correcta;
	}

	public List<String> getOpciones() {
		return List.copyOf(opciones);
	}

	public int getCorrecta() {
		return correcta;
	}
	
	

}
