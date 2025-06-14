package gui;

import javax.swing.JComboBox;

public class CampoDesplegable extends CampoPredeterminado {

	public CampoDesplegable(String texto, int ancho, int alto) {
		super(texto, ancho, alto);
	}
	
	public CampoDesplegable(String texto, int ancho, int alto, int tamanoEtiqueta) {
		super(texto, ancho, alto, tamanoEtiqueta);		
	}
	
	@Override
	protected void construirCampo(int ancho, int alto) {
		campo = new JComboBox<String>();
	}

    @SuppressWarnings("unchecked")
	public void addElementos(String ... elementos) {
        for (String elemento : elementos) {
            ((JComboBox<String>)campo).addItem(elemento);
        }
    }
	
    @SuppressWarnings("unchecked")
	@Override
    public String getTexto() {
    	return (String) ((JComboBox<String>)campo).getSelectedItem();
    }
    
}
