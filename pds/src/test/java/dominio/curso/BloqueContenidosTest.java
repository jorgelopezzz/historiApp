package dominio.curso;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.tarea.Tarea;
import dominio.tarea.Tip;

public class BloqueContenidosTest {

    // Prueba para el constructor cuando el título es null
    @Test
    void testConstructorTituloNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BloqueContenidos(null, "descripcion", "ruta", List.of());
        });
        assertEquals("El título no puede ser nulo", exception.getMessage());
    }

    // Prueba de los getters
    @Test
    void testGetters() {
        BloqueContenidos bloque = new BloqueContenidos("Título", "Descripción", "ruta/imagen.png", List.of(new Tip("Tarea 1", "ruta1")));

        assertEquals("Título", bloque.getTitulo());
        assertEquals("Descripción", bloque.getDescripcion());
        assertEquals("ruta/imagen.png", bloque.getRutaImagen());
        assertNull(bloque.getCertBloque());
        assertNotNull(bloque.getTareas());
        assertFalse(bloque.getTareas().isEmpty());
    }
    
}
