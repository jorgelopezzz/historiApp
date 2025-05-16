package dominio;

import java.util.List;

public class InfoVF extends Info {
	
	/* Cadenas por defecto */
	public static final String CADENA_VERDADERO = "Verdadero";
	public static final String CADENA_FALSO = "Falso";
	
	private final boolean esVerdadero;
	
	public InfoVF(String enunciado, boolean esVerdadero) {
		super(enunciado);
		this.esVerdadero = esVerdadero;
	}

	public InfoVF(PreguntaVF preguntaVF) {
		this(preguntaVF.getEnunciado(), preguntaVF.esVerdadero());
	}
	
	public InfoTipoTest adaptarVF() {
		return new InfoTipoTest(getTitulo(), List.of(CADENA_VERDADERO, CADENA_FALSO));
	}
	
}
