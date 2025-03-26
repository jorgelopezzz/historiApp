package gui.info.tarea;

import gui.info.Info;

public class InfoRellenar extends Info {
	
	private final String respuesta;
	
	public InfoRellenar(String pregunta, String respuesta) {
		super(pregunta);
		this.respuesta = respuesta;
	}

	public String getRespuesta() {
		return respuesta;
	}

}
