package dominio.tarea;

public abstract class Tarea {
	
	/* Anotación para herencia JSON:
	@JsonSubTypes({
    @JsonSubTypes.Type(value = TareaEscribir.class, name = "escribir"),
    @JsonSubTypes.Type(value = TareaLeer.class, name = "leer")
	})
	 */
	
	/* Atributos de información */
	private String enunciado;
	
	public Tarea() {} // Constructor vacío para Jackson (servicioJSON)
	
	public Tarea(String enunciado) {
		cadenaValida(enunciado);
		this.enunciado = enunciado;
	}
	
	protected void cadenaValida(String cadena) {
		if(cadena == null || cadena.length() == 0) {
			throw new IllegalArgumentException(getClass().toString() + ": la cadena '" + cadena +"' no es válida.");
		}
	}
	
	

}
