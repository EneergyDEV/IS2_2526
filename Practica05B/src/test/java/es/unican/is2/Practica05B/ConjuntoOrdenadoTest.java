package es.unican.is2.Practica05B;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {

	private ConjuntoOrdenado<Integer> coleccion;

	@BeforeEach
	public void inicializar() {
		coleccion = new ConjuntoOrdenado<Integer>();
	}

	@Test
	public void comprobarAddCasosValidos() {
		assertTrue(coleccion.add(5));
		assertEquals(1, coleccion.size());
		assertEquals(5, coleccion.get(0));
		coleccion.add(10);
		assertTrue(coleccion.add(8));
		assertEquals(3, coleccion.size());
		assertEquals(5, coleccion.get(0));

		assertEquals(8, coleccion.get(1));
		assertEquals(10, coleccion.get(2));
		assertFalse(coleccion.add(5));
		assertEquals(3, coleccion.size());
	}

	@Test
	public void comprobarAddExcepciones() {
		assertThrows(NullPointerException.class, () -> coleccion.add(null));
		coleccion.add(5);
		coleccion.add(8);

		assertThrows(NullPointerException.class, () -> coleccion.add(null));
	}

	@Test
	public void validarGetFuncionamientoNormal() {
		coleccion.add(5);

		coleccion.add(8);
		coleccion.add(10);
		assertEquals(5, coleccion.get(0));
		assertEquals(10, coleccion.get(2));
	}

	@Test
	public void validarGetFuerzaExcepciones() {
		assertThrows(IndexOutOfBoundsException.class, () -> coleccion.get(0));
		coleccion.add(5);
		coleccion.add(8);
		coleccion.add(10);
		assertThrows(IndexOutOfBoundsException.class, () -> coleccion.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> coleccion.get(3));
	}

	@Test
	public void verificarRemoveDentroDeLimites() {
		coleccion.add(5);
		coleccion.add(8);
		coleccion.add(10);
		assertEquals(5, coleccion.remove(0));
		assertEquals(2, coleccion.size());
		assertEquals(8, coleccion.get(0));

		inicializar();

		coleccion.add(5);
		coleccion.add(8);
		coleccion.add(10);
		assertEquals(10, coleccion.remove(2));
		assertEquals(2, coleccion.size());
		assertEquals(8, coleccion.get(1));

	}

	@Test
	public void verificarRemoveFueraDeLimites() {
		assertThrows(IndexOutOfBoundsException.class, () -> coleccion.remove(0));
		coleccion.add(5);
		coleccion.add(8);
		coleccion.add(10);
		assertThrows(IndexOutOfBoundsException.class, () -> coleccion.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> coleccion.remove(3));
	}

	@Test
	public void comprobarSize() {
		assertEquals(0, coleccion.size());
		coleccion.add(5);
		assertEquals(1, coleccion.size());
		coleccion.add(8);
		coleccion.add(10);

		assertEquals(3, coleccion.size());
	}

	@Test
	public void comprobarClear() {
		coleccion.clear();
		assertEquals(0, coleccion.size());
		coleccion.add(5);
		coleccion.add(8);
		coleccion.clear();
		assertEquals(0, coleccion.size());
	}
}