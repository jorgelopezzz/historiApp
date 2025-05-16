package gui;

import java.awt.Color;
import java.awt.Font;

public class Tema {
	
	private final Color colorBlanco;
	private final Color colorClaro;
	private final Color colorMedio;
	private final Color colorOscuro;
	private final Color colorError;
	
	private final Font fuenteTexto;
	private final Font fuenteGrande;
	private final Font fuenteTitulo;
	
	public Tema(Color colorBlanco, Color colorClaro, Color colorMedio, Color colorOscuro, Color colorError, Font fuenteTexto, Font fuenteGrande, Font fuenteTitulo) {
		this.colorBlanco = colorBlanco;
		this.colorClaro = colorClaro;
		this.colorMedio = colorMedio;
		this.colorOscuro = colorOscuro;
		this.colorError = colorError;
		this.fuenteTexto = fuenteTexto;
		this.fuenteGrande = fuenteGrande;
		this.fuenteTitulo = fuenteTitulo;
	}

	public Font getFuenteGrande() {
		return fuenteGrande;
	}
	
	public Color getColorError() {
		return colorError;
	}

	public Color getColorBlanco() {
		return colorBlanco;
	}
	
	public Color getColorClaro() {
		return colorClaro;
	}

	public Color getColorMedio() {
		return colorMedio;
	}

	public Color getColorOscuro() {
		return colorOscuro;
	}

	public Font getFuenteTexto() {
		return fuenteTexto;
	}

	public Font getFuenteTitulo() {
		return fuenteTitulo;
	}
	
}
