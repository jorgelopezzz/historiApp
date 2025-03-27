package dominio.tarea;

public abstract class Tarea {
	
	/* Atributos de información */
	private String enunciado;
	
	public Tarea() {} // Constructor vacío para Jackson (servicioJSON)
	
	public Tarea(String enunciado) {
		this.enunciado = enunciado;
	}
	
	

}
