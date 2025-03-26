package dominio.usuario;

import java.time.LocalDateTime;

public class Profesor extends Usuario {

	public Profesor(String nombre, String movil, String contrasena, String imagen, String saludo,
			LocalDateTime fechaRegistro, boolean isPremium) {
		super(nombre, movil, contrasena, imagen, saludo, fechaRegistro, isPremium);
	}
	
	public boolean crearCurso() {
		return true;
	}

}
