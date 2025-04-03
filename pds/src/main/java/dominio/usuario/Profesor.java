package dominio.usuario;

import java.time.LocalDateTime;

import dominio.info.usuario.infoEstadisticasProfesor;
import dominio.info.usuario.infoPerfilUsuario;

public class Profesor extends Usuario {

	private int cursosPublicados;

	public Profesor(String nombre, String contrasena, String correo, String imagen, String saludo, LocalDateTime fechaRegistro) {
		super(nombre, contrasena, correo, imagen, saludo, fechaRegistro);


	}
	
	@Override
	public infoEstadisticasProfesor getEstadisticas(){
		return new infoEstadisticasProfesor(nombre, puntuacion, cursosCompletados, tiempoUso, diasUso, maxRacha, cursosPublicados);
	}

	@Override
	public infoPerfilUsuario getDatosPerfil(){
        return new infoPerfilUsuario(nombre, nombre, correo, saludo, "Profesor", imagen);
    }

}
