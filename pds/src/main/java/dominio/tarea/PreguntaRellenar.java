package dominio.tarea;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreguntaRellenar extends Pregunta {
	
	/* Atributos de información */
	@JsonProperty(required = true)
	private String respuesta;
	
	/* Serialización y herencia */
	@JsonProperty
	public static final String tipoPregunta = "Rellenar";
	
	public PreguntaRellenar(String enunciado, String respuesta) {
		super(enunciado);
        setRespuesta(respuesta);
	}
	
	@Override
	public boolean evaluar(String respuesta) {
		return this.respuesta.equals(respuesta);
	}

	public String getRespuesta() {
		return respuesta;
	}
	
    /* Método para validar la serialización */
    @JsonProperty("respuesta")
    public void setRespuesta(String respuesta) {
        cadenaValida(respuesta);
        this.respuesta = respuesta;
    }
	
}
