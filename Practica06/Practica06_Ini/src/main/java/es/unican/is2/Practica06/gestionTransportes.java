package es.unican.is2.Practica06;
import java.util.ArrayList;
import java.util.List;

public class gestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) {		
		for(Conductor c: cs) 
			if (c.dni().equals(DNI))
				return c;
		
		return null;
	}
	// WMC: +1 (método base) +1 (for) +1 (if) = 3
	// CCog: +1 (for) +1 (if) +1 (anidación) = 3
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
		if (buscaConductor(dni) != null)
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}
	// WMC: +1 (metodo base) +1 (if) = 2
	// CCog: +1 (if)

	public List<Conductor> conductores() {
		return cs;
	}
	// WMC: +1 (metodo base)
	
}
