package dominio.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dominio.info.usuario.infoPerfilUsuario;
import repositorios.RepositorioUsuarios;

class RepositorioUsuariosTest {

    private RepositorioUsuarios repositorio;

    @BeforeEach
    void setUp() {
        repositorio = RepositorioUsuarios.INSTANCE;
        for(Usuario u : repositorio.obtenerTodosLosUsuarios()) {
        	repositorio.eliminarUsuario(u);
        }
    }

    @ParameterizedTest
    @MethodSource("usuariosValidos")
    void testAddAndencontrarUsuario(String nombre, String contrasena, String correo, String saludo) {
        Usuario u = new Usuario(nombre, contrasena, correo, null, saludo);
        repositorio.agregarUsuario(u);
        Usuario encontrado = repositorio.encontrarUsuarioPorNombre(nombre);
        assertNotNull(encontrado);
        assertEquals(nombre, encontrado.getNombre());
        assertEquals(correo, encontrado.getDatosPerfil().getCorreo());
    }

    static List<Arguments> usuariosValidos() {
        return List.of(
            Arguments.of("Jorge", "1234", "jorge@correo.com", "Alfonso"),
            Arguments.of("Alfonso", "abcd", "alfonso@correo.com", "Alejandro"),
            Arguments.of("Alejandro", "clave", "alejandro@correo.com", "Jorge")
        );
    }

    @Test
    void testeliminarUsuario() {
        Usuario u = new Usuario("Jorge", "1234", "jorge@correo.com", null, "Alfonso");
        repositorio.agregarUsuario(u);
        assertNotNull(repositorio.encontrarUsuarioPorNombre("Jorge"));
        repositorio.eliminarUsuario(u);
        assertNull(repositorio.encontrarUsuarioPorNombre("Jorge"));
    }

    @Test
    void testUpdateUsuario() {
        Usuario u = new Usuario("Alfonso", "abc", "alfonso@correo.com", null, "Hola");
        repositorio.agregarUsuario(u);
        u.setSaludo("Nuevo saludo");
        repositorio.actualizarUsuario(u);
        Usuario actualizado = repositorio.encontrarUsuarioPorNombre("Alfonso");
        assertEquals("Nuevo saludo", actualizado.getSaludo());
    }

    @Test
    void testAddAndFindProfesor() {
        Profesor p = new Profesor("Alejandro", "clave", "alejandro@correo.com", null, "Saludos");
        repositorio.agregarUsuario(p);
        Usuario encontrado = repositorio.encontrarUsuarioPorNombre("Alejandro");
        assertNotNull(encontrado);
        assertTrue(encontrado instanceof Profesor);
        assertEquals("Alejandro", encontrado.getNombre());
        assertEquals("Saludos", encontrado.getSaludo());
    }

    @Test
    void testPerfilProfesorContieneRolCorrecto() {
        Profesor p = new Profesor("Jorge", "prof123", "jorge@correo.com", null, "Buen día");
        repositorio.agregarUsuario(p);
        Usuario encontrado = repositorio.encontrarUsuarioPorNombre("Jorge");
        infoPerfilUsuario perfil = encontrado.getDatosPerfil();
        assertEquals("Profesor", perfil.getRol());
    }

    @Test
    void testActualizarProfesorSaludo() {
        Profesor p = new Profesor("Alfonso", "abc", "alfonso@correo.com", null, "Hola");
        repositorio.agregarUsuario(p);
        p.setSaludo("Saludo actualizado");
        repositorio.actualizarUsuario(p);
        Usuario actualizado = repositorio.encontrarUsuarioPorNombre("Alfonso");
        assertEquals("Saludo actualizado", actualizado.getSaludo());
    }
}
