package dominio.tarea;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "tipo"
	)
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = Tip.class, name = "Tip")
	})
public abstract class Tarea {
	
	/* Atributos de información */
	private String enunciado;
	
	/* Constructor para serialización JSON */
	public Tarea() {} 
	
	/* Constructor predeterminado */
	public Tarea(String enunciado) {
		cadenaValida(enunciado);
		this.enunciado = enunciado;
	}
	
	/* Lanzamiento de excepción para argumentos inválidos */
	protected void cadenaValida(String cadena) {
		if(cadena == null || cadena.length() == 0) {
			throw new IllegalArgumentException(getClass().toString() + ": la cadena '" + cadena +"' no es válida.");
		}
	}

	public String getEnunciado() {
		return enunciado;
	}
	

}
