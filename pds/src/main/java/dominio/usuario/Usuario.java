package dominio.usuario;

import jakarta.persistence.*;
import java.io.File;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import dominio.info.usuario.infoEstadisticas;
import dominio.info.usuario.infoPerfilUsuario;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public class Usuario {
    
    private static final String RUTA_PERFIL_PREDETERMINADO = "/perfil.png";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @Column(nullable = true)
    protected int puntuacion;
    @Column(nullable = true)
    protected int cursosCompletados; /* Arreglar esto, hay que ver el tema de la lista de instancias de curso en usuario */
    @Column(nullable = true)
    protected int tiempoUso; //100 años de uso continuado no se sale de un int en minutos
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

        this.puntuacion = 0;
        this.cursosCompletados = 0;
        this.tiempoUso = 0;
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
        return new infoEstadisticas(nombre, puntuacion, cursosCompletados, tiempoUso, diasUso, maxRacha);
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
        System.out.println(inicioSesion);
        System.out.println(tiempoUso);
        tiempoUso += (int) inicioSesion.until(LocalDateTime.now(), ChronoUnit.MINUTES);
        System.out.println(LocalDateTime.now());
        System.out.println(tiempoUso);
        return true;
    }

	/////////////////////////////////////////////////////////
	// 0.- NO HISTORIAS DE USUARIO //////////////////////////
	/////////////////////////////////////////////////////////
    
}