package gui.campos;

import javax.swing.JPasswordField;

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
	public String getTexto() {
		return new String(((JPasswordField) campo).getPassword());
	}
	
}
