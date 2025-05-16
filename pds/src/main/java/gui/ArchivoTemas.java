package gui;

import java.awt.Color;
import java.awt.Font;

public enum ArchivoTemas {	
	NATURALEZA(new Tema(
						new Color(237, 241, 214),
						new Color(157, 192, 139),
						new Color(96, 153, 102),
						new Color(64, 81, 59),
						new Color(255,119,119),
						new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TEXTO),
						new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TEXTO_GRANDE),
						new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TITULO)
						)),
	GRANATE(new Tema(
			new Color(238, 235, 221),
			new Color(205,18,18),
			new Color(129,0,0),
			new Color(27,23,23),
			new Color(255,119,119),
			new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TEXTO),
			new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TEXTO_GRANDE),
			new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TITULO)
			)),
	CIELO(new Tema(
			new Color(250,245,242),
			new Color(175,211,226),
			new Color(24, 149, 184),
			new Color(20,108,148),
			new Color(255,119,119),
			new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TEXTO),
			new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TEXTO_GRANDE),
			new Font("Helvetica", Font.PLAIN, GestorGUI.TAM_TITULO)
			));
						
	
	private final Tema tema;
	
	private ArchivoTemas(Tema tema) {
		this.tema = tema;
	}	

	public Tema getTema() {
		return tema;
	}
}
