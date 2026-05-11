package es.unican.is2.Practica06_Refactorizada;
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
	private static final double SALARIO_BASE = 700;
	private static final double HORAS = 5;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) { //wmc 1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { //wmc +1 (if) + 3( 3 barras) ----ccog +1(if) + 1(secuencia barra)
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2; 
		this.dire = direccion;
	}


	//+5 wmc uno por getter
	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getDireccion() {
		return dire;
	}

	public double sueldo() { //WMC +1
		double sueldoTransportes = 0.0;
		for (Transporte t : transportes) { //WMC +1 CCog +1
			sueldoTransportes += t.getHoras() * HORAS + t.getSueldoExtra();
		}
		return SALARIO_BASE + sueldoTransportes;
	}
	//wmc +1
	public void anhadeTransporte(Transporte t) {
		transportes.add(t);
	}

}
