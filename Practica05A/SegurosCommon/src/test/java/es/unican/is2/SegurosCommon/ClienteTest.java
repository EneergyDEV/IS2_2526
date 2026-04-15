package es.unican.is2.SegurosCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private Cliente cliente;
    private Seguro seguroBarato;
    private Seguro seguroCaro;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setDni("11111111A");
        cliente.setNombre("Prueba");

        // Seguro que sabemos que cuesta 800 (Todo riesgo, potencia 89, de hoy)
        seguroBarato = new Seguro();
        seguroBarato.setFechaInicio(LocalDate.now());
        seguroBarato.setCobertura(Cobertura.TODO_RIESGO);
        seguroBarato.setPotencia(89);

        // Seguro que sabemos que cuesta 1200 (Todo riesgo, potencia 150, de hace 2 años)
        seguroCaro = new Seguro();
        seguroCaro.setFechaInicio(LocalDate.now().minusYears(2));
        seguroCaro.setCobertura(Cobertura.TODO_RIESGO);
        seguroCaro.setPotencia(150);
    }

    @Test
    public void testTotalSegurosSinMinusvalia() {
        cliente.setMinusvalia(false);

        // CASO 1: Lista vacía
        assertEquals(0.0, cliente.totalSeguros(), 0.01);

        // CASO 2: Un solo seguro
        cliente.getSeguros().add(seguroBarato);
        assertEquals(800.0, cliente.totalSeguros(), 0.01);

        // CASO 3: Varios seguros
        cliente.getSeguros().add(seguroCaro);
        assertEquals(2000.0, cliente.totalSeguros(), 0.01); // 800 + 1200
    }

    @Test
    public void testTotalSegurosConMinusvalia() {
        cliente.setMinusvalia(true);

        // CASO 4: Lista vacía con minusvalía
        assertEquals(0.0, cliente.totalSeguros(), 0.01);

        // CASO 5: Un solo seguro con minusvalía (Aplica 25% descuento -> 800 * 0.75 = 600)
        cliente.getSeguros().add(seguroBarato);
        assertEquals(600.0, cliente.totalSeguros(), 0.01);

        // CASO 6: Varios seguros con minusvalía (Aplica 25% descuento al total -> 2000 * 0.75 = 1500)
        cliente.getSeguros().add(seguroCaro);
        assertEquals(1500.0, cliente.totalSeguros(), 0.01);
    }
}