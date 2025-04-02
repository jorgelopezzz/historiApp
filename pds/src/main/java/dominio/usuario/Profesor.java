package dominio.usuario;

import java.time.LocalDateTime;

import dominio.info.usuario.infoEstadisticasProfesor;

public class Profesor extends Usuario {

	private int cursosPublicados;

	public Profesor(String nombre, String contrasena, String imagen, String saludo, LocalDateTime fechaRegistro) {
		super(nombre, contrasena, imagen, saludo, fechaRegistro);


	}
	
	@Override
	public infoEstadisticasProfesor getEstadisticas(){
		return new infoEstadisticasProfesor(nombre, puntuacion, cursosCompletados, tiempoUso, diasUso, maxRacha, cursosPublicados);
	}

}
