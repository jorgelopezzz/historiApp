package dominio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import dominio.curso.Curso;
import dominio.curso.RepositorioCursos;
import dominio.info.contenidos.InfoCurso;
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
	
	public boolean registrarUsuario(String nombre, String movil, String contrasena, String imagen, String saludo) {
		//if (existeUsuario(nombre) || existeUsuario(movil))
		//	return false;
		Usuario usuario = new Usuario(nombre, movil, contrasena, imagen, LocalDateTime.now());
		usuarios.addUsuario(usuario);	
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
	
	// 1.3.- Cambiar imagen
	
	public void cambiarImagen(String imagen) {
		usuario.setImagen(imagen);
		actualizarUsuario(usuario);
	}
	
	// 1.4.- Cambiar saludo
	
	public void cambiarSaludo(String saludo) {
		usuario.setSaludo(saludo);
		actualizarUsuario(usuario);
	}
	
	////////////
	
	public List<InfoCurso> getCursos() {
		return InfoCurso.getListInfoCurso(cursos.getCursos());
	}
	
	
	
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
