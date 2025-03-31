package dominio.curso;

import java.time.LocalDate;

import dominio.usuario.Usuario;

public class Matricula {
    private Curso curso;
    private Usuario usuario;
    private LocalDate fechaMatricula;
    
    public Matricula(Curso curso, Usuario usuario, LocalDate fechaMatricula) {
    	if (curso == null || usuario == null || fechaMatricula == null) {
    		throw new IllegalArgumentException("Los atributos no pueden ser nulos");
        }
        this.curso = curso;
        this.usuario = usuario;
        this.fechaMatricula = fechaMatricula;
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
}