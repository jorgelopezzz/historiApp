package dominio;

import dominio.curso.BloqueContenidos;
import dominio.curso.Curso;
import dominio.info.Info;
import dominio.info.contenidos.InfoCurso;
import dominio.info.usuario.infoEstadisticas;
import dominio.info.usuario.infoPerfilUsuario;
import dominio.metodoAprendizaje.IteradorTarea;
import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.tarea.Tarea;
import dominio.usuario.Profesor;
import dominio.usuario.Usuario;
import repositorios.RepositorioCursos;
import repositorios.RepositorioUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HistoriAppTest {

    private HistoriApp app;

    @Mock private RepositorioUsuarios mockRepoUsuarios;
    @Mock private RepositorioCursos mockRepoCursos;

    @Mock private Usuario mockUsuario;
    @Mock private Profesor mockProfesor;
    @Mock private Curso mockCurso;
    @Mock private BloqueContenidos mockBloque;
    @Mock private MetodoAprendizaje mockMetodo;
    @Mock private IteradorTarea mockIterador;
    @Mock private Tarea mockTarea;
    @Mock private infoPerfilUsuario mockInfoPerfil;
    @Mock private infoEstadisticas mockEstadisticas;

    @BeforeEach
    void configurar() {
        MockitoAnnotations.openMocks(this);
        app = HistoriApp.INSTANCE;
        app.init(mockRepoUsuarios, mockRepoCursos);
    }

    // 1. Registro de usuario
    @Test
    void registrarUsuarioNuevo() {
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("nuevo")).thenReturn(null);
        when(mockRepoUsuarios.encontrarUsuarioPorCorreo("nuevo@correo.com")).thenReturn(null);

        boolean resultado = app.registrarUsuario("nuevo", "clave", "nuevo@correo.com", "imagen", "saludo");

        assertTrue(resultado);
        verify(mockRepoUsuarios).agregarUsuario(any(Usuario.class));
    }

    @Test
    void registrarUsuarioExistente() {
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("existente")).thenReturn(mockUsuario);

        boolean resultado = app.registrarUsuario("existente", "clave", "existente@correo.com", "imagen", "saludo");

        assertFalse(resultado);
        verify(mockRepoUsuarios, never()).agregarUsuario(any());
    }

    // 2. Registro de profesor
    @Test
    void registrarProfesorNuevo() {
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("prof")).thenReturn(null);
        when(mockRepoUsuarios.encontrarUsuarioPorCorreo("prof@correo.com")).thenReturn(null);

        boolean resultado = app.registrarProfesor("prof", "clave", "prof@correo.com", "imagen", "saludo");

        assertTrue(resultado);
        verify(mockRepoUsuarios).agregarUsuario(any(Profesor.class));
    }

    // 3. Inicio y cierre de sesión
    @Test
    void iniciarSesionCorrecta() {
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("user")).thenReturn(mockUsuario);
        when(mockUsuario.checkContrasena("clave")).thenReturn(true);
        when(mockUsuario.iniciarSesion()).thenReturn(true);

        boolean resultado = app.iniciarSesionUsuario("user", "clave");

        assertTrue(resultado);
        verify(mockUsuario).iniciarSesion();
    }

    @Test
    void iniciarSesionIncorrecta() {
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("user")).thenReturn(null);

        boolean resultado = app.iniciarSesionUsuario("user", "clave");

        assertFalse(resultado);
    }

    @Test
    void cerrarSesionUsuarioActivo() {
        when(mockUsuario.cerrarSesion()).thenReturn(true);
        app.iniciarSesionUsuario("user", "clave"); // Para establecer el usuario
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("user")).thenReturn(mockUsuario);
        when(mockUsuario.checkContrasena("clave")).thenReturn(true);
        when(mockUsuario.iniciarSesion()).thenReturn(true);

        app.iniciarSesionUsuario("user", "clave");
        boolean resultado = app.cerrarSesionUsuario();

        assertTrue(resultado);
        verify(mockUsuario).cerrarSesion();
    }

    // 4. Perfil de usuario
    @Test
    void pedirDatosPerfilUsuario() {
        app.iniciarSesionUsuario("user", "clave");
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("user")).thenReturn(mockUsuario);
        when(mockUsuario.checkContrasena("clave")).thenReturn(true);
        when(mockUsuario.iniciarSesion()).thenReturn(true);
        when(mockUsuario.getDatosPerfil()).thenReturn(mockInfoPerfil);

        app.iniciarSesionUsuario("user", "clave");
        assertEquals(mockInfoPerfil, app.pedirDatosUsuario());
    }

    @Test
    void cambiarImagenYSaludo() {
        when(mockUsuario.getNombre()).thenReturn("user");
        app.iniciarSesionUsuario("user", "clave");
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("user")).thenReturn(mockUsuario);
        when(mockUsuario.checkContrasena("clave")).thenReturn(true);
        when(mockUsuario.iniciarSesion()).thenReturn(true);

        app.iniciarSesionUsuario("user", "clave");
        app.cambiarInformacionPerfil("nueva_imagen.png", "nuevo saludo");

        verify(mockUsuario).setImagen("nueva_imagen.png");
        verify(mockUsuario).setSaludo("nuevo saludo");
        verify(mockRepoUsuarios, atLeastOnce()).actualizarUsuario(mockUsuario);
    }

    // 5. Estadísticas
    @Test
    void pedirEstadisticas() {
        when(mockUsuario.getEstadisticas()).thenReturn(mockEstadisticas);
        app.iniciarSesionUsuario("user", "clave");
        when(mockRepoUsuarios.encontrarUsuarioPorNombre("user")).thenReturn(mockUsuario);
        when(mockUsuario.checkContrasena("clave")).thenReturn(true);
        when(mockUsuario.iniciarSesion()).thenReturn(true);

        app.iniciarSesionUsuario("user", "clave");
        assertEquals(mockEstadisticas, app.pedirEstadisticasUsuario());
    }

    // 6. Curso actual
    @Test
    void obtenerCursosDisponibles() {
        List<Curso> cursos = List.of(mockCurso);
        when(mockRepoCursos.getCursos()).thenReturn(cursos);
        when(mockCurso.getTitulo()).thenReturn("Curso 1");

        List<InfoCurso> infoCursos = app.getCursos();

        assertFalse(infoCursos.isEmpty());
    }

    @Test
    void matricularCursoNuevo() {
        when(mockCurso.getTitulo()).thenReturn("Historia");
        when(mockRepoCursos.buscarCursoPorNombre("Historia")).thenReturn(mockCurso);
        when(mockUsuario.estaMatriculado("Historia")).thenReturn(false);

        boolean resultado = app.matricularCurso("Historia", mockMetodo);

        assertTrue(resultado);
        verify(mockUsuario).matricularCurso(mockCurso, mockMetodo);
    }

    @Test
    void realizarCursoExistente() {
        when(mockCurso.getTitulo()).thenReturn("Curso A");
        when(mockRepoCursos.buscarCursoPorNombre("Curso A")).thenReturn(mockCurso);

        boolean resultado = app.realizarCurso("Curso A");

        assertTrue(resultado);
    }

    @Test
    void realizarBloqueYAvanzar() {
        when(mockCurso.getTitulo()).thenReturn("Curso B");
        when(mockCurso.getBloquePorNombre("Bloque 1")).thenReturn(mockBloque);
        when(mockUsuario.getMetodoAprendizaje(mockCurso)).thenReturn(Optional.of(mockMetodo));
        when(mockBloque.getTareas()).thenReturn(List.of(mockTarea));
        when(mockTarea.crearInfo()).thenReturn(mock(Info.class));

        app.realizarCurso("Curso B");
        app.realizarBloque("Bloque 1");
        Info info = app.siguiente(Optional.empty());

        assertNotNull(info);
    }

    @Test
    void obtenerPuntuacionBloque() {
        when(mockIterador.obtenerPuntuacion()).thenReturn(80.0);
        app.realizarCurso("Curso C");
        app.realizarBloque("Bloque C");

        double puntuacion = app.obtenerPuntuacion();

        assertTrue(puntuacion >= 0);
    }

    @Test
    void hacerValoracion() {
        when(mockUsuario.hacerValoracion(any(), eq("Muy buen curso"), eq(5))).thenReturn(true);

        boolean resultado = app.hacerValoracion("Muy buen curso", 5);

        assertTrue(resultado);
    }

    @Test
    void comprobarBloquesYCursoCompletado() {
        when(mockUsuario.haCompletado("Curso X")).thenReturn(true);
        when(mockUsuario.estaMatriculado("Curso X")).thenReturn(false);

        app.realizarCurso("Curso X");
        assertTrue(app.cursoCompletado());
    }
}
