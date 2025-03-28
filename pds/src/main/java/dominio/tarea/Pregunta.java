package dominio.tarea;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public abstract class Pregunta extends Tarea {
	
	@JsonTypeInfo(
		    use = JsonTypeInfo.Id.NAME,
		    include = JsonTypeInfo.As.PROPERTY,
		    property = "tipoPregunta"
		)
		@JsonSubTypes({
		    @JsonSubTypes.Type(value = PreguntaRellenar.class, name = PreguntaTipoTest.tipoPregunta),
		    @JsonSubTypes.Type(value = PreguntaTipoTest.class, name = PreguntaTipoTest.tipoPregunta),
		    @JsonSubTypes.Type(value = PreguntaVF.class, name = PreguntaVF.tipoPregunta),
		})
	
	@JsonProperty
	public static final String tipo = "Pregunta";
	
	public Pregunta(String enunciado) {
		super(enunciado);
	}
	
	public abstract boolean evaluar(String respuesta);
	
}
