package repositorios;

import jakarta.persistence.*;
import modelo.Usuario;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Isolated;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Isolated
class RepositorioUsuariosTest {


    @BeforeAll
    static void setUpRepository() {
        RepositorioUsuarios.INSTANCE.init(Persistence.createEntityManagerFactory("usuarios"));
    }

    @BeforeEach
    void setUp() {
        RepositorioUsuarios repositorio = RepositorioUsuarios.INSTANCE;
        for(Usuario u : repositorio.obtenerTodosLosUsuarios()) {
        	repositorio.eliminarUsuario(u);
        }
    }

    @AfterAll
    static void cerrarFactory() {
        RepositorioUsuarios.INSTANCE.cerrar();
    }

    @Test
    void testAgregarYBuscarUsuario() {
        Usuario usuario = new Usuario("Juan", "a", "juan@example.com", "a", "a");
        RepositorioUsuarios.INSTANCE.agregarUsuario(usuario);

        Usuario encontrado = RepositorioUsuarios.INSTANCE.encontrarUsuarioPorNombre("Juan");

        assertNotNull(encontrado);
        assertEquals("juan@example.com", encontrado.getCorreo());
    }

    @Test
    void testBuscarPorCorreo() {
        Usuario usuario = new Usuario("Ana", "a", "ana@example.com", "a", "a");

        RepositorioUsuarios.INSTANCE.agregarUsuario(usuario);

        Usuario encontrado = RepositorioUsuarios.INSTANCE.encontrarUsuarioPorCorreo("ana@example.com");

        assertNotNull(encontrado);
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void testObtenerTodos() {
        RepositorioUsuarios.INSTANCE.agregarUsuario(new Usuario("Uno", "uno", "a@gmail.com", "a", "a"));
        RepositorioUsuarios.INSTANCE.agregarUsuario(new Usuario("Dos", "dos", "b@gmail.com", "a", "a"));

        List<Usuario> usuarios = RepositorioUsuarios.INSTANCE.obtenerTodosLosUsuarios();

        assertEquals(2, usuarios.size());
    }

    @Test
    void testActualizarUsuario() {
        Usuario usuario = new Usuario("Carlos", "carlos@test.com", "a", "a", "a");
        RepositorioUsuarios.INSTANCE.agregarUsuario(usuario);
        
        usuario.setImagen("nueva");
        RepositorioUsuarios.INSTANCE.actualizarUsuario(usuario);
        
        Usuario actualizado = RepositorioUsuarios.INSTANCE.encontrarUsuarioPorNombre("Carlos");
        assertEquals("nueva", actualizado.getImagen());
    }

    @Test
    void testEliminarUsuario() {
        Usuario usuario = new Usuario("Borrar", "borrar@test.com", "a", "a", "a");
        RepositorioUsuarios.INSTANCE.agregarUsuario(usuario);

        RepositorioUsuarios.INSTANCE.eliminarUsuario(usuario);

        Usuario encontrado = RepositorioUsuarios.INSTANCE.encontrarUsuarioPorNombre("Borrar");
        assertNull(encontrado);
    }

    @Test
    void testBuscarUsuarioInexistenteDevuelveNull() {
        Usuario noExiste = RepositorioUsuarios.INSTANCE.encontrarUsuarioPorNombre("no_existe");
        assertNull(noExiste);

        Usuario noExisteCorreo = RepositorioUsuarios.INSTANCE.encontrarUsuarioPorCorreo("no@existe.com");
        assertNull(noExisteCorreo);
    }

}