package gui.campos;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import gui.GestorGUI;

public class CampoTexto extends CampoPredeterminado {
	
	public CampoTexto(String texto, int ancho, int alto) {
		super(texto, ancho, alto);		
	}
	
	@Override
	protected void construirCampo(int ancho, int alto) {
		campo = new JTextField();
		campo.setFont(GestorGUI.getInstancia().getFuenteTexto());
		
	}
	
	protected boolean campoValido() {
		return ! campo.getText().isEmpty();
	}
	
}
