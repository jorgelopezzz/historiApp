package dominio.tarea;

public abstract class Tarea {

	private String tipo;
	
	public Tarea() {} // Constructor vacío para Jackson (servicioJSON)
	
	public Tarea(String tipo) {
		this.tipo = tipo;
	}
	
	

}
