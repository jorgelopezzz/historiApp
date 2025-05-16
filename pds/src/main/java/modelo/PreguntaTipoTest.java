package modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreguntaTipoTest extends Pregunta {
	
	/* Atributos de información */
	@JsonProperty(required = true)
	private List<String> opciones;
	
	@JsonProperty(required = true)
	private int correcta;
	
	/* Serialización y herencia */
	@JsonProperty("tipo")
	public static final String tipoPregunta = "PreguntaTipoTest";
	
	/* Constructor JSON*/
	public PreguntaTipoTest() {}
	
	public PreguntaTipoTest(String enunciado, List<String> opciones, int correcta) {
		super(enunciado);
		
		/* Comprobaciones sobre lista de opciones */
		if( opciones == null || opciones.size() == 0 ) {
			throw new IllegalArgumentException(getClass() + ": la lista de opciones debe ser no vacía.");
		} 
		setOpciones(opciones);
		
		/* Comprobaciones sobre índice de respuesta correcta */
		setCorrecta(correcta);
		
	}
	
	@Override
	public boolean evaluar(String respuesta) {
		if(respuesta == null || respuesta.length() == 0)
			return false;
		return respuesta.equals(opciones.get(correcta));
	}
	
	
	public List<String> getOpciones() {
		return List.copyOf(opciones);
	}
	
    /* Métodos para validar la serialización */
	
    @JsonProperty("opciones")
    public void setOpciones(List<String> opciones) {
        if(opciones == null || opciones.size() == 0) {
        	throw new IllegalArgumentException(getClass() + ": las preguntas tipo test deben tener al menos una opción.");
        }
        this.opciones = List.copyOf(opciones);
    }
    
    @JsonProperty("correcta")
    public void setCorrecta(int correcta) {
    	if( correcta < 0 || correcta > opciones.size()) {
    		throw new IllegalArgumentException(getClass() + ": el índice debe ser válido respecto a la lista de opciones.");
    	}
    	this.correcta = correcta;
    }
    
    @Override
	public Info crearInfo() {
		return new InfoTipoTest(this);
	}
	
	

    
}
