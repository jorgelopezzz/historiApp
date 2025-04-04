package dominio.tarea;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import dominio.info.Info;


@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "tipo"
	)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Tip.class, name = Tip.tipo),
    @JsonSubTypes.Type(value = PreguntaRellenar.class, name = PreguntaTipoTest.tipoPregunta),
    @JsonSubTypes.Type(value = PreguntaTipoTest.class, name = PreguntaTipoTest.tipoPregunta),
    @JsonSubTypes.Type(value = PreguntaVF.class, name = PreguntaVF.tipoPregunta)
})
public abstract class Tarea {
	
	/* Atributos de información */
	@JsonProperty(required = true)
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
	
    /* Método para validar la serialización */
    @JsonProperty("enunciado")
    public void setEnunciado(String enunciado) {
        cadenaValida(enunciado);
        this.enunciado = enunciado;
    }
    
    /* Método para mutar a info */
    public abstract Info crearInfo();
	
    public boolean esPregunta() {
    	return this instanceof Pregunta;
    }
    

}
