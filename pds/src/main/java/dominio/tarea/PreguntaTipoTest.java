package dominio.tarea;

import java.util.List;

public class PreguntaTipoTest extends Pregunta {
	
	/* Atributos de información */
	private List<String> opciones;
	private int correcta;
	
	public PreguntaTipoTest(String enunciado, List<String> opciones, int correcta) {
		super(enunciado);
		
		/* Comprobaciones sobre lista de opciones */
		if( opciones == null || opciones.size() == 0 ) {
			throw new IllegalArgumentException(getClass() + ": la lista de opciones debe ser no vacía.");
		} 
		
		for(String opcion : opciones) {
			cadenaValida(opcion);
		}
		this.opciones = List.copyOf(opciones);
		
		/* Comprobaciones sobre índice de respuesta correcta */
		if( correcta < 0 || correcta > opciones.size()) {
			throw new IllegalArgumentException(getClass() + ": el índice debe ser válido respecto a la lista de opciones.");
		}
		this.correcta = correcta;
		
	}
	
	@Override
	public boolean evaluar(String respuesta) {
		if(respuesta == null || respuesta.length() == 0)
			return false;
		return respuesta.equals(opciones.get(correcta));
	}
	
}
