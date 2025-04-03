package dominio.info.tarea;

import dominio.info.Info;
import dominio.tarea.PreguntaRellenar;

public class InfoRellenar extends Info {

	public InfoRellenar(String titulo) {
		super(titulo);
	}
	
	public InfoRellenar(PreguntaRellenar preguntaRellenar) {
		super(preguntaRellenar.getEnunciado());
	}

}
