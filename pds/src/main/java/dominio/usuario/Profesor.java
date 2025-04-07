package dominio.usuario;

import java.time.LocalDateTime;

import dominio.info.usuario.infoEstadisticasProfesor;
import dominio.info.usuario.infoPerfilUsuario;

public class Profesor extends Usuario {

	private int cursosPublicados;

	public Profesor(String nombre, String contrasena, String correo, String imagen, String saludo) {
		super(nombre, contrasena, correo, imagen, saludo);


	}
	
	@Override
	public infoEstadisticasProfesor getEstadisticas(){
		return new infoEstadisticasProfesor(nombre, puntuacion, getCursosCompletados(), getBloquesCompletados(), tiempoUso, diasUso, maxRacha, cursosPublicados);
	}

	@Override
	public infoPerfilUsuario getDatosPerfil(){
        return new infoPerfilUsuario(nombre, nombre, correo, saludo, "Profesor", imagen);
    }

}
