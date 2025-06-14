package controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import repositorios.RepositorioCursos;
import repositorios.RepositorioUsuarios;

import modelo.*;

public enum HistoriApp {
	INSTANCE;
	
	private Usuario usuario;
	private Curso cursoActual = null;
	private BloqueContenidos bloqueActual = null;
	private IteradorTarea iteradorTarea;
	private RepositorioUsuarios usuarios;
	private RepositorioCursos cursos;
	
	private boolean inicializado = false;

    public void init(RepositorioUsuarios usuarios, RepositorioCursos cursos) {
        if (inicializado) return;
        this.usuarios = usuarios;
        this.cursos = cursos;
        this.inicializado = true;
    }
	
	
	private void actualizarUsuario(Usuario usuario) {
        usuarios.actualizarUsuario(usuario);
    }
	
	@SuppressWarnings("unused")
	private Usuario getUsuario() {
		return usuario;
	}
	
	/////////////////////////////////////////////////////////
	// 1.- Acceso de usuarios ///////////////////////////////
	/////////////////////////////////////////////////////////
	
	
	// 1.1.- Registro de un nuevo usuario
	
	public boolean registrarUsuario(String nombre, String contrasena, String correo, String imagen, String saludo) {
		if (usuarioRegistrado(nombre, correo)){
			return false;
		}
		
		Usuario usuario = new Usuario(nombre, contrasena, correo, imagen, saludo);
		usuarios.agregarUsuario(usuario);	
		return true;
	}

	public boolean registrarProfesor(String nombre, String contrasena, String correo, String imagen, String saludo) {
		if (usuarioRegistrado(nombre, correo))
			return false;
			
		Profesor profesor = new Profesor(nombre, contrasena, correo, imagen, saludo);
		usuarios.agregarUsuario(profesor);	
		return true;
	}
	
	// 1.2.- Inicio de sesion
	
	public boolean iniciarSesionUsuario(String nombre, String contrasena) {
		// BLOQUE PARA COMPROBAR LA BASE DE DATOS 
		Usuario usuario = usuarios.encontrarUsuarioPorNombre(nombre);

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
	
	public void cambiarImagen(String imagen) {
		usuario.setImagen(imagen);
		actualizarUsuario(usuario);
	}
	
	public void cambiarSaludo(String saludo) {
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
		return imagenRuta;
	}

	public int getPuntuacionUsuario(){
		return usuario.getPuntuacion();
	}

	public int getMaxRachaUsuario(){
		return usuario.getMaxRacha();
	}

	////////////
	
	public infoClasificacion obtenerInfoClasificacion() {
		Map<String, String> clasificacion = usuarios.obtenerTodosLosUsuarios().stream()
			.sorted((u1, u2) -> Integer.compare(u2.getPuntuacion(), u1.getPuntuacion()))
			.limit(6)
			.collect(Collectors.toMap(
				Usuario::getNombre,
				u -> String.format("%d", u.getPuntuacion()),
				(a, b) -> a,
				LinkedHashMap::new
			));

		return new infoClasificacion(clasificacion);
	}


	////////////
	
	public String getCursoActual() {
		return cursoActual.getTitulo();
	}
	
	public List<InfoCurso> getCursos() {
		return InfoCurso.getListInfoCurso(cursos.getCursos());
	}
	
	public List<InfoBloque> getBloques() {
		return InfoBloque.getListInfoBloque(cursos.getBloques(cursoActual));
	}
	
	public boolean matricularCurso(String nombreCurso, MetodoAprendizaje metodoAprendizaje) {
		Curso curso = cursos.buscarCursoPorNombre(nombreCurso);
		if(curso == null || usuario.estaMatriculado(nombreCurso))
			return false;
		usuario.matricularCurso(curso, metodoAprendizaje);
		return true;
	}
	
	
	public boolean realizarCurso(String nombreCurso) {
		Curso curso = cursos.buscarCursoPorNombre(nombreCurso);
		if(curso == null)
			return false;
		cursoActual = curso;
		return true;
	}
	
	public boolean realizarBloque(String bloqueNombre) {
		bloqueActual = cursoActual.getBloquePorNombre(bloqueNombre);
		iteradorTarea = FactoriaIteradorTarea.crearIterador(bloqueActual.getTareas(), usuario.getMetodoAprendizaje(cursoActual).get());
		return true;
	}
	
	public void cerrarBloque() {
		bloqueActual = null;
	}
	
	public Info siguiente(Optional<String> respuesta) {
		Optional<Tarea> tareaSiguiente = iteradorTarea.siguiente(respuesta);
		Info infoTarea = tareaSiguiente.isPresent() ? tareaSiguiente.get().crearInfo() : null;
		return infoTarea;
	}
	
	public int obtenerPuntuacion() {
		int puntuacion = iteradorTarea.obtenerPuntuacion();
		usuario.completarBloque(cursoActual, bloqueActual, puntuacion);
		return puntuacion;
	}
	
	public boolean crearCurso(String rutaCurso) {
	    if (!esProfesor()) {
	        return false;
	    }
	    
	    Path origen = Paths.get(rutaCurso);
	    Path destino = Paths.get("resources/cursos/" + origen.getFileName());
	   
	    
	    try {
	        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
	        cursos.anadirCurso(destino.toString());
			((Profesor) usuario).publicarCurso(); //Ya hemos asegurado que usuario es profesor
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/* Comprobación de registro */
	public boolean usuarioRegistrado(String nombre, String correo) {
		return usuarioRegistrado(nombre) || usuarios.encontrarUsuarioPorCorreo(correo) != null;
	}
	
	public boolean usuarioRegistrado(String nombre) {
		return usuarios.encontrarUsuarioPorNombre(nombre) != null;
	}
	
	/* Obtención del rol */
	public boolean esProfesor() {
		return usuario.esProfesor();
	}
	
	/* Comprobación de matrícula */
	public boolean usuarioMatriculado(String nombreCurso) {
		return usuario.estaMatriculado(nombreCurso);
	}
	
	/* Notificar curso completado */
	public boolean cursoCompletado() {
		return cursoCompletado(cursoActual.getTitulo());
	}
	
	public boolean cursoCompletado(String nombreCurso) {
		return usuario.haCompletado(nombreCurso) && ! usuario.estaMatriculado(nombreCurso);
	}
	
	private boolean bloqueCompletado(String nombreCurso, String nombreBloque) {
		return usuario.bloqueCompletado(nombreCurso, nombreBloque);
	}
	
	public boolean bloqueCompletado(String nombreBloque) {
		return bloqueCompletado(cursoActual.getTitulo(), nombreBloque);
	}
	
	/* Ver y crear valoraciones */
	public List<InfoValoracion> obtenerValoraciones() {
		return usuario.getValoraciones().stream()
				.map(InfoValoracion::new)
				.collect(Collectors.toList());
	}
	
	public List<InfoValoracion> obtenerValoracionesPorCursoNombre(String cursoNombre) {
		return usuarios.obtenerTodosLosUsuarios().stream()
				.flatMap(usuario -> usuario.getValoracionesPorCursoNombre(cursoNombre).stream())
				.map(InfoValoracion::new)
				.collect(Collectors.toList());
	}
	
	public boolean hacerValoracion(String comentario, int puntuacion) {
		return usuario.hacerValoracion(cursoActual, comentario, puntuacion);		
	}
	
	public boolean hayAlgunBloqueCompletado() {
		return usuario.getBloquesCompletados() > 0;
	}
}
