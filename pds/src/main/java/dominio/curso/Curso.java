package dominio.curso;

import java.util.List;

public class Curso {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    //private Matricula matricula;
    private List<BloqueContenidos> bloquesContenidos;
    
    public Curso() {} // Constructor vacío para Jackson (servicioJSON)
    
    public Curso(String titulo, String descripcion, String rutaImagen, List<BloqueContenidos> bloquesContenidos) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.bloquesContenidos = bloquesContenidos;
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
    
    //public Matricula getMatricula() {
    //	return matricula;
    //}
    
    public List<BloqueContenidos> getBloquesContenidos() {
        return bloquesContenidos;
    }


}
