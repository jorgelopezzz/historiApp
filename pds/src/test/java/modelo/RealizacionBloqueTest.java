package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import modelo.BloqueContenidos;
import modelo.PreguntaVF;
import modelo.RealizacionBloque;
import modelo.RealizacionCurso;
import modelo.Tip;

class RealizacionBloqueTest {

	private static RealizacionCurso rc = new RealizacionCurso();

	private static BloqueContenidos bloqueValido = new BloqueContenidos(
		"Título", 
		"Descripción", 
		null, 
		List.of(new Tip("Enunciado", null))
	);

	static List<Arguments> argumentosInvalidos() {
		return List.of(
			Arguments.of(null, 1, rc)
		);
	}

	static List<Arguments> argumentosInvalidosNota() {
		return List.of(
			Arguments.of(bloqueValido, -1, rc),
			Arguments.of(bloqueValido, 1000, rc)
		);
	}

	static List<Arguments> argumentosValidosNota() {
		return List.of(
			Arguments.of(bloqueValido, 0, rc),
			Arguments.of(bloqueValido, 5, rc),
			Arguments.of(bloqueValido, 10, rc)
		);
	}

	static List<Arguments> argumentosValidos() {
		return List.of(
			Arguments.of(bloqueValido, 3, rc),
			Arguments.of(bloqueValido, 0, rc),
			Arguments.of(bloqueValido, 10, rc)
		);
	}

	@ParameterizedTest
	@MethodSource("argumentosInvalidos")
	void testArgumentosInvalidos(BloqueContenidos bloque, int puntuacion, RealizacionCurso realizacionCurso) {
		assertThrows(IllegalArgumentException.class, () -> {
			new RealizacionBloque(realizacionCurso, bloque, puntuacion);
		});
	}

	@ParameterizedTest
	@MethodSource("argumentosValidos")
	void testArgumentosValidos(BloqueContenidos bloque, int puntuacion, RealizacionCurso realizacionCurso) {
		RealizacionBloque rb = new RealizacionBloque(realizacionCurso, bloque, puntuacion);
		assertNotNull(rb);
		assertEquals(bloque, rb.getBloque());
		assertEquals(puntuacion, rb.getPuntuacion());
	}

	@ParameterizedTest
	@MethodSource("argumentosInvalidosNota")
	void testPuntuacionesIncorrectas(BloqueContenidos bloque, int puntuacion, RealizacionCurso realizacionCurso) {
		assertThrows(IllegalArgumentException.class, () -> {
			new RealizacionBloque(realizacionCurso, bloque, puntuacion);
		});
	}

	@ParameterizedTest
	@MethodSource("argumentosValidosNota")
	void testPuntuacionesCorrectas(BloqueContenidos bloque, int puntuacion, RealizacionCurso realizacionCurso) {
		assertDoesNotThrow(() -> {
			new RealizacionBloque(realizacionCurso, bloque, puntuacion);
		});
	}

	@Test
	void testFechaCompletadoEsMismoDiaActual() {
		int puntuacion = 8;
		RealizacionBloque realizacion = new RealizacionBloque(rc, bloqueValido, puntuacion);
		LocalDateTime fechaCompletado = realizacion.getFechaCompletado();
		LocalDateTime ahora = LocalDateTime.now();

		assertEquals(ahora.getYear(), fechaCompletado.getYear());
		assertEquals(ahora.getMonth(), fechaCompletado.getMonth());
		assertEquals(ahora.getDayOfMonth(), fechaCompletado.getDayOfMonth());
	}
}
