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
		this.enunciado = enunciado;
	}
	
	

}
