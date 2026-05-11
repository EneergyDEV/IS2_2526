package es.unican.is2.Practica06_Refactorizada;
public class TransporteMercancias extends Transporte {

	private int toneladas;
	private static final double TONELADAS = 2;

	//WMC +1
	public TransporteMercancias(double horas, int toneladas) throws IllegalArgumentException {
		super(horas);
		//WMC +1
		if (toneladas <= 0) {
			throw new IllegalArgumentException();
		}
		this.toneladas = toneladas;
	}

	//WMC +1
	public int getToneladas() {
		return toneladas;
	}

	//WMC +1
	@Override
	public CategoriaTransporte getCategoria() {
		return CategoriaTransporte.Mercancias;
	}
	//WMC +1
	@Override
	public double getSueldoExtra() {
		return getToneladas() * TONELADAS;
	}
}