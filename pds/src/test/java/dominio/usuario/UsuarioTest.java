package dominio.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dominio.info.usuario.infoEstadisticas;
import dominio.info.usuario.infoPerfilUsuario;
import jakarta.persistence.Persistence;
import repositorios.RepositorioUsuarios;

class UsuarioTest {
    
    static List<Arguments> usuarioValido() {
        return List.of(Arguments.of(new Usuario("Jorge", "1234", "egea@correo.com", null, "Alfonso")));
    }

    @BeforeAll
    static void repositorySetUp() {
    	RepositorioUsuarios.INSTANCE.init(Persistence.createEntityManagerFactory("usuarios"));
    }
    
    @BeforeEach
    void setUp() {
        RepositorioUsuarios repositorio = RepositorioUsuarios.INSTANCE;
        for(Usuario u : repositorio.obtenerTodosLosUsuarios()) {
        	repositorio.eliminarUsuario(u);
        }
    }
    
    @ParameterizedTest
    @MethodSource("usuariosValidos")
    void testConstructorValido(String nombre, String contrasena, String correo, String rutaImagen, String saludo) {
        Usuario u = new Usuario(nombre, contrasena, correo, null, saludo);
        assertEquals(nombre, u.getNombre());
        assertEquals(saludo, u.getSaludo());
        assertEquals(correo, u.getDatosPerfil().getCorreo());
        assertNotNull(u.getFechaRegistro());
    }

    static List<Arguments> usuariosValidos() {
        return List.of(
            Arguments.of("Jorge", "1234", "jorge@correo.com", null, "Alfonso"),
            Arguments.of("María", "abcd", "maria@gmail.com", "imagen.png", "¡Hola!"),
            Arguments.of("Luis", "contraseña", "luis@yahoo.es", null, "Buenos días")
        );
    }

    @ParameterizedTest
    @MethodSource("usuariosInvalidos")
    void testConstructorDatosNulos(String nombre, String contrasena, String correo) {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, contrasena, correo, null, "X"));
    }

    static List<Arguments> usuariosInvalidos() {
        return List.of(
            Arguments.of(null, "1234", "jorge@correo.com"),
            Arguments.of("Jorge", null, "jorge@correo.com"),
            Arguments.of("Jorge", "1234", null)
        );
    }

    @ParameterizedTest
    @MethodSource("contrasenas")
    void testCheckContrasena(String input, boolean esperado) {
        Usuario u = new Usuario("Jorge", "clave123", "jorge@mail.com", null, "Hola");
        assertEquals(esperado, u.checkContrasena(input));
    }

    static List<Arguments> contrasenas() {
        return List.of(
            Arguments.of("clave123", true),
            Arguments.of("CLAVE123", false),
            Arguments.of("123", false)
        );
    }

    @ParameterizedTest
    @MethodSource("usuarioValido")
    void testImagenPorDefecto(Usuario usuario) {
        String imagen = usuario.getImagen();
        assertNotNull(imagen);
        assertTrue(imagen.endsWith("perfil.png"));
    }

    @ParameterizedTest
    @MethodSource("usuarioValido")
    void testIniciarYcerrarSesionAumentaTiempoUso(Usuario usuario) throws InterruptedException {
        usuario.iniciarSesion();
        Thread.sleep(1000);
        usuario.cerrarSesion();
        infoEstadisticas estadisticas = usuario.getEstadisticas();
        assertNotNull(estadisticas.getMinutosUso());
        assertTrue(estadisticas.getMinutosUso().matches("\\d{2}:\\d{2}:\\d{2}"));
    }
    
    @ParameterizedTest
    @MethodSource("usuarioValido")
    void testGetEstadisticas(Usuario usuario) {
        infoEstadisticas estadisticas = usuario.getEstadisticas();
        assertEquals(0, estadisticas.getPuntuacion());
        assertEquals(0, estadisticas.getCursosCompletados());
        assertEquals("00:00:00", estadisticas.getMinutosUso());
        assertEquals("00:00:00", estadisticas.getMinutosUsoDiario());
        assertTrue(estadisticas.getMaxRacha() >= 1);
    }


    @ParameterizedTest
    @MethodSource("usuarioValido")
    void testGetDatosPerfil(Usuario usuario) {
        infoPerfilUsuario perfil = usuario.getDatosPerfil();
        assertEquals("Jorge", perfil.getNombre());
        assertEquals("egea@correo.com", perfil.getCorreo());
        assertEquals("Alfonso", perfil.getSaludo());
        assertEquals("Estudiante", perfil.getRol());
        assertTrue(perfil.getImagen().endsWith("perfil.png"));
        assertFalse(usuario.esProfesor());
    }

    @ParameterizedTest
    @MethodSource("usuarioValido")
    void testModificarSaludoEImagen(Usuario usuario) {
        usuario.setSaludo("Buenos días");
        usuario.setImagen("aaaa.png");
        assertEquals("Buenos días", usuario.getSaludo());
        assertEquals("aaaa.png", usuario.getImagen());
    }
    
    @ParameterizedTest
    @MethodSource("usuarioValido")
    void testRachaSeReiniciaAlSaltarDia(Usuario usuario) {
        usuario.iniciarSesion();
        usuario.cerrarSesion();

        // Simular salto de más de un día
        usuario.ultimaConexion = usuario.ultimaConexion.minusDays(2);

        usuario.iniciarSesion();
        usuario.cerrarSesion();

        assertEquals(1, usuario.getMaxRacha());
    }



}
