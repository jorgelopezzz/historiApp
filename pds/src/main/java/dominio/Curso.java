package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Curso {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    private Map<String,BloqueContenidos> bloquesContenidos;
    
    public Curso() {} // Constructor vacío para Jackson (servicioJSON)
    
    public Curso(String titulo, String descripcion, String rutaImagen, List<BloqueContenidos> bloquesContenidos) {
    	if(titulo == null) 
    		throw new IllegalArgumentException("El título no puede ser nulo");
    	this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        if(bloquesContenidos == null)
        	throw new IllegalArgumentException("Se necesita al menos un bloque de contenidos");
        setBloquesContenidos(bloquesContenidos);
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
    
    public List<BloqueContenidos> getBloquesContenidos() {
    	return new ArrayList<>(bloquesContenidos.values());
    }
    
    public BloqueContenidos getBloquePorNombre(String bloqueNombre) {
    	return bloquesContenidos.get(bloqueNombre);    	
    }
    
    @JsonSetter("bloquesContenidos")
    private void setBloquesContenidos(List<BloqueContenidos> bloques) {
        if (bloques == null || bloques.isEmpty()) 
            throw new IllegalArgumentException("Se necesita al menos un bloque de contenidos");

        this.bloquesContenidos = new HashMap<>();
        for (BloqueContenidos bloque : bloques) {
            this.bloquesContenidos.put(bloque.getTitulo(), bloque);
        }
    }


}
