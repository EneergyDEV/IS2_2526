package es.unican.is2.Practica06_Refactorizada;
import java.util.ArrayList;
import java.util.List;
//cbo 3
public class GestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();

	public Conductor buscaConductor(String DNI) {		//wmc 1
		for(Conductor c: cs)  //wmc 1 -- ccog 1
			if (c.getDni().equals(DNI)) //wmc 1 ccog 2
				return c;

		return null;
	}

	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) { //wmc 1
		if (buscaConductor(dni) != null) //wmc1 ccog 1 
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	public List<Conductor> conductores() { //wmc 1
		return cs;
	}

}
