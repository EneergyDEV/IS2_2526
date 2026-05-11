package es.unican.is2.Practica06_Refactorizada;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransportePersonasTest {

	@Test
	public void testConstructor() {
		// Caso valido
		TransportePersonas sut = new TransportePersonas(10, 5);
		assertEquals(10, sut.getHoras());
		assertEquals(5, sut.getPersonas());
		assertEquals(CategoriaTransporte.Personas, sut.getCategoria());

		// Casos no validos
		assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(0, 5));
		assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(10, 0));
	}
}