package modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Pregunta extends Tarea {
	/*
	@JsonTypeInfo(
		    use = JsonTypeInfo.Id.NAME,
		    include = JsonTypeInfo.As.PROPERTY,
		    property = "tipoPregunta"
		)
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = PreguntaRellenar.class, name = PreguntaTipoTest.tipoPregunta),
	    @JsonSubTypes.Type(value = PreguntaTipoTest.class, name = PreguntaTipoTest.tipoPregunta),
	    @JsonSubTypes.Type(value = PreguntaVF.class, name = PreguntaVF.tipoPregunta)
	})*/
	
	@JsonCreator
    public Pregunta(@JsonProperty("enunciado") String enunciado) {
		super(enunciado);
	}
	
	/* Constructor JSON */
	public Pregunta() {super();}
	
	public abstract boolean evaluar(String respuesta);
	
	public abstract Info crearInfo();
	
	
	
}
