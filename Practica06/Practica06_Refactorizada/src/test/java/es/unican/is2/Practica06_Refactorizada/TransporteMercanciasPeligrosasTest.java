package es.unican.is2.Practica06_Refactorizada;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransporteMercanciasPeligrosasTest {

	@Test
	public void testConstructor() {
		TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(10, 100);
		assertEquals(10, sut.getHoras());
		assertEquals(100, sut.getToneladas());
		assertEquals(CategoriaTransporte.MercanciasPeligrosas, sut.getCategoria());

		assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(0, 100));
		assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(10, 0));
	}
}