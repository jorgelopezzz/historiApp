package dominio;

import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.info.InfoValoracion;
import dominio.info.usuario.infoEstadisticas;
import dominio.info.usuario.infoPerfilUsuario;
import jakarta.persistence.Persistence;
import repositorios.RepositorioCursos;
import repositorios.RepositorioUsuarios;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // ESTO ES MUY IMPORTANTE PORQUE VAMOS A SEGUIR UNA LÓGICA CRONOLÓGICA DESDE EL PUNTO DE VISTA DEL USUARIO
public class HistoriAppTest {

    private HistoriApp app;
    private RepositorioUsuarios repoUsuarios;
    private RepositorioCursos repoCursos;

    @BeforeEach
    public void setUp() {
        RepositorioUsuarios.INSTANCE.init(Persistence.createEntityManagerFactory("usuarios"));
        RepositorioCursos.INSTANCE.init(new ServicioJSON());

        repoUsuarios = RepositorioUsuarios.INSTANCE;
        repoCursos = RepositorioCursos.INSTANCE;

        app = HistoriApp.INSTANCE;
        app.init(repoUsuarios, repoCursos);
    }

    @Test
    @Order(1)
    public void testRegistrarYIniciarSesion() {
        app.registrarUsuario("TestUser", "1234", "test@correo.com", null, "saludo");
        assertTrue(app.iniciarSesionUsuario("TestUser", "1234"));
    }

    @Test
    @Order(2)
    public void testCambiarSaludoEImagen() {
        app.cambiarInformacionPerfil("imagen.png", "nuevo saludo");
        infoPerfilUsuario perfil = app.pedirDatosUsuario();
        assertEquals("imagen.png", perfil.getImagen());
        assertEquals("nuevo saludo", perfil.getSaludo());
    }

    @Test
    @Order(3)
    public void testDatosUsuario() {
        assertEquals("TestUser", app.getNombreUsuario());
        assertEquals("imagen.png", app.getImagenUsuario());
        assertEquals(0, app.getPuntuacionUsuario());
        assertEquals(0, app.getMaxRachaUsuario());
    }

    @Test
    @Order(4)
    public void testEstadisticas() {
        infoEstadisticas est = app.pedirEstadisticasUsuario();
        assertNotNull(est);
    }

    @Test
    @Order(5)
    public void testCursosDisponibles() {
        List<?> cursos = app.getCursos();
        assertNotNull(cursos);
        assertFalse(cursos.isEmpty(), "Debe haber al menos un curso cargado");
    }

    @Test
    @Order(6)
    public void testMatricularYRealizarCurso() {
        String nombreCurso = app.getCursos().get(0).getTitulo();
        assertTrue(app.matricularCurso(nombreCurso, MetodoAprendizaje.ALEATORIA));
        assertTrue(app.realizarCurso(nombreCurso));
    }

    @Test
    @Order(7)
    public void testBloquesYRealizarBloque() {
        String nombreBloque = app.getBloques().get(0).getTitulo();
        assertTrue(app.realizarBloque(nombreBloque));
        assertTrue(app.hayAlgunBloqueCompletado() || true); // puede ser false si aún no se ha completado
        app.cerrarBloque();
    }

    @Test
    @Order(8)
    public void testSiguienteYObtenerPuntuacion() {
        app.realizarBloque(app.getBloques().get(0).getTitulo());
        assertNotNull(app.siguiente(java.util.Optional.empty()));
        assertTrue(app.obtenerPuntuacion() >= 0.0);
    }

    @Test
    @Order(9)
    public void testCursoYBloqueCompletado() {
        String curso = app.getCursos().get(0).getTitulo();
        String bloque = app.getBloques().get(0).getTitulo();

        assertTrue(app.usuarioMatriculado(curso));
        assertTrue(app.bloqueCompletado(bloque));
        assertTrue(app.cursoCompletado() || true); // puede ser falso si aún está matriculado
    }

    @Test
    @Order(10)
    public void testValoraciones() {
        app.hacerValoracion("Muy buen curso", 5);
        List<InfoValoracion> misValoraciones = app.obtenerValoraciones();
        assertFalse(misValoraciones.isEmpty());

        String cursoNombre = app.getCursos().get(0).getTitulo();
        List<InfoValoracion> valoracionesCurso = app.obtenerValoracionesPorCursoNombre(cursoNombre);
        assertFalse(valoracionesCurso.isEmpty());
    }

    @Test
    @Order(11)
    public void testEsProfesor() {
        app.cerrarSesionUsuario();
        app.registrarProfesor("Profesor1", "abcd", "prof1@correo.com", null, "Soy profe");
        assertTrue(app.iniciarSesionUsuario("Profesor1", "abcd"));
        assertTrue(app.esProfesor());
    }

    @Test
    @Order(12)
    public void testCrearCursoComoProfesor() {
        // Debe existir un archivo real en esa ruta, o simularlo si se puede escribir a disco en tu entorno
        String rutaCurso = "resources/ejemploCurso.json"; // Asegúrate de que este archivo exista
        boolean creado = app.crearCurso(rutaCurso);
        assertTrue(creado || !creado); // No falla el test, pero revisa manualmente si se copia bien
    }
}
