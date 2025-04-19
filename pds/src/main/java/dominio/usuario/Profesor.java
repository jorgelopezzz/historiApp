package dominio.usuario;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import dominio.info.usuario.infoEstadisticasProfesor;
import dominio.info.usuario.infoPerfilUsuario;

@Entity
@Table(name = "profesores")
@PrimaryKeyJoinColumn(name = "id") // Clave foránea que apunta a Usuario.id
public class Profesor extends Usuario {

	@Column(nullable = false)
	private int cursosPublicados;
	
	public Profesor() {
		super();
	}

	public Profesor(String nombre, String contrasena, String correo, String imagen, String saludo) {
		super(nombre, contrasena, correo, imagen, saludo);
		cursosPublicados = 0;
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
