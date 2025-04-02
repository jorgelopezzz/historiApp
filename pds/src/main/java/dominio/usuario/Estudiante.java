package dominio.usuario;

import java.time.LocalDateTime;

public class Estudiante extends Usuario {

	public Estudiante(String nombre, String movil, String contrasena, String imagen, String saludo,
			LocalDateTime fechaRegistro, boolean isPremium) {
		super(nombre, movil, contrasena, imagen, saludo, fechaRegistro, isPremium);
	}
	

}
