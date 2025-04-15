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
        repositorio.removeUsuario(new Usuario("Jorge", "1234", "jorge@correo.com", null, "Alfonso"));
        repositorio.removeUsuario(new Usuario("Alfonso", "abcd", "alfonso@correo.com", null, "Alejandro"));
        repositorio.removeUsuario(new Usuario("Alejandro", "clave", "alejandro@correo.com", null, "Jorge"));
    }

    @ParameterizedTest
    @MethodSource("usuariosValidos")
    void testAddAndFindUsuario(String nombre, String contrasena, String correo, String saludo) {
        Usuario u = new Usuario(nombre, contrasena, correo, null, saludo);
        repositorio.addUsuario(u);
        Usuario encontrado = repositorio.findUsuarioPorNombre(nombre);
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
    void testRemoveUsuario() {
        Usuario u = new Usuario("Jorge", "1234", "jorge@correo.com", null, "Alfonso");
        repositorio.addUsuario(u);
        assertNotNull(repositorio.findUsuarioPorNombre("Jorge"));
        repositorio.removeUsuario(u);
        assertNull(repositorio.findUsuarioPorNombre("Jorge"));
    }

    @Test
    void testUpdateUsuario() {
        Usuario u = new Usuario("Alfonso", "abc", "alfonso@correo.com", null, "Hola");
        repositorio.addUsuario(u);
        u.setSaludo("Nuevo saludo");
        repositorio.updateUsuario(u);
        Usuario actualizado = repositorio.findUsuarioPorNombre("Alfonso");
        assertEquals("Nuevo saludo", actualizado.getSaludo());
    }

    @Test
    void testAddAndFindProfesor() {
        Profesor p = new Profesor("Alejandro", "clave", "alejandro@correo.com", null, "Saludos");
        repositorio.addUsuario(p);
        Usuario encontrado = repositorio.findUsuarioPorNombre("Alejandro");
        assertNotNull(encontrado);
        assertTrue(encontrado instanceof Profesor);
        assertEquals("Alejandro", encontrado.getNombre());
        assertEquals("Saludos", encontrado.getSaludo());
    }

    @Test
    void testPerfilProfesorContieneRolCorrecto() {
        Profesor p = new Profesor("Jorge", "prof123", "jorge@correo.com", null, "Buen día");
        repositorio.addUsuario(p);
        Usuario encontrado = repositorio.findUsuarioPorNombre("Jorge");
        infoPerfilUsuario perfil = encontrado.getDatosPerfil();
        assertEquals("Profesor", perfil.getRol());
    }

    @Test
    void testActualizarProfesorSaludo() {
        Profesor p = new Profesor("Alfonso", "abc", "alfonso@correo.com", null, "Hola");
        repositorio.addUsuario(p);
        p.setSaludo("Saludo actualizado");
        repositorio.updateUsuario(p);
        Usuario actualizado = repositorio.findUsuarioPorNombre("Alfonso");
        assertEquals("Saludo actualizado", actualizado.getSaludo());
    }
}
