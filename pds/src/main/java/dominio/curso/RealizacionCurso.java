package dominio.curso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.usuario.Usuario;

public class RealizacionCurso {
    private Curso curso;
    private Usuario usuario;
    private LocalDate fechaMatricula;
	private MetodoAprendizaje metodoAprendizaje;
	
	private List<RealizacionBloque> listaBloques; 
    private List<RealizacionBloque> listaBloquesCompletados;
	
    public RealizacionCurso(Curso curso, Usuario usuario, LocalDate fechaMatricula, MetodoAprendizaje metodoAprendizaje) {
    	if (curso == null || usuario == null || fechaMatricula == null) {
    		throw new IllegalArgumentException("Los atributos no pueden ser nulos");
        }
        this.curso = curso;
        this.usuario = usuario;
        this.fechaMatricula = fechaMatricula;
        this.metodoAprendizaje = metodoAprendizaje;
        
        /* Inicialización de listas de bloques */
        
        List<BloqueContenidos> listaBloquesOriginal = curso.getBloquesContenidos();
        listaBloques = new ArrayList<RealizacionBloque>(listaBloquesOriginal.size());
        listaBloquesCompletados = new ArrayList<RealizacionBloque>(listaBloquesOriginal.size());
        
        for(BloqueContenidos bloque : listaBloquesOriginal) {
        	listaBloques.add(new RealizacionBloque(bloque));
        }
        
    }
    
    public Curso getCurso() {
    	return curso;
    }
    
    public Usuario getUsuario() {
    	return usuario;
    }
    
    public LocalDate fechaMatricula() {
    	return fechaMatricula;
    }

	public MetodoAprendizaje getMetodoAprendizaje() {
		return metodoAprendizaje;
	}

	public void setMetodoAprendizaje(MetodoAprendizaje metodoAprendizaje) {
		this.metodoAprendizaje = metodoAprendizaje;
	}
}