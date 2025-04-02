package dominio.curso;

import java.time.LocalDate;

import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.usuario.Usuario;

public class RealizacionCurso {
    private Curso curso;
    private Usuario usuario;
    private LocalDate fechaMatricula;
	private MetodoAprendizaje metodoAprendizaje;
    
    public RealizacionCurso(Curso curso, Usuario usuario, LocalDate fechaMatricula, MetodoAprendizaje metodoAprendizaje) {
    	if (curso == null || usuario == null || fechaMatricula == null) {
    		throw new IllegalArgumentException("Los atributos no pueden ser nulos");
        }
        this.curso = curso;
        this.usuario = usuario;
        this.fechaMatricula = fechaMatricula;
        this.metodoAprendizaje = metodoAprendizaje;
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