package gui.info.tarea;

import java.util.List;

import gui.info.Info;

public class InfoVF extends Info {
	
	private final boolean esVerdadero;
	
	public InfoVF(String enunciado, boolean esVerdadero) {
		super(enunciado);
		this.esVerdadero = esVerdadero;
	}

	public InfoTipoTest adaptarVF() {
		return new InfoTipoTest(getTitulo(), List.of("Verdadero", "Falso"), esVerdadero ? 0 : 1);
	}
	
}
