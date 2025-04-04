package dominio.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dominio.info.usuario.infoEstadisticas;
import dominio.info.usuario.infoPerfilUsuario;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Jorge", "1234", "egea@correo.com", null, "Alfonso");
    }

    @ParameterizedTest
    @MethodSource("usuariosValidos")
    void testConstructorValido(String nombre, String contrasena, String correo, String saludo) {
        Usuario u = new Usuario(nombre, contrasena, correo, null, saludo);
        assertEquals(nombre, u.getNombre());
        assertEquals(saludo, u.getSaludo());
        assertEquals(correo, u.getDatosPerfil().getCorreo());
        assertNotNull(u.getFechaRegistro());
    }

    static List<Arguments> usuariosValidos() {
        return List.of(
            Arguments.of("Jorge", "1234", "jorge@correo.com", "Alfonso"),
            Arguments.of("María", "abcd", "maria@gmail.com", "¡Hola!"),
            Arguments.of("Luis", "contraseña", "luis@yahoo.es", "Buenos días")
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

    @Test
    void testImagenPorDefecto() {
        String imagen = usuario.getImagen();
        assertNotNull(imagen);
        assertTrue(imagen.endsWith("perfil.png"));
    }

    @Test
    void testIniciarYcerrarSesionAumentaTiempoUso() throws InterruptedException {
        usuario.iniciarSesion();
        Thread.sleep(100);
        usuario.cerrarSesion();
        infoEstadisticas estadisticas = usuario.getEstadisticas();
        assertNotNull(estadisticas.getMinutosUso());
        assertTrue(estadisticas.getMinutosUso().matches("\\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    void testGetEstadisticas() {
        infoEstadisticas estadisticas = usuario.getEstadisticas();
        assertEquals(0, estadisticas.getPuntuacion());
        assertEquals(0, estadisticas.getCursosCompletados());
        assertEquals("00:00:00", estadisticas.getMinutosUso());
        assertEquals("00:00", estadisticas.getMinutosUsoDiario());
        assertEquals(0, estadisticas.getMaxRacha());
    }

    @Test
    void testGetDatosPerfil() {
        infoPerfilUsuario perfil = usuario.getDatosPerfil();
        assertEquals("Jorge", perfil.getNombre());
        assertEquals("egea@correo.com", perfil.getCorreo());
        assertEquals("Alfonso", perfil.getSaludo());
        assertEquals("Estudiante", perfil.getRol());
        assertTrue(perfil.getImagen().endsWith("perfil.png"));
    }

    @Test
    void testModificarSaludoEImagen() {
        usuario.setSaludo("Buenos días");
        usuario.setImagen("aaaa.png");
        assertEquals("Buenos días", usuario.getSaludo());
        assertEquals("aaaa.png", usuario.getImagen());
    }
}
