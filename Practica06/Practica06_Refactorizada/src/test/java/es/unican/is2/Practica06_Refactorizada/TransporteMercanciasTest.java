package es.unican.is2.Practica06_Refactorizada;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransporteMercanciasTest {

	@Test
	public void testConstructor() {
		TransporteMercancias sut = new TransporteMercancias(10, 100);
		assertEquals(10, sut.getHoras());
		assertEquals(100, sut.getToneladas());
		assertEquals(CategoriaTransporte.Mercancias, sut.getCategoria());

		assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 100));
		assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
	}
}