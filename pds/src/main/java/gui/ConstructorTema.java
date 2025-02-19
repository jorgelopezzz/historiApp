package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Optional;

public class ConstructorTema {
	
	// Identificadores para distintos temas
	public static final int NATURALEZA = 0;
	public static final int GRANATE = 1;
	
	private static final int TAM_TEXTO = 13;
	private static final int TAM_TITULO = 20;
	
	public ConstructorTema() {
		super();
	}
	
	public Optional<Tema> getTema(int id) {
		switch(id) {
			case NATURALEZA:
				return Optional.of(new Tema(
						new Color(237, 241, 214),
						new Color(157, 192, 139),
						new Color(96, 153, 102),
						new Color(64, 81, 59),
						new Font("Helvetica", Font.PLAIN, TAM_TEXTO),
						new Font("Helvetica", Font.PLAIN, TAM_TITULO)
						));
			case GRANATE:
				return Optional.of(new Tema(
						new Color(238, 235, 221),
						new Color(205,18,18),
						new Color(129,0,0),
						new Color(27,23,23),
						new Font("Helvetica", Font.PLAIN, TAM_TEXTO),
						new Font("Helvetica", Font.PLAIN, TAM_TITULO)
						));
			default:
				return Optional.empty();
		}
	}
	
}
