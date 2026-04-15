package es.unican.is2.SegurosCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SeguroTest {

    private Seguro seguro;
    private LocalDate hoy;

    @BeforeEach
    public void setUp() {
        seguro = new Seguro();
        hoy = LocalDate.now();
    }

    @Test
    public void testPrecioValoresLimite() {
        // CASO 1: Fecha futura (mañana). Retorna 0.
        seguro.setFechaInicio(hoy.plusDays(1));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(100);
        assertEquals(0.0, seguro.precio(), 0.01);

        // CASO 2: Todo Riesgo (1000), Potencia límite < 90 (89), Fecha límite inferior (Hoy -> -20%)
        // Cálculo: 1000 * 1 * 0.8 = 800
        seguro.setFechaInicio(hoy);
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(89);
        assertEquals(800.0, seguro.precio(), 0.01);

        // CASO 3: Terceros Lunas (600), Potencia límite inferior rango [90, 110] (90), Fecha nominal < 1 año (-20%)
        // Cálculo: 600 * 1.05 * 0.8 = 504
        seguro.setFechaInicio(hoy.minusMonths(6));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(90);
        assertEquals(504.0, seguro.precio(), 0.01);

        // CASO 4: Terceros (400), Potencia límite superior rango [90, 110] (110), Fecha límite superior < 1 año (hace 1 año menos 1 día -> -20%)
        // Cálculo: 400 * 1.05 * 0.8 = 336
        seguro.setFechaInicio(hoy.minusYears(1).plusDays(1));
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(110);
        assertEquals(336.0, seguro.precio(), 0.01);

        // CASO 5: Terceros Lunas (600), Potencia límite inferior > 110 (111), Fecha límite inferior >= 1 año (exactamente 1 año -> sin descuento)
        // Cálculo: 600 * 1.2 = 720
        seguro.setFechaInicio(hoy.minusYears(1));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(111);
        assertEquals(720.0, seguro.precio(), 0.01);

        // CASO 6: Todo Riesgo (1000), Potencia alta nominal (150), Fecha > 1 año (sin descuento)
        // Cálculo: 1000 * 1.2 = 1200
        seguro.setFechaInicio(hoy.minusYears(2));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(150);
        assertEquals(1200.0, seguro.precio(), 0.01);
        
     // CASO 7: Cobertura nula (solo precio base 0)
        seguro.setFechaInicio(hoy);
        seguro.setCobertura(null);
        seguro.setPotencia(100);
        assertEquals(0.0, seguro.precio(), 0.01);
    }
}