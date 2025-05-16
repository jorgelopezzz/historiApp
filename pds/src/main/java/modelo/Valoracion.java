package modelo;

import jakarta.persistence.*;

@Entity
public class Valoracion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Transient
    private Curso curso;
	@Column(nullable = false)
	private String cursoNombre;
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
	
	@Column(nullable = true)
    private int puntuacion;
	@Column(nullable = true)
    private String comentario;
	
	public Valoracion () {}
	
    public Valoracion(Curso curso, Usuario usuario, int puntuacion, String comentario) {
    	if (curso == null) {
    		throw new IllegalArgumentException("El curso no puede ser nulo.");
        }
    	if (usuario == null) {
    		throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
    	if ( comentario == null) {
    		throw new IllegalArgumentException("El comentario debe ser no nulos.");
    	}
    	if(puntuacion < 0 || puntuacion > 10) {
    		throw new IllegalArgumentException("La puntuación debe pertenecer a [0,10].");
    	}
        this.curso = curso;
        this.cursoNombre = curso.getTitulo();
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;         
    }

	public String getCursoNombre() {
		return cursoNombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public String getComentario() {
		return comentario;
	}
    
    
    
}
    