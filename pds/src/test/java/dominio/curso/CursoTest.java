package dominio.curso;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.BloqueContenidos;
import dominio.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoTest {

    private Curso curso;

    @BeforeEach
    public void setUp() {
        curso = new Curso("Curso de Java", "Descripción del curso", "ruta/imagen", List.of(new BloqueContenidos()));
    }

    @Test
    public void testGetTitulo() {
        assertEquals("Curso de Java", curso.getTitulo());
    }

    @Test
    public void testGetDescripcion() {
        assertEquals("Descripción del curso", curso.getDescripcion());
    }

    @Test
    public void testGetRutaImagen() {
        assertEquals("ruta/imagen", curso.getRutaImagen());
    }

    @Test
    public void testGetBloquesContenidos() {
        assertNotNull(curso.getBloquesContenidos());
        assertFalse(curso.getBloquesContenidos().isEmpty());
    }
}
