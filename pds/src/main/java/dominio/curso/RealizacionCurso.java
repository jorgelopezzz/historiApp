package dominio.curso;

import jakarta.persistence.*;
import repositorios.RepositorioCursos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.usuario.Usuario;

@Entity
public class RealizacionCurso {
	
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
	@Column(nullable = false)
    private LocalDate fechaMatricula;
	@Enumerated(EnumType.STRING)
	private MetodoAprendizaje metodoAprendizaje;
	
	@Column(nullable = false)
	private int bloquesTotales;
	@Column(nullable = false)
	private int bloquesCompletados;
	@OneToMany(mappedBy = "realizacionCurso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RealizacionBloque> listaBloques;
	
	public RealizacionCurso () {}
	
    public RealizacionCurso(Curso curso, Usuario usuario, MetodoAprendizaje metodoAprendizaje) {
    	if (curso == null) {
    		throw new IllegalArgumentException("El curso no puede ser nulo.");
        }
    	if (usuario == null) {
    		throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
    	if (metodoAprendizaje== null) {
    		throw new IllegalArgumentException("El método de aprendizaje no puede ser nulo.");
        }
        this.curso = curso;
        this.cursoNombre = curso.getTitulo();
        this.usuario = usuario;
        this.fechaMatricula = LocalDate.now();
        this.metodoAprendizaje = metodoAprendizaje;
        this.bloquesCompletados = 0;
        
        /* Inicialización de listas de bloques */
        
        List<BloqueContenidos> listaBloquesOriginal = curso.getBloquesContenidos();
        this.bloquesTotales = listaBloquesOriginal.size();
        this.listaBloques = new ArrayList<RealizacionBloque>(listaBloquesOriginal.size());
        
        
        
    }
    
    public boolean estaCompletado(){
    	return bloquesCompletados == bloquesTotales;
    }
    public Optional<RealizacionBloque> getRealizacionBloque(BloqueContenidos bloque){
    	return listaBloques.stream()
    			.filter( rb -> rb.getBloque().getTitulo().equals(bloque.getTitulo()))
    			.findFirst();
    			
    }
    
    public void completarBloque(BloqueContenidos bloque, double puntuacion) {
        getRealizacionBloque(bloque).ifPresentOrElse(
            // Caso 1: El bloque ya ha sido realizado
            rb -> {
                if (rb.getPuntuacion() < puntuacion) {
                    listaBloques.remove(rb);
                    listaBloques.add(new RealizacionBloque(bloque, puntuacion));
                }
            },
            // Caso 2: El bloque no ha sido realizado
            () -> {
                listaBloques.add(new RealizacionBloque(bloque, puntuacion));
                bloquesCompletados++;
            }
        );
    }

    public Curso getCurso() {
    	if(curso == null) {
    		this.curso = RepositorioCursos.INSTANCE.buscarCursoPorNombre(cursoNombre);
    	}
    	return curso;
    }
    
    public Usuario getUsuario() {
    	return usuario;
    }
    
    public LocalDate getFechaMatricula() {
    	return fechaMatricula;
    }

	public MetodoAprendizaje getMetodoAprendizaje() {
		return metodoAprendizaje;
	}

	public void setMetodoAprendizaje(MetodoAprendizaje metodoAprendizaje) {
		this.metodoAprendizaje = metodoAprendizaje;
	}
	
	public List<RealizacionBloque> getBloques() {
		return List.copyOf(listaBloques);
	}

	public void setUsuario(Usuario usuario) {
	    this.usuario = usuario;
	}
	
}