package dominio.tarea;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreguntaVF extends Pregunta {
	
	/* Serialización y herencia */
	@JsonProperty
	public static final String tipoPregunta = "PreguntaVF";
	
	/* Cadenas predeterminadas */
	public static final String CADENA_VERDADERO = "Verdadero";
	public static final String CADENA_FALSO = "Falso";
	
	/* Atributos de información */
	@JsonProperty(required = true)
	public boolean esVerdadero;
	
	public PreguntaVF(@JsonProperty(value = "enunciado", required = true) String enunciado, @JsonProperty(value = "esVerdadero", required = true) boolean esVerdadero) {
		super(enunciado);
		this.esVerdadero = esVerdadero;
	}
	
	/* Constructor JSON */
	public PreguntaVF() {}
	
	@Override
	public boolean evaluar(String respuesta) {
		if(respuesta == null || respuesta.length() == 0)
			return false;
		return (respuesta.equals(CADENA_VERDADERO) && esVerdadero) || (respuesta.equals(CADENA_FALSO) && (!esVerdadero));
	}
	
}
