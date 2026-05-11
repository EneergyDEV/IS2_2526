package es.unican.is2.Practica06;
import java.util.LinkedList;
import java.util.List;
import fundamentos.*;

/**
 * Gestion de una empresa de transportes
 */
public class GestionTransportesGUI {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1, 
		SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

		// variables auxiliares
		String dni;
		Lectura lect;
		Conductor c;

		// crea la empresa de transportes
		gestionTransportes gt = new gestionTransportes();
		// crea la ventana de menu
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);
		
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) {
			// WMC: +1 (while)
			// CCog: +1 (while)
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			// CCog: +1 (switch) +1 (anidación) = 2
			case  ANHADE_CONDUCTOR:
			// WMC: +1 (case)
				lect = new Lectura("Datos Conductor");
				lect.creaEntrada("DNI", "");
				lect.creaEntrada("Nombre","");
				lect.creaEntrada("Apellido1", "");
				lect.creaEntrada("Apellido2", "");
				lect.creaEntrada("Direccion", "");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				String nombre = lect.leeString("Nombre");
				String apellido1 = lect.leeString("Apellido1");
				String apellido2 = lect.leeString("Apellido2");
				String direccion = lect.leeString("Direccion");
				// Anhade el conductor
				if (!gt.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) 
				// WMC: +1 (if)
				// CCog: +1 (if) +2 (anidación) = 3
					mensaje("ERROR", "Ya existe un conductor con DNI "+dni);
				break;

			case ANHADE_TRANSPORTE:
			// WMC: +1 (case)
				lect = new Lectura("Nuevo transporte");
				lect.creaEntrada("DNI", "");
				lect.creaEntrada("Tipo Transporte: P | M | MP", "");
				lect.creaEntrada("Horas", 0);
				lect.creaEntrada("Personas", 0);
				lect.creaEntrada("Toneladas", 0);
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				String tipo = lect.leeString("Tipo Transporte: P | M | MP");
				int horas = lect.leeInt("Horas");
				int personas = lect.leeInt("Personas");
				int toneladas = lect.leeInt("Toneladas");

				Transporte t = null;
				c = gt.buscaConductor(dni);
				if (c!=null) {
				// WMC: +1 (if)
				// CCog: +1 (if) +2 (anidación) = 3
					switch (tipo) {
					// CCog: +1 (switch) +3 (anidación) = 4
						case "P":
						// WMC: +1 (case)
							t = new Transporte(horas,CategoriaTransporte.Personas, personas);
							c.anhadeTransporte(t);
							break;
						case "M":
						// WMC: +1 (case)
							t = new Transporte(horas, CategoriaTransporte.Mercancias, toneladas);
							c.anhadeTransporte(t);
							break;
						case "MP":
						// WMC: +1 (case)
							t = new Transporte(horas, CategoriaTransporte.MercanciasPeligrosas, toneladas);
							c.anhadeTransporte(t);
							break;		
					}
				} else {
					// CCog: +1 (else)
					mensaje("ERROR", "No existe un conductor con DNI "+dni);
				}
				break;
				
			case SUELDO_CONDUCTOR:
			// WMC: +1 (case)
				lect = new Lectura("Transportes Peligrosos");
				lect.creaEntrada("DNI", "");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				c = gt.buscaConductor(dni);
				if (c!=null){
				// WMC: +1 (if)
				// CCog: +1 (if) +2 (anidación) = 3
					mensaje("Sueldo", "El sueldo del conductor es: "+c.sueldo());
				} else {
					// CCog: +1 (else)
					mensaje("ERROR", "No existe un conductor con DNI "+dni);
				}
 				break;

			case MEJOR_CONDUCTOR:
			// WMC: +1 (case)
				List<Conductor> resultado = new LinkedList<Conductor>();
				double maxSueldo = 0.0;
				for (Conductor conductor : gt.conductores()) {
				// WMC: +1 (for)
				// CCog: +1 (for) +2 (anidación) = 3
					if (conductor.sueldo() > maxSueldo) {
					// WMC: +1 (if)
					// CCog: +1 (if) +3 (anidación) = 4
						maxSueldo = conductor.sueldo();
						resultado.clear();
						resultado.add(conductor);
					} else if (conductor.sueldo() == maxSueldo) {
						// WMC: +1 (else if)
						// CCog: +1 (else if)
						resultado.add(conductor);
					}
				}		
				String msj = "";
				if (resultado.size() == 0) {
				// WMC: +1 (if)
				// CCog: +1 (if) +2 (anidación) = 3
					msj = "No hay conductores";
				} else {
					// CCog: +1 (else)
					for (Conductor conductor : resultado) {
					// WMC: +1 (for)
					// CCog: +1 (for) +2 (anidación) = 3
						msj += conductor.getNombre() + " "+conductor.getNombre()+"\n";
					}
				}
				mensaje("MEJOR CONDUCTOR", msj);
				break;
			}
		}
	}
	// WMC: +1 (metodo base)

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
	// WMC: +1 (metodo base)

}
