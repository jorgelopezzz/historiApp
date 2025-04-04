package dominio.curso;

import java.time.LocalDateTime;
import java.util.Optional;

public class RealizacionBloque {

	/* Atributos iniciales */
	private BloqueContenidos bloque;
	
	/* Atributos opcionales */
	private LocalDateTime fechaCompletado;
	private Double puntuacion;

	public RealizacionBloque(BloqueContenidos bloque) {
		if(bloque == null)
			throw new IllegalArgumentException();
		this.bloque = bloque;
		this.puntuacion = null;
		this.fechaCompletado = null;
	}

	public void completar(Double puntuacion) {
		if(puntuacion == null || puntuacion < 0 || puntuacion > 10) {
			throw new IllegalArgumentException();
		}
		this.puntuacion = puntuacion;
		this.fechaCompletado = LocalDateTime.now();
	}

	public BloqueContenidos getBloque() {
		return bloque;
	}

	public Optional<LocalDateTime>getFechaCompletado() {
		return Optional.ofNullable(fechaCompletado);
	}

	public Optional<Double> getPuntuacion() {
		return Optional.ofNullable(puntuacion);
	}

	
	
	
}
