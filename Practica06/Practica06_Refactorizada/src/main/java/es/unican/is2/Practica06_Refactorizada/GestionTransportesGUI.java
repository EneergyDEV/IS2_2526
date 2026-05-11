package es.unican.is2.Practica06_Refactorizada;
import java.util.LinkedList;
import java.util.List;
import fundamentos.*;

public class GestionTransportesGUI {

	public static void main(String[] args) { //WMC +1
		final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1,
				SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

		GestionTransportes gt = new GestionTransportes();
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);

		int opcion;
		while (true) { //WMC +1 CCog +1
			opcion = menu.leeOpcion();
			switch (opcion) { //CCog +2
			//WMC +1
			case ANHADE_CONDUCTOR:  anhadeConductor(gt);  break;
			//WMC +1
			case ANHADE_TRANSPORTE: anhadeTransporte(gt); break;
			//WMC +1
			case SUELDO_CONDUCTOR:  sueldoConductor(gt);  break;
			//WMC +1
			case MEJOR_CONDUCTOR:   mejorConductor(gt);   break;
			}
		}
	}

	private static void anhadeConductor(GestionTransportes gt) { //WMC +1
		Lectura lect = new Lectura("Datos Conductor");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Nombre", "");
		lect.creaEntrada("Apellido1", "");
		lect.creaEntrada("Apellido2", "");
		lect.creaEntrada("Direccion", "");
		lect.esperaYCierra();
		String dni       = lect.leeString("DNI");
		String nombre    = lect.leeString("Nombre");
		String apellido1 = lect.leeString("Apellido1");
		String apellido2 = lect.leeString("Apellido2");
		String direccion = lect.leeString("Direccion");
		if (!gt.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) //WMC +1 CCog +1
			mensaje("ERROR", "Ya existe un conductor con DNI " + dni);
	}

	private static void anhadeTransporte(GestionTransportes gt) { //WMC +1
		Lectura lect = new Lectura("Nuevo transporte");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Tipo Transporte: P | M | MP", "");
		lect.creaEntrada("Horas", 0);
		lect.creaEntrada("Personas", 0);
		lect.creaEntrada("Toneladas", 0);
		lect.esperaYCierra();
		String dni      = lect.leeString("DNI");
		String tipo     = lect.leeString("Tipo Transporte: P | M | MP");
		int horas       = lect.leeInt("Horas");
		int personas    = lect.leeInt("Personas");
		int toneladas   = lect.leeInt("Toneladas");
		Transporte t = null;
		Conductor c = gt.buscaConductor(dni);
		if (c != null) { //WMC +1 CCog +1
			switch (tipo) { //CCog +2
			//WMC +1
			case "P":  t = new TransportePersonas(horas, personas);              c.anhadeTransporte(t); break;
			//WMC +1
			case "M":  t = new TransporteMercancias(horas, toneladas);           c.anhadeTransporte(t); break;
			//WMC +1
			case "MP": t = new TransporteMercanciasPeligrosas(horas, toneladas); c.anhadeTransporte(t); break;
			}
		} else { //CCog +1
			mensaje("ERROR", "No existe un conductor con DNI " + dni);
		}
	}

	private static void sueldoConductor(GestionTransportes gt) { //WMC +1
		Lectura lect = new Lectura("Sueldo Conductor");
		lect.creaEntrada("DNI", "");
		lect.esperaYCierra();
		String dni = lect.leeString("DNI");
		Conductor c = gt.buscaConductor(dni);
		if (c != null) { //WMC +1 CCog +1
			mensaje("Sueldo", "El sueldo del conductor es: " + c.sueldo());
		} else { //CCog +1
			mensaje("ERROR", "No existe un conductor con DNI " + dni);
		}
	}

	private static void mejorConductor(GestionTransportes gt) { //WMC +1
		List<Conductor> resultado = new LinkedList<Conductor>();
		double maxSueldo = 0.0;
		for (Conductor conductor : gt.conductores()) { //WMC +1 CCog +1
			if (conductor.sueldo() > maxSueldo) { //WMC +1 CCog +2
				maxSueldo = conductor.sueldo();	
				resultado.clear();
				resultado.add(conductor);
			} else if (conductor.sueldo() == maxSueldo) { //WMC +1 CCog +1
				resultado.add(conductor);
			}
		}
		String msj = "";
		if (resultado.size() == 0) { //WMC +1 CCog +1
			msj = "No hay conductores";
		} else { //CCog +1
			for (Conductor conductor : resultado) { //WMC +1 CCog +2
				msj += conductor.getNombre() + " " + conductor.getNombre() + "\n";
			}
		}
		mensaje("MEJOR CONDUCTOR", msj);
	}

	private static void mensaje(String titulo, String txt) { //WMC +1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}
}