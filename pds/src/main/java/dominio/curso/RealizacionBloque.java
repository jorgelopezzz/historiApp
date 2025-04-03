package dominio.curso;

import java.time.LocalDateTime;
import java.util.Optional;

public class RealizacionBloque {

	/* Atributos iniciales */
	private BloqueContenidos bloque;
	
	/* Atributos opcionales */
	private LocalDateTime fechaCompletado;
	private Float puntuacion;

	public RealizacionBloque(BloqueContenidos bloque) {
		if(bloque == null || bloque.getTareas().size() == 0)
			throw new IllegalArgumentException();
		this.bloque = bloque;
		this.puntuacion = null;
		this.fechaCompletado = null;
	}

	public void completar(Float puntuacion) {
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

	public Optional<Float> getPuntuacion() {
		return Optional.ofNullable(puntuacion);
	}

	
	
	
}
