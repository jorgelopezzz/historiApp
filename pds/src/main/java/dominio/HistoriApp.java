package dominio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import dominio.curso.RepositorioCursos;
import dominio.usuario.Profesor;
import dominio.usuario.RepositorioUsuarios;
import dominio.usuario.Usuario;

public enum HistoriApp {
	INSTANCE;
	
	private Usuario usuario;
	private RepositorioUsuarios usuarios;
	private RepositorioCursos cursos;
	
	private HistoriApp() {
		usuario = null;
		usuarios = RepositorioUsuarios.INSTANCE;
		cursos = RepositorioCursos.INSTANCE;
	}
	
	// NO CASOS DE USO
	
	private void actualizarUsuario(Usuario usuario) {
        usuarios.updateUsuario(usuario);
    }
	
	private Usuario getUsuario() {
		return usuario;
	}
	
	/////////////////////////////////////////////////////////
	// 1.- Acceso de usuarios ///////////////////////////////
	/////////////////////////////////////////////////////////
	
	
	// 1.1.- Registro de un nuevo usuario
	
	public boolean registrarUsuario(String nombre, String contrasena, String imagen, String saludo) {
		//if (existeUsuario(nombre))
		//	return false;
		Usuario usuario = new Usuario(nombre, contrasena, imagen, saludo, LocalDateTime.now());
		usuarios.addUsuario(usuario);	
		return true;
	}

	public boolean registrarProfesor(String nombre, String contrasena, String imagen, String saludo) {
		//if (existeUsuario(nombre))
		//	return false;
		Profesor profesor = new Profesor(nombre, contrasena, imagen, saludo, LocalDateTime.now());
		usuarios.addUsuario(profesor);	
		return true;
	}
	
	// 1.2.- Inicio de sesion
	
	public boolean loginUsuario(String nombre, String password) {
		// BLOQUE PARA COMPROBAR LA BASE DE DATOS 
		
		Usuario usuario = usuarios.findUsuarioPorNombre(nombre);
		if (usuario != null && usuario.getContrasena().equals(password)) {
			this.usuario = usuario;
			return true;
		}
		return false;
	}
	
	// 1.3.- Cambiar información de perfil
	
	public void cambiarImagen(String imagen) {
		usuario.setImagen(imagen);
		actualizarUsuario(usuario);
	}
	
	public void cambiarSaludo(String saludo) {
		usuario.setSaludo(saludo);
		actualizarUsuario(usuario);
	}
	
	////////////
	
	public boolean crearCurso(String rutaCurso) {
	    if (!(usuario instanceof Profesor)) {
	        return false;
	    }
	    
	    Path origen = Paths.get(rutaCurso);
	    Path destino = Paths.get("src/main/resources/" + origen.getFileName());
	    
	    try {
	        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
