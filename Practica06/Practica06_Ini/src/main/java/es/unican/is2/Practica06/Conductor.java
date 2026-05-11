package es.unican.is2.Practica06;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}
	// WMC: +1 (método base) +1 (if) +3 (3 boolean en el if) = 5
	// CCOg: +1 (if) +1 (secuencia de ||) = 2

	public String dni() {
		return dni;
	}
	// WMC: +1 (metodo base)

	public String getDni() {
		return dni;
	}
	// WMC: +1 (metodo base)

	public String getNombre() {
		return nombre;
	}
	// WMC: +1 (metodo base)

	public String getApellido1() {
		return apellido1;
	}
	// WMC: +1 (metodo base)

	public String apellido2() {
		return apellido2;
	}
	// WMC: +1 (metodo base)

	public String getDire() {
		return dire;
	}
	// WMC: +1 (metodo base)

	public double sueldo() {
		double sueldoTransportes = 0;
		for (Transporte t : transportes) {
			double sueldoExtraTransporte = 0.0;
			switch (t.categoria()) {
				case Mercancias:
					sueldoExtraTransporte = t.ton() * 2;
					break;
				case MercanciasPeligrosas:
					sueldoExtraTransporte = t.ton() * 2 + 50;
					break;
				case Personas:
					if (t.getPersonas() < 10)
						sueldoExtraTransporte = t.horas() * 0.5;
					else
						sueldoExtraTransporte = t.horas();
					break;
			}
			sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
		}
		return 700 + sueldoTransportes;
	}
	// WMC: +1 (metodo base) +1 (for) +3 (3 case) +1 (if) = 6
	// CCog: +1 (for) +1 (switch) +1 (anidación) +1 (if) +2 (anidación) +1 (else) = 7

	public void anhadeTransporte(Transporte t) {
		transportes.add(t);
	}
	// WMC: +1 (metodo base)

}
