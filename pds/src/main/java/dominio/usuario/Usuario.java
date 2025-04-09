package dominio.usuario;

import jakarta.persistence.*;
import java.io.File;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.curso.BloqueContenidos;
import dominio.curso.Curso;
import dominio.curso.RealizacionCurso;
import dominio.info.usuario.infoEstadisticas;
import dominio.info.usuario.infoPerfilUsuario;
import dominio.metodoAprendizaje.MetodoAprendizaje;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public class Usuario {
    
    private static final String RUTA_PERFIL_PREDETERMINADO = "/perfil.png";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /* Atributos de información */
    @Column(nullable = false, unique = true)
    protected String nombre;
    @Column(nullable = false, unique = true)
    protected String correo;
    @Column(nullable = false)
    protected String contrasena;
    @Column(nullable = true)
    protected String imagen;
    @Column(nullable = true)
    protected String saludo;
    
    
    /* Progresos */
    
    @Column(nullable = true)
    protected int cursosCompletados; /* Arreglar esto, hay que ver el tema de la lista de instancias de curso en usuario */
    @Column(nullable = true)
    private List<RealizacionCurso> cursos;
    
    /* Estadísticas */
    @Column(nullable = true)
    protected int puntuacion;
    @Column(nullable = true)
    protected Duration tiempoUso;
	@Column(nullable = true)
    protected int diasUso;
    @Column(nullable = true)
    protected int maxRacha;
    

    @Transient
    private LocalDateTime inicioSesion;

    @Column(nullable = true)
    private LocalDateTime fechaRegistro;
    
    public Usuario() {}

    public Usuario(String nombre, String contrasena, String correo, String imagen, String saludo) {
        if(nombre == null) 
    		throw new IllegalArgumentException("El nombre no puede ser nulo");

        if(contrasena == null) 
    		throw new IllegalArgumentException("La contraseña no puede ser nula");

        if(correo == null) 
    		throw new IllegalArgumentException("El correo no puede ser nulo");

        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        
        this.imagen = imagen == null ? RUTA_PERFIL_PREDETERMINADO : imagen;
        this.saludo = saludo;

        this.cursos = new ArrayList<RealizacionCurso>();
        
        this.tiempoUso = Duration.ofSeconds(0);
        this.diasUso = 0;
        this.maxRacha = 0;

        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaRegistro() {
    	return fechaRegistro;
    }

    /* Alterables */
    public String getImagen() {
    	if(imagen != RUTA_PERFIL_PREDETERMINADO)
    		return imagen;
    	try {
			return new File(this.getClass().getResource(imagen).toURI()).getAbsolutePath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
        
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getSaludo() {
    	return saludo;
    }

    public void setSaludo(String saludo) {
    	this.saludo = saludo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getMaxRacha() {
        return maxRacha;
    }
    
    public infoEstadisticas getEstadisticas(){
        return new infoEstadisticas(nombre, puntuacion, getCursosCompletados(), getBloquesCompletados(), tiempoUso, diasUso, maxRacha);
    }

    public infoPerfilUsuario getDatosPerfil(){
        return new infoPerfilUsuario(nombre, nombre, correo, saludo, "Estudiante", imagen);
    }
    
    public boolean checkContrasena(String contrasena){
        return this.contrasena.equals(contrasena);
    }

    public boolean iniciarSesion(){
        inicioSesion = LocalDateTime.now();
        return true;
    }

    public boolean cerrarSesion(){
        tiempoUso = tiempoUso.plus(Duration.between(inicioSesion, LocalDateTime.now()));
        return true;
    }

	public boolean esProfesor() {
		return this instanceof Profesor;
	}
	
	/* Progreso y realizaciones de curso y bloque */
	
	public Optional<RealizacionCurso> getRealizacion(String nombreCurso) {
		return cursos.stream()
				.filter( rc -> ! rc.estaCompletado() )
				.filter( rc -> rc.getCurso().getTitulo().equals(nombreCurso))
				.findFirst();
	}
	
	public boolean estaMatriculado(String nombreCurso) {
		return getRealizacion(nombreCurso).isPresent();
	}
	
	public boolean matricularCurso(Curso curso, MetodoAprendizaje metodoAprendizaje) {
		if(! estaMatriculado(curso.getTitulo())) {
			cursos.add(new RealizacionCurso(curso, this, metodoAprendizaje));
			return true;
		}
		return false;
	}
	
	public void completarBloque(Curso curso, BloqueContenidos bloque, double puntuacion) {
		/* Caso 1: El usuario no está matriculado en el curso */
		getRealizacion(curso.getTitulo()).ifPresentOrElse(
				rc -> rc.completarBloque(bloque, puntuacion),
				() -> {throw new IllegalStateException("El usuario no está matriculado en el curso.");});
		
	}
	
	public int getCursosCompletados() {
		Long numCursos = cursos.stream()
			.filter( rc -> rc.estaCompletado())
			.count();
		return numCursos.intValue();
	}
	
	public int getBloquesCompletados() {
		return cursos.stream()
			.mapToInt( rc -> rc.getBloques().size() )
			.sum();
	}
	
	
	public Optional<MetodoAprendizaje> getMetodoAprendizaje(Curso curso) {
		Optional<RealizacionCurso> rc = getRealizacion(curso.getTitulo());
		if(rc.isPresent())
			return Optional.of(rc.get().getMetodoAprendizaje());
		return Optional.empty();
	}
	
	public boolean haCompletado(String nombreCurso) {
		/* Comprueba que:
		 * - El usuario tenga una realización del curso completada
		 */
		return cursos.stream()
				.filter( rc -> rc.estaCompletado() )
				.anyMatch( rc -> rc.getCurso().getTitulo().equals(nombreCurso) );
	}
	
	public boolean bloqueCompletado(String nombreCurso, String nombreBloque) {
		Optional<RealizacionCurso> realizacionCurso = getRealizacion(nombreCurso);
		if(realizacionCurso.isPresent()) {
			return realizacionCurso.get().getBloques().stream()
				.anyMatch( rb -> rb.getBloque().getTitulo().equals(nombreBloque));
		}
		return false;
	}
	
	
}