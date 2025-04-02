package dominio.usuario;

import java.time.LocalDateTime;

import dominio.info.usuario.infoEstadisticas;

public class Usuario {

    protected String nombre;
    private String contrasena;
    private String imagen;
    private String saludo;

    protected int puntuacion;
    protected int bloquesCompletados;
    protected int cursosCompletados;
    protected int minutosUso; //100 años de uso continuado no se sale de un int en minutos
    protected int diasUso;
    protected int maxRacha;

    private final LocalDateTime fechaRegistro;

    public Usuario(String nombre, String contrasena, String imagen, String saludo, LocalDateTime fechaRegistro) {
        if(nombre == null) 
    		throw new IllegalArgumentException("El nombre no puede ser nulo");

        if(contrasena == null) 
    		throw new IllegalArgumentException("La contraseña no puede ser nula");

        this.nombre = nombre;
        this.contrasena = contrasena;
        this.imagen = imagen;
        this.saludo = saludo;

        this.puntuacion = 0;
        this.bloquesCompletados = 0;
        this.cursosCompletados = 0;
        this.minutosUso = 0;
        this.diasUso = 0;
        this.maxRacha = 0;

        this.fechaRegistro = fechaRegistro;
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

    /* Estadísticas */
    /*public int getPuntuacion() {
        return puntuacion;
    }

    public int getBloquesCompletadosPuntuacion() {
        return bloquesCompletados;
    }

    public int getCursosCompletados() {
        return cursosCompletados;
    }

    public int getMinutosUso() {
        return minutosUso;
    }

    public int getDiasUso() {
        return diasUso;
    }

    public int getMaxRacha() {
        return maxRacha;
    } */
    
    public infoEstadisticas getEstadisticas(){
        return new infoEstadisticas(nombre, puntuacion, bloquesCompletados, cursosCompletados, minutosUso, diasUso, maxRacha);
    }
    
    public boolean checkContrasena(String contrasena){
        return this.contrasena.equals(contrasena);
    }

	/////////////////////////////////////////////////////////
	// 0.- NO HISTORIAS DE USUARIO //////////////////////////
	/////////////////////////////////////////////////////////
    
}