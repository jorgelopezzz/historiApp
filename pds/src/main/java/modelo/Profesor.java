package modelo;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends Usuario {

	@Column(nullable = true) // Para que funcione bien "single table" de usuario
	private int cursosPublicados;
	
	public Profesor() {
		super();
	}

	public Profesor(String nombre, String contrasena, String correo, String imagen, String saludo) {
		super(nombre, contrasena, correo, imagen, saludo);
		cursosPublicados = 0;
	}

	public boolean publicarCurso(){
		cursosPublicados++;
		return true;
	}
	
	@Override
	public infoEstadisticasProfesor getEstadisticas(){
		return new infoEstadisticasProfesor(nombre, getPuntuacion(), getCursosCompletados(), getBloquesCompletados(), tiempoUso, diasUso, maxRacha, cursosPublicados);
	}

	@Override
	public infoPerfilUsuario getDatosPerfil(){
        return new infoPerfilUsuario(nombre, nombre, correo, saludo, "Profesor", imagen);
    }

}
