package dominio.curso;

import java.time.LocalDate;

import dominio.usuario.Usuario;

public class CertificadoBloque {
    private LocalDate fechaCert;
    private Usuario usuario;
    private Curso curso;
    private BloqueContenidos bloque;
    
    public CertificadoBloque(LocalDate fechaCert, Usuario usuario, Curso curso, BloqueContenidos bloque) {
        this.fechaCert = fechaCert;
        this.usuario = usuario;
        this.curso = curso;
        this.bloque = bloque;
    }
    
    public LocalDate getFechaCert() {
		return fechaCert;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public BloqueContenidos getBloque() {
		return bloque;
	}
}