package dominio.curso;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CertificadoBloqueTest {

    // Método para proporcionar los parámetros de prueba para los diferentes casos
    private static List<Arguments> parametrosParaConstructor() {
        return List.of(
            // Casos válidos
            Arguments.of(LocalDate.of(2025, 03, 29), "usuario1", new BloqueContenidos("Título", "Descripción", "ruta/imagen.png", null, null), null),
            // Casos con valores nulos
            Arguments.of(null, "usuario1", new BloqueContenidos("Título", "Descripción", "ruta/imagen.png", null, null), "Los atributos no pueden ser nulos"),
            Arguments.of(LocalDate.of(2025, 03, 29), null, new BloqueContenidos("Título", "Descripción", "ruta/imagen.png", null, null), "Los atributos no pueden ser nulos"),
            Arguments.of(LocalDate.of(2025, 03, 29), "usuario1", null, "Los atributos no pueden ser nulos")
        );
    }

    // Test parametrizado para verificar el comportamiento del constructor y los mensajes de error
    @ParameterizedTest
    @MethodSource("parametrosParaConstructor")
    void testConstructor(LocalDate fechaCert, String usuario, BloqueContenidos bloque, String mensajeEsperado) {
        if (mensajeEsperado != null) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new CertificadoBloque(fechaCert, usuario, bloque);
            });
            assertEquals(mensajeEsperado, exception.getMessage());
        } else {
            CertificadoBloque certificado = new CertificadoBloque(fechaCert, usuario, bloque);
            assertEquals(fechaCert, certificado.getFechaCert());
            assertEquals(usuario, certificado.getUsuario());
            assertEquals(bloque, certificado.getBloque());
        }
    }

    // Test para los getters usando test parametrizados
    @ParameterizedTest
    @MethodSource("parametrosParaConstructor")
    void testGetters(LocalDate fechaCert, String usuario, BloqueContenidos bloque, String mensajeEsperado) {
        if (mensajeEsperado == null) {
            CertificadoBloque certificado = new CertificadoBloque(fechaCert, usuario, bloque);

            assertEquals(fechaCert, certificado.getFechaCert());
            assertEquals(usuario, certificado.getUsuario());
            assertEquals(bloque, certificado.getBloque());
        }
    }
}
