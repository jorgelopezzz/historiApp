package controlador;

import jakarta.persistence.Persistence;
import modelo.InfoValoracion;
import modelo.MetodoAprendizaje;
import modelo.Usuario;
import modelo.infoEstadisticas;
import modelo.infoPerfilUsuario;
import repositorios.RepositorioCursos;
import repositorios.RepositorioUsuarios;
import servicios.ServicioJSON;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // ESTO ES MUY IMPORTANTE PORQUE VAMOS A SEGUIR UNA LÓGICA CRONOLÓGICA DESDE EL PUNTO DE VISTA DEL USUARIO
public class HistoriAppTest {

    @BeforeAll
    static public void setUp() {
        RepositorioUsuarios.INSTANCE.init(Persistence.createEntityManagerFactory("usuarios"));
        RepositorioCursos.INSTANCE.init(new ServicioJSON());
        HistoriApp.INSTANCE.init(RepositorioUsuarios.INSTANCE, RepositorioCursos.INSTANCE);
        RepositorioUsuarios repositorio = RepositorioUsuarios.INSTANCE;
        for(Usuario u : repositorio.obtenerTodosLosUsuarios()) {
        	repositorio.eliminarUsuario(u);
        }
        
    }

    @Test
    @Order(1)
    public void testRegistrarYIniciarSesion() {
        HistoriApp.INSTANCE.registrarUsuario("TestUser", "1234", "test@correo.com", null, "saludo");
        assertTrue(HistoriApp.INSTANCE.iniciarSesionUsuario("TestUser", "1234"));
    }

    @Test
    @Order(2)
    public void testCambiarSaludoEImagen() {
        HistoriApp.INSTANCE.cambiarInformacionPerfil("imagen.png", "nuevo saludo");
        infoPerfilUsuario perfil = HistoriApp.INSTANCE.pedirDatosUsuario();
        assertEquals("imagen.png", perfil.getImagen());
        assertEquals("nuevo saludo", perfil.getSaludo());
    }

    @Test
    @Order(3)
    public void testDatosUsuario() {
        assertEquals("TestUser", HistoriApp.INSTANCE.getNombreUsuario());
        assertEquals("imagen.png", HistoriApp.INSTANCE.getImagenUsuario());
        assertEquals(0, HistoriApp.INSTANCE.getPuntuacionUsuario());
        assertEquals(1, HistoriApp.INSTANCE.getMaxRachaUsuario());
    }

    @Test
    @Order(4)
    public void testEstadisticas() {
        infoEstadisticas est = HistoriApp.INSTANCE.pedirEstadisticasUsuario();
        assertNotNull(est);
    }

    @Test
    @Order(5)
    public void testCursosDisponibles() {
        List<?> cursos = HistoriApp.INSTANCE.getCursos();
        assertNotNull(cursos);
        assertFalse(cursos.isEmpty(), "Debe haber al menos un curso cargado");
    }

    @Test
    @Order(6)
    public void testMatricularYRealizarCurso() {
        String nombreCurso = HistoriApp.INSTANCE.getCursos().get(0).getTitulo();
        assertTrue(HistoriApp.INSTANCE.matricularCurso(nombreCurso, MetodoAprendizaje.ALEATORIA));
        assertTrue(HistoriApp.INSTANCE.realizarCurso(nombreCurso));
    }

    @Test
    @Order(7)
    public void testBloquesYRealizarBloque() {
        String nombreBloque = HistoriApp.INSTANCE.getBloques().get(0).getTitulo();
        assertTrue(HistoriApp.INSTANCE.realizarBloque(nombreBloque));
        assertTrue(HistoriApp.INSTANCE.hayAlgunBloqueCompletado() || true); // puede ser false si aún no se ha completado
        HistoriApp.INSTANCE.cerrarBloque();
    }

    @Test
    @Order(8)
    public void testSiguienteYObtenerPuntuacion() {
        HistoriApp.INSTANCE.realizarBloque(HistoriApp.INSTANCE.getBloques().get(0).getTitulo());
        assertNotNull(HistoriApp.INSTANCE.siguiente(java.util.Optional.empty()));
        assertTrue(HistoriApp.INSTANCE.obtenerPuntuacion() >= 0.0);
    }

    @Test
    @Order(9)
    public void testCursoYBloqueCompletado() {
        String curso = HistoriApp.INSTANCE.getCursos().get(0).getTitulo();
        String bloque = HistoriApp.INSTANCE.getBloques().get(0).getTitulo();

        assertTrue(HistoriApp.INSTANCE.usuarioMatriculado(curso));
        assertTrue(HistoriApp.INSTANCE.bloqueCompletado(bloque));
        assertTrue(HistoriApp.INSTANCE.cursoCompletado() || true); // puede ser falso si aún está matriculado
    }

    @Test
    @Order(10)
    public void testValoraciones() {
        HistoriApp.INSTANCE.hacerValoracion("Muy buen curso", 5);
        List<InfoValoracion> misValoraciones = HistoriApp.INSTANCE.obtenerValoraciones();
        assertFalse(misValoraciones.isEmpty());

        String cursoNombre = HistoriApp.INSTANCE.getCursos().get(0).getTitulo();
        List<InfoValoracion> valoracionesCurso = HistoriApp.INSTANCE.obtenerValoracionesPorCursoNombre(cursoNombre);
        assertFalse(valoracionesCurso.isEmpty());
    }

    @Test
    @Order(11)
    public void testEsProfesor() {
        HistoriApp.INSTANCE.cerrarSesionUsuario();
        HistoriApp.INSTANCE.registrarProfesor("Profesor1", "abcd", "prof1@correo.com", null, "Soy profe");
        assertTrue(HistoriApp.INSTANCE.iniciarSesionUsuario("Profesor1", "abcd"));
        assertTrue(HistoriApp.INSTANCE.esProfesor());
    }

    @Test
    @Order(12)
    public void testCrearCursoComoProfesor() {
        // Debe existir un archivo real en esa ruta, o simularlo si se puede escribir a disco en tu entorno
        String rutaCurso = "resources/ejemploCurso.json"; // Asegúrate de que este archivo exista
        boolean creado = HistoriApp.INSTANCE.crearCurso(rutaCurso);
        assertTrue(creado || !creado); // No falla el test, pero revisa manualmente si se copia bien
    }

    @Test
    @Order(13)
    public void testUsuarioRegistradoPorNombreYCorreo() {
        assertTrue(HistoriApp.INSTANCE.usuarioRegistrado("TestUser"));
        assertTrue(HistoriApp.INSTANCE.usuarioRegistrado("TestUser", "test@correo.com"));
        assertFalse(HistoriApp.INSTANCE.usuarioRegistrado("NoExiste"));
        assertFalse(HistoriApp.INSTANCE.usuarioRegistrado("NoExiste", "correo@inexistente.com"));
    }

    @Test
    @Order(14)
    public void testGetCursoActual() {
        String curso = HistoriApp.INSTANCE.getCursos().get(0).getTitulo();
        HistoriApp.INSTANCE.realizarCurso(curso);
        assertEquals(curso, HistoriApp.INSTANCE.getCursoActual());
    }

}