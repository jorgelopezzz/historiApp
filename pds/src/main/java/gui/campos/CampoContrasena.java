package gui.campos;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import gui.GestorGUI;

public class CampoContrasena extends CampoPredeterminado {


	public CampoContrasena(String texto, int ancho, int alto) {
		super(texto, ancho, alto);		
	}
	
	public CampoContrasena(String texto, int ancho, int alto, int tamanoEtiqueta) {
		super(texto, ancho, alto, tamanoEtiqueta);		
	}
	
	@Override
	protected void construirCampo(int ancho, int alto) {
		campo = new JPasswordField();
	}
	
	@Override
	protected boolean campoValido() {
		return ((JPasswordField) campo).getPassword().length > 0;
	}
	
	@Override 
	public String getTexto() {
		return new String(((JPasswordField) campo).getPassword());
	}
	
}
