package dominio.curso;

import java.time.LocalDateTime;
import java.util.Optional;

public class RealizacionBloque {

	/* Atributos iniciales */
	private BloqueContenidos bloque;
	
	/* Atributos opcionales */
	private LocalDateTime fechaCompletado;
	private Double puntuacion;

	public RealizacionBloque(BloqueContenidos bloque, double puntuacion) {
		if(bloque == null)
			throw new IllegalArgumentException();
		this.bloque = bloque;
		if( puntuacion < 0 || puntuacion > 10) {
			throw new IllegalArgumentException();
		}
		this.puntuacion = puntuacion;
		this.fechaCompletado = LocalDateTime.now();
	}

	public BloqueContenidos getBloque() {
		return bloque;
	}

	public LocalDateTime getFechaCompletado() {
		return fechaCompletado;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	
	
	
}
