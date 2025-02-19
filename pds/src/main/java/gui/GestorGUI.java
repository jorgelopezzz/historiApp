package gui;

import java.awt.Color;
import java.awt.Font;

public class GestorGUI {

	private Tema tema;
	
	public GestorGUI() {
		
		setTema(ConstructorTema.GRANATE);	// Elección del tema por defecto
		
	}
	
	public void setTema(int id) {
		new ConstructorTema().getTema(id).ifPresentOrElse(t -> tema = t, () -> tema = null);
	}
	public Color getColorFondo() {
		return tema.getColorFondo();
	}
	
	public Color getColorClaro() {
		return tema.getColorClaro();
	}

	public Color getColorMedio() {
		return tema.getColorMedio();
	}

	public Color getColorOscuro() {
		return tema.getColorOscuro();
	}

	public Font getFuenteTexto() {
		return tema.getFuenteTexto();
	}

	public Font getFuenteTitulo() {
		return tema.getFuenteTitulo();
	}
	
}

