package dominio.tarea;

import java.util.List;

public class PreguntaVF extends Pregunta {

	public static final String CADENA_VERDADERO = "Verdadero";
	public static final String CADENA_FALSO = "Falso";
	
	/* Atributos de información */
	public boolean esVerdadero;
	
	public PreguntaVF(String enunciado, boolean esVerdadero) {
		super(enunciado);
		this.esVerdadero = esVerdadero;
	}
	
	@Override
	public boolean evaluar(String respuesta) {
		if(respuesta == null)
			return false;
		return (respuesta.equals(CADENA_VERDADERO) && esVerdadero) || (respuesta.equals(CADENA_FALSO) && (!esVerdadero));
	}
	
}
