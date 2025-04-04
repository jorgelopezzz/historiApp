package dominio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import dominio.curso.BloqueContenidos;
import dominio.curso.Curso;
import dominio.curso.RepositorioCursos;
import dominio.info.Info;
import dominio.info.contenidos.InfoBloque;
import dominio.info.contenidos.InfoCurso;
import dominio.info.usuario.infoEstadisticas;
import dominio.info.usuario.infoPerfilUsuario;
import dominio.metodoAprendizaje.FactoriaIteradorTarea;
import dominio.metodoAprendizaje.IteradorTarea;
import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.tarea.Tarea;
import dominio.usuario.Profesor;
import dominio.usuario.RepositorioUsuarios;
import dominio.usuario.Usuario;

public enum HistoriApp {
	INSTANCE;
	
	private Usuario usuario;
	private Curso cursoActual;
	private MetodoAprendizaje metodoAprendizaje;
	private IteradorTarea iteradorTarea;
	private RepositorioUsuarios usuarios;
	private RepositorioCursos cursos;
	
	private HistoriApp() {
		usuarios = RepositorioUsuarios.INSTANCE;
		cursos = RepositorioCursos.INSTANCE;
		registrarUsuario("a", "a", "a", null, "a");
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
	
	public boolean registrarUsuario(String nombre, String contrasena, String correo, String imagen, String saludo) {
		if (usuarios.findUsuarioPorNombre(nombre) != null){
			return false;
		}
		Usuario usuario = new Usuario(nombre, contrasena, correo, imagen, saludo);
		usuarios.addUsuario(usuario);	
		return true;
	}

	public boolean registrarProfesor(String nombre, String contrasena, String correo, String imagen, String saludo) {
		if (usuarios.findUsuarioPorNombre(nombre) != null){
			return false;
		}
		Profesor profesor = new Profesor(nombre, contrasena, correo, imagen, saludo);
		usuarios.addUsuario(profesor);	
		return true;
	}
	
	// 1.2.- Inicio de sesion
	
	public boolean iniciarSesionUsuario(String nombre, String contrasena) {
		// BLOQUE PARA COMPROBAR LA BASE DE DATOS 
		Usuario usuario = usuarios.findUsuarioPorNombre(nombre);

		if (usuario != null && usuario.checkContrasena(contrasena)) {
			this.usuario = usuario;
			return usuario.iniciarSesion();
		}
		return false;
	}

	public boolean cerrarSesionUsuario(){
		if(usuario != null){
			return usuario.cerrarSesion();
		}
		return false;
	}
	
	// 1.3.- Cambiar información de perfil

	public infoPerfilUsuario pedirDatosUsuario(){
		return usuario.getDatosPerfil();
	}

	public void cambiarInformacionPerfil(String imagen, String saludo){ //OJO que pasa si solo se cambia una? Hay que revisar cuando sea funcional para ver flecos
		cambiarImagen(imagen);
		cambiarSaludo(saludo);
	}
	
	private void cambiarImagen(String imagen) {
		usuario.setImagen(imagen);
		actualizarUsuario(usuario);
	}
	
	private void cambiarSaludo(String saludo) {
		usuario.setSaludo(saludo);
		actualizarUsuario(usuario);
	}

	////////////

	public infoEstadisticas pedirEstadisticasUsuario(){
		return usuario.getEstadisticas();
	}
	
	public String getNombreUsuario() {
		return usuario.getNombre();
	}
	
	public String getImagenUsuario() {
		String imagenRuta = usuario.getImagen();
		return imagenRuta == null ? "/perfil.png" : imagenRuta;
	}

	public int getPuntuacionUsuario(){
		return usuario.getPuntuacion();
	}

	public int getMaxRachaUsuario(){
		return usuario.getMaxRacha();
	}
	
	////////////
	
	public Curso getCursoActual() {
		return cursoActual;
	}
	
	public List<InfoCurso> getCursos() {
		return InfoCurso.getListInfoCurso(cursos.getCursos());
	}
	
	public List<InfoBloque> getBloques() {
		return InfoBloque.getListInfoBloque(cursos.getBloques(cursoActual));
	}
	
	public boolean realizarCurso(String nombreCurso, MetodoAprendizaje metodoAprendizaje) {
		this.cursoActual = cursos.buscarCursoPorNombre(nombreCurso);
		this.metodoAprendizaje = metodoAprendizaje;
		return true;
	}
	
	public boolean realizarBloque(String bloqueNombre) {
		BloqueContenidos bloque = cursoActual.getBloquePorNombre(bloqueNombre);
		iteradorTarea = FactoriaIteradorTarea.crearIterador(bloque.getTareas(), metodoAprendizaje);
		return true;
	}
	
	public Info siguiente(Optional<String> respuesta) {
		//if(!iteradorTarea.tieneSiguiente()) 
		//	return null;
		
		Optional<Tarea> tareaSiguiente = iteradorTarea.siguiente(respuesta);
		Info infoTarea = tareaSiguiente.isPresent() ? tareaSiguiente.get().crearInfo() : null;
		return infoTarea;
	}
	
	public double obtenerPuntuacion() {
		return iteradorTarea.obtenerPuntuacion();
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
