import java.util.ArrayList;

public class Grafo {
	private ArrayList<Aeropuerto> aeropuertos;
	private ArrayList<Reserva> reservas;

	public Grafo() {
		aeropuertos = new ArrayList<Aeropuerto>();
	}
	
	public void mostrarAeropuertos() {
		for(Aeropuerto a: aeropuertos) {
			System.out.println(a.getNombre());
		}
	}
	
	public void mostrarReservas() {
		for(Reserva a: reservas) {
			System.out.println(a.getAerolinea()+ " : " + a.getReservados());
		}
	}

	public void addAeropuerto(Aeropuerto a) {
		aeropuertos.add(a);
	}

	

	public void addReserva(Reserva r) {
		reservas.add(r);
	}

	public ArrayList<Reserva> getReservas() {
		return this.reservas;
	}

	public Aeropuerto getAeropuerto(String nombre) {
		for (int i = 0; i < aeropuertos.size(); i++) {
			if (aeropuertos.get(i).getNombre() == nombre) {
				return aeropuertos.get(i);
			}
		}
		return null;
	}

	public Reserva getReserva(String aerolinea, Aeropuerto origen, Aeropuerto destino) {
		for (int i = 0; i < reservas.size(); i++) {
			Reserva r = reservas.get(i);
			if ((r.getAerolinea() == aerolinea) && (r.getOrigen() == origen) && (r.getDestino() == destino)) {
				return r;
			}

		}
		return null;

	}

	public Ruta existeDirecto(Aeropuerto origen, Aeropuerto destino) {
		
		if (origen.hasVecino(destino) != null) {
			return origen.hasVecino(destino);
			
		} else
			return null;

	}

	public int existeAerolinea(Ruta ruta, String aerolinea) {
		return ruta.existeAerolinea(aerolinea);

	}

	public void serivicio1(Aeropuerto o, Aeropuerto d, String aerolinea) {
		Ruta ruta = existeDirecto(o, d);
		if (ruta != null) {
			float kilometros = ruta.getKm();
			int asientosDisponibles = existeAerolinea(ruta, aerolinea);
			if (asientosDisponibles > 0) {
				System.out.println("Existe vuelo directo entre " + o + " y " + d + "de " + kilometros
						+ " kilometros, con " + asientosDisponibles + " asientos disponibles");
			} else if (asientosDisponibles == 0) {
				System.out.println("No hay asientos disponibles en dicha aerolinea para este vuelo directo");
			} else {
				System.out.println("No existe dicha aerolinea en este vuelo directo");
			}
		} else {
			System.out.println("No existe vuelo directo");
		}
	}

// siguiente funcion busca todos los vuelos entre dos aeropuertos sin utilizar una aerolinea determinada	

	public void servicio2prueba(Aeropuerto origen, Aeropuerto destino, String aerolinea) {
		ArrayList<Ruta> rutasDisponibles = new ArrayList<Ruta>();
		
		servicio2caminos(origen, destino, aerolinea, rutasDisponibles);
	}

	public void servicio2caminos(Aeropuerto origen, Aeropuerto destino, String aerolinea,
			ArrayList<Ruta> rutasDisponibles) {
		for (Ruta r : origen.getVecinos()) {
			if (r.haySinAerolinea(aerolinea)) {

				if (r.getDestino().equals(destino)) {
					rutasDisponibles.add(r);
					mostrarVuelos(rutasDisponibles, aerolinea); // modificarlo al directo ya que puede que haya vuelos con escala
				} else if (r.getDestino() != origen) {
					rutasDisponibles.add(r);
					servicio2caminos(r.getDestino(), destino, aerolinea, rutasDisponibles);
				}

			}
		}
		
	}
	public void mostrarVuelos(ArrayList<Ruta> rutasDisponibles, String aerolinea) {
		int escalas = 0;
		int kilometros = 0;
		for(Ruta r: rutasDisponibles) {
			escalas= escalas++;
			kilometros += r.getKm();
			System.out.println("Las aerolineas disponibles para ir desde " + r.getOrigen() + " hasta " + r.getDestino() + " son:");
			for(String a: r.disponibleSinAerolinea(aerolinea)) {
				System.out.println(a);
			}
		}
		System.out.println("El numero de escalas es : " + escalas);
		System.out.println("La cantidad de kilometros a recorrer es: " + kilometros);
		
	}

	public void VuelosDisponibles(Aeropuerto origen, Aeropuerto destino, String aerolinea) {
		ArrayList<Aeropuerto> caminosDisponibles = new ArrayList<Aeropuerto>();
		ArrayList<String> aerolineasDisponibles = new ArrayList<String>();
		int kilometros = 0;
		for (Aeropuerto a : aeropuertos) {
			a.setColor("amarillo");
		}
		datosCaminos(origen, destino, aerolinea, caminosDisponibles, aerolineasDisponibles, kilometros);

	}

	public int datosCaminos(Aeropuerto origen, Aeropuerto destino, String aerolinea,
			ArrayList<Aeropuerto> caminosDisponibles, ArrayList<String> aerolineasDisponibles, int kilometros) {

		for (Ruta r : origen.getVecinos()) {
			if (r.haySinAerolinea(aerolinea)) {
				caminosDisponibles.add(origen);
				aerolineasDisponibles.addAll(r.disponibleSinAerolinea(aerolinea));
				kilometros += r.getKm();
			}
			if (r.getDestino().equals(destino)) {
				caminosDisponibles.add(destino);
				int escalas = caminosDisponibles.size() - 1;
				mostrarVuelos(escalas, aerolineasDisponibles, kilometros);
				caminosDisponibles.remove(r.getDestino());
			} else if (r.getDestino().getColor().equals("amarillo")) {
				r.getDestino().setColor("negro");
				kilometros = datosCaminos(r.getDestino(), destino, aerolinea, caminosDisponibles, aerolineasDisponibles,
						kilometros);
				r.getDestino().setColor("amarillo");
			}
			aerolineasDisponibles.remove(r.disponibleSinAerolinea(aerolinea));
			caminosDisponibles.remove(r.getDestino());
			kilometros -= r.getKm();
		}
		return kilometros;
	}

	public void mostrarVuelos(int escalas, ArrayList<String> aerolineasDisponibles, int kilometros) {
		System.out.println();
		System.out.println("La cantidad de escalas a realizar es de " + escalas);
		System.out.println("Las aerolienas disponibles son:");
		for (String a : aerolineasDisponibles) {
			System.out.println(a);
		}
		System.out.println("Los kilometros a recorrer son: " + kilometros);
	}

	public void servicio3(String paisO, String paisD) {
		ArrayList<Aeropuerto> aeropuertosO = new ArrayList<Aeropuerto>();
		ArrayList<Aeropuerto> aeropuertosD = new ArrayList<Aeropuerto>();
		ArrayList<Ruta> rutasDisponibles = new ArrayList<Ruta>();
		for (Aeropuerto a : aeropuertos) {
			if (a.getPais().equals(paisO))
				aeropuertosO.add(a);
			else if (a.getPais().equals(paisD))
				aeropuertosD.add(a);

		}
		for (Aeropuerto a : aeropuertosO) {
			for (Aeropuerto b : aeropuertosD) {
				Ruta ruta = existeDirecto(a, b);
				if ((ruta != null) && (!ruta.asientosDisponibles().isEmpty())) {

					rutasDisponibles.add(ruta);
				}
			}
		}
		mostrarVuelos(rutasDisponibles);
	}

	public void mostrarVuelos(ArrayList<Ruta> rutas) {
		for (Ruta r : rutas) {
			System.out.println("Vuelo disponible encontrado");
			System.out.println("Desde " + r.getOrigen() + " hacia " + r.getDestino());
			System.out.println("Los kilometros a recorrer son: " + r.getKm());
			System.out.println("Las aerolineas disponibles para este vuelo son:");
			for (String aerolineas : r.asientosDisponibles()) {
				System.out.println(aerolineas);
			}
		}
	}

}
