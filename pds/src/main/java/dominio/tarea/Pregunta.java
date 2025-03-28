package dominio.tarea;

public abstract class Pregunta extends Tarea {
	
	public Pregunta(String enunciado) {
		super(enunciado);
	}
	
	public abstract boolean evaluar(String respuesta);
	
}
