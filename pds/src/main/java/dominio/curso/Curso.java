package dominio.curso;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    private Matricula matricula;
    private List<BloqueContenidos> bloquesContenidos;
    
    public Curso() {} // Constructor vacío para Jackson (servicioJSON)
    
    public Curso(String titulo, String descripcion, String rutaImagen, List<BloqueContenidos> bloquesContenidos) {
    	if(titulo == null) 
    		throw new IllegalArgumentException("El título no puede ser nulo");
    	this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        if(bloquesContenidos == null)
        	throw new IllegalArgumentException("Se necesita al menos un bloque de contenidos");
        this.bloquesContenidos = new ArrayList<>(bloquesContenidos);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
    	return descripcion;
    }
    
    public String getRutaImagen() {
    	return rutaImagen;
    }
    
    public Matricula getMatricula() {
    	return matricula;
    }
    
    public void setMatricula(Matricula matricula) {
        if (matricula == null) 
        	throw new IllegalArgumentException("La matrícula no puede ser nula");
        this.matricula = matricula;
    }
    
    public List<BloqueContenidos> getBloquesContenidos() {
        return bloquesContenidos;
    }


}
