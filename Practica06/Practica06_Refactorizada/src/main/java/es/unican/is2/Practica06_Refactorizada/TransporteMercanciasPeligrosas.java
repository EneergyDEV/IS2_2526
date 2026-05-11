package es.unican.is2.Practica06_Refactorizada;
public class TransporteMercanciasPeligrosas extends TransporteMercancias {

	private static final double TONELADAS = 2;
	private static final double BONUS = 50;

	//WMC +1
	public TransporteMercanciasPeligrosas(double horas, int toneladas) throws IllegalArgumentException {
		super(horas, toneladas);
	}

	//WMC +1
	@Override
	public CategoriaTransporte getCategoria() {
		return CategoriaTransporte.MercanciasPeligrosas;
	}
	//WMC +1
	@Override
	public double getSueldoExtra() {
		return getToneladas() * TONELADAS + BONUS;
	}
}