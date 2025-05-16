package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;


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
        assertNotNull(bloque.getTareas());
        assertFalse(bloque.getTareas().isEmpty());
    }
    
}
