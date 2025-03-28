package dominio.curso;

import java.time.LocalDate;

public class CertificadoBloque {
    private LocalDate fechaCert;
    private String usuario;
    private BloqueContenidos bloque;
    
    public CertificadoBloque(LocalDate fechaCert, String usuario, BloqueContenidos bloque) {
        this.fechaCert = fechaCert;
        this.usuario = usuario;
        this.bloque = bloque;
    }
    
    public LocalDate getFechaCert() {
		return fechaCert;
	}

	public String getUsuario() {
		return usuario;
	}

	public BloqueContenidos getBloque() {
		return bloque;
	}
}