package gui.info.tarea;

import gui.info.Info;

public class InfoVF extends Info {
	
	private final boolean esVerdadero;
	
	public InfoVF(String enunciado, boolean esVerdadero) {
		super(enunciado);
		this.esVerdadero = esVerdadero;
	}

}
