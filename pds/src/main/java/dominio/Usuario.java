package dominio;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import repositorios.RepositorioUsuarios;

import java.io.File;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_USUARIO")
@DiscriminatorValue("ESTUDIANTE")
@Table(name = "USUARIOS")
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
    @Column(nullable = false)
    protected String saludo;
    
    
    /* Progresos */
    
    @Column(nullable = false)
    protected int cursosCompletados;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RealizacionCurso> cursos = new ArrayList<>();
    
    /* Estadísticas */
    @Convert(converter = DurationConverter.class)
    protected Duration tiempoUso;
	@Column(nullable = false)
    protected int diasUso;
    @Column(nullable = false)
    protected int maxRacha;
    @Column(nullable = false)
    protected int rachaActual;
    @Column(nullable = false)
    protected LocalDate ultimaConexion;
    
    /* Valoraciones */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Valoracion> valoraciones = new ArrayList<>();

    @Transient
    private LocalDateTime inicioSesion;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
    
    public Usuario() {}

    public Usuario(String nombre, String contrasena, String correo, String imagen, String saludo) {
        if(nombre == null) 
    		throw new IllegalArgumentException("El nombre no puede ser nulo");

        if(contrasena == null) 
    		throw new IllegalArgumentException("La contraseña no puede ser nula");

        if(correo == null) 
    		throw new IllegalArgumentException("El correo no puede ser nulo");

        if(saludo == null) 
    		throw new IllegalArgumentException("El saludo no puede ser nulo");

        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;

        this.saludo = saludo;
        
        this.imagen = imagen;


        this.tiempoUso = Duration.ofSeconds(0);
        this.diasUso = 1;
        this.maxRacha = 1;
        this.rachaActual = 1;
        this.cursosCompletados = 0;

        this.fechaRegistro = LocalDateTime.now();
        this.ultimaConexion = fechaRegistro.toLocalDate();
    }

    // Getters y setters

    public List<Valoracion> getValoraciones() {
		return List.copyOf(valoraciones);
	}
    
    public List<Valoracion> getValoracionesPorCursoNombre(String cursoNombre) {
    	return valoraciones.stream()
    			.filter(v -> v.getCursoNombre().equals(cursoNombre))
    			.collect(Collectors.toList());
    }

	public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaRegistro() {
    	return fechaRegistro;
    }

    /* Alterables */
    public String getImagen() {
    	if(!imagen.equals(RUTA_PERFIL_PREDETERMINADO))
    		return imagen;
    	try {
			return new File(this.getClass().getResource(imagen).toURI()).getAbsolutePath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Transactional
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getSaludo() {
    	return saludo;
    }

    @Transactional
    public void setSaludo(String saludo) {
    	this.saludo = saludo;
    }

    public double getPuntuacion() {
        return cursos.stream()
            .flatMap(rc -> rc.getBloques().stream())
            .mapToDouble(rb -> rb.getPuntuacion())
            .sum();
    }

    public int getMaxRacha() {
        return maxRacha;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public infoEstadisticas getEstadisticas(){
        return new infoEstadisticas(nombre, getPuntuacion(), getCursosCompletados(), getBloquesCompletados(), tiempoUso, diasUso, maxRacha);
    }

    public infoPerfilUsuario getDatosPerfil(){
        return new infoPerfilUsuario(nombre, nombre, correo, saludo, "Estudiante", imagen);
    }
    
    public boolean checkContrasena(String contrasena){
        return this.contrasena.equals(contrasena);
    }

    public boolean iniciarSesion(){
        inicioSesion = LocalDateTime.now();
        if (ultimaConexion != null && !ultimaConexion.equals(inicioSesion.toLocalDate())) {
            rachaActual = 0;
        }
        return true;
    }

    public boolean cerrarSesion(){
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate hoy = ahora.toLocalDate();
        
        tiempoUso = tiempoUso.plus(Duration.between(inicioSesion, ahora));
        int dias = 0;
        dias = (int)ChronoUnit.DAYS.between(ultimaConexion, hoy); 
        if (dias > 0) {
            diasUso += dias;
            rachaActual += dias;
            if (rachaActual > maxRacha) {
                maxRacha = rachaActual;
            }
            ultimaConexion = hoy;
        }
        RepositorioUsuarios.INSTANCE.actualizarUsuario(this);
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
	
	@Transactional //MUY IMPORTANTEEE
	public boolean matricularCurso(Curso curso, MetodoAprendizaje metodoAprendizaje) {
		if(! estaMatriculado(curso.getTitulo())) {
			RealizacionCurso rc = new RealizacionCurso(curso, this, metodoAprendizaje);
			cursos.add(rc);
			return true;
		}
		return false;
	}
	
	@Transactional //MUY IMPORTANTEEE
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

	public boolean hacerValoracion(Curso curso, String comentario, int puntuacion) {
		boolean exito = valoraciones.add(new Valoracion(curso, this, puntuacion, comentario));
		return exito;
	}
}