package dominio.usuario;

import java.time.LocalDateTime;

public abstract class Usuario {

    private int id;
    private String nombre;
    private String movil;
    private String contrasena;
    private String imagen;
    private String saludo;
    private final LocalDateTime fechaRegistro;

    public Usuario(String nombre, String movil, String contrasena, String imagen, String saludo, LocalDateTime fechaRegistro, boolean isPremium) {
        this.id = 0;
        this.nombre = nombre;
        this.movil = movil;
        this.contrasena = contrasena;
        this.imagen = imagen;
        this.saludo = saludo;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getImagen() {
        return imagen;
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
    public LocalDateTime getFechaRegistro() {
    	return fechaRegistro;
    }
    
	/////////////////////////////////////////////////////////
	// 0.- NO HISTORIAS DE USUARIO //////////////////////////
	/////////////////////////////////////////////////////////
    
    public boolean realizarCurso() {
    	return true;
    }
    
}