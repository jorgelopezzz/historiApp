package modelo;

public class InfoRellenar extends Info {

	public InfoRellenar(String titulo) {
		super(titulo);
	}
	
	public InfoRellenar(PreguntaRellenar preguntaRellenar) {
		super(preguntaRellenar.getEnunciado());
	}

}
