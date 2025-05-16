package dominio.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import modelo.Profesor;
import modelo.infoEstadisticasProfesor;
import modelo.infoPerfilUsuario;

class ProfesorTest {

    private Profesor profesor;

    @BeforeEach
    void setUp() {
        profesor = new Profesor("Jorge", "1234", "jorge@correo.com", null, "Alfonso");
    }

    @ParameterizedTest
    @MethodSource("profesoresValidos")
    void testConstructorValido(String nombre, String contrasena, String correo, String saludo) {
        Profesor p = new Profesor(nombre, contrasena, correo, null, saludo);
        assertEquals(nombre, p.getNombre());
        assertEquals(saludo, p.getSaludo());
        assertEquals(correo, p.getDatosPerfil().getCorreo());
        assertNotNull(p.getFechaRegistro());
    }

    static List<Arguments> profesoresValidos() {
        return List.of(
            Arguments.of("Jorge", "1234", "jorge@correo.com", "Alfonso"),
            Arguments.of("Alfonso", "abcd", "alfonso@correo.com", "Alejandro"),
            Arguments.of("Alejandro", "clave", "alejandro@correo.com", "Jorge")
        );
    }

    @ParameterizedTest
    @MethodSource("profesoresInvalidos")
    void testConstructorDatosNulos(String nombre, String contrasena, String correo) {
        assertThrows(IllegalArgumentException.class, () -> new Profesor(nombre, contrasena, correo, null, "X"));
    }

    static List<Arguments> profesoresInvalidos() {
        return List.of(
            Arguments.of(null, "1234", "jorge@correo.com"),
            Arguments.of("Jorge", null, "jorge@correo.com"),
            Arguments.of("Jorge", "1234", null)
        );
    }

    @ParameterizedTest
    @MethodSource("contrasenas")
    void testCheckContrasena(String input, boolean esperado) {
        Profesor p = new Profesor("Jorge", "clave123", "jorge@correo.com", null, "Alfonso");
        assertEquals(esperado, p.checkContrasena(input));
    }

    static List<Arguments> contrasenas() {
        return List.of(
            Arguments.of("clave123", true),
            Arguments.of("CLAVE123", false),
            Arguments.of("incorrecta", false)
        );
    }

    @Test
    void testGetEstadisticasProfesor() {
        infoEstadisticasProfesor est = profesor.getEstadisticas();
        assertEquals("Jorge", est.getTitulo());
        assertEquals(0, est.getPuntuacion());
        assertEquals(0, est.getCursosCompletados());
        assertEquals("00:00:00", est.getMinutosUso());
        assertEquals("00:00:00", est.getMinutosUsoDiario());
        assertEquals(1, est.getMaxRacha());
        assertEquals(0, est.getCursosPublicados());
    }

    @Test
    void testGetDatosPerfilProfesor() {
        infoPerfilUsuario perfil = profesor.getDatosPerfil();
        assertEquals("Jorge", perfil.getNombre());
        assertEquals("jorge@correo.com", perfil.getCorreo());
        assertEquals("Alfonso", perfil.getSaludo());
        assertEquals("Profesor", perfil.getRol());
        assertTrue(perfil.getImagen().endsWith("perfil.png"));
    }

    @Test
    void testModificarSaludoEImagen() {
        profesor.setSaludo("Buenos días");
        profesor.setImagen("imagen.png");
        assertEquals("Buenos días", profesor.getSaludo());
        assertEquals("imagen.png", profesor.getImagen());
    }

}
