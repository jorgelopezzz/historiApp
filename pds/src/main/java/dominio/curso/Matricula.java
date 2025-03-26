package dominio.curso;

import java.time.LocalDate;

import dominio.usuario.Usuario;

public class Matricula {
    private Curso curso;
    private Usuario usuario;
    private LocalDate fechaMatricula;
    
    public Matricula(Curso curso, Usuario usuario, LocalDate fechaMatricula) {
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