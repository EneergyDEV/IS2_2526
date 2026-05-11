package es.unican.is2.Practica06_Refactorizada;
/**
 * Clase base abstracta para todos los tipos de transporte.
 * Cada subclase concreta representa una categoría específica.
 */
public abstract class Transporte {

	private double horas;

	public Transporte(double horas) throws IllegalArgumentException { // WMC + 1
		if (horas <= 0) { // WMC + 1 (if)
			throw new IllegalArgumentException();
		}
		this.horas = horas;
	}
	// WMC + 1
	public double getHoras() {
		return horas;
	}
	// WMC + 1
	public abstract CategoriaTransporte getCategoria();
	// WMC + 1
	public abstract double getSueldoExtra();
}