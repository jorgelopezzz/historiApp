package dominio.curso;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class RealizacionBloque {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "realizacionCurso_id")
    private RealizacionCurso realizacionCurso;
	
	/* Atributos iniciales */
	@Transient
	private BloqueContenidos bloque;
	@Column(nullable = false)
	private String bloqueNombre;
	
	/* Atributos opcionales */
	@Column(nullable = false)
	private LocalDateTime fechaCompletado;
	@Column(nullable = false)
	private Double puntuacion;
	
	protected RealizacionBloque() {}

	public RealizacionBloque(BloqueContenidos bloque, double puntuacion) {
		if(bloque == null)
			throw new IllegalArgumentException("Bloque no puede ser nulo.");
		this.bloque = bloque;
		this.bloqueNombre = bloque.getTitulo();
		if( puntuacion < 0 || puntuacion > 10) {
			throw new IllegalArgumentException("Puntuación fuera de rango.");
		}
		this.puntuacion = puntuacion;
		this.fechaCompletado = LocalDateTime.now();
	}

	public BloqueContenidos getBloque() {
		if(bloque == null) {
			bloque = RepositorioCursos.INSTANCE.buscarBloquePorNombre(bloqueNombre);
		}
		return bloque;
	}

	public LocalDateTime getFechaCompletado() {
		return fechaCompletado;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

}
