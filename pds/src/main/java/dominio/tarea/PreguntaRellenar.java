package dominio.tarea;

public class PreguntaRellenar extends Pregunta {
	
	/* Atributos de información */
	private String respuesta;
	
	public PreguntaRellenar(String enunciado, String respuesta) {
		super(enunciado);
        cadenaValida(respuesta);
		this.respuesta = respuesta;
	}
	
	@Override
	public boolean evaluar(String respuesta) {
		return this.respuesta.equals(respuesta);
	}
	
}
