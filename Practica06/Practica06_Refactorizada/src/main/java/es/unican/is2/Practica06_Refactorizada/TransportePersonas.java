package es.unican.is2.Practica06_Refactorizada;
public class TransportePersonas extends Transporte {

	private int personas;
	private static final int BONUS = 10;
	private static final double HORAS = 0.5;

	// WMC +1
	public TransportePersonas(double horas, int personas) throws IllegalArgumentException {
		super(horas);
		if (personas <= 0) { // WMC +1 (if)
			throw new IllegalArgumentException();
		}
		this.personas = personas;
	}

	// WMC +1
	public int getPersonas() {
		return personas;
	}
	
	//WMC +1
	@Override
	public CategoriaTransporte getCategoria() {
		return CategoriaTransporte.Personas;
	}
	//WMC +1
	@Override
	public double getSueldoExtra() {
		if (getPersonas() < BONUS) //WMC +1 (if)
			return getHoras() * HORAS;
		else
			return getHoras();
	}
}