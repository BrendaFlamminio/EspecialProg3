import java.util.ArrayList;

public class Grafo {
	private ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private ArrayList<ArrayList<Ruta>> posiblesCaminos = new ArrayList<ArrayList<Ruta>>();

	public Grafo() {
		aeropuertos = new ArrayList<Aeropuerto>();
	}

	public void mostrarAeropuertos() {
		for (Aeropuerto a : aeropuertos) {
			System.out.println(a.getNombre());
		}
	}

	public void mostrarReservas() {
		for (Reserva a : reservas) {
			System.out.println(a.getAerolinea() + " : " + a.getReservados());
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
		for (Aeropuerto a : aeropuertos) {
			if (a.getNombre().equals(nombre)) {
				return a;
			}
		}
		Aeropuerto ae = new Aeropuerto("Pepe", "pepito", "peputo");
		return ae;
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
				System.out.println("Existe vuelo directo entre " + o.getNombre() + " y " + d.getNombre() + " de "
						+ kilometros + " kilometros, con " + asientosDisponibles + " asientos disponibles");
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

	public void dfs(Aeropuerto origen, Aeropuerto destino, String aerolinea) {
		ArrayList<Ruta> rutaDisponible = new ArrayList<Ruta>();
		// int kilometros = 0;
		for (Aeropuerto a : aeropuertos) {
			a.setColor("blanco");
		}
		origen.setColor("amarillo");
		dfsVisit(origen, destino, rutaDisponible, aerolinea);
		imprimirCaminos(origen, destino);

	}

	public void imprimirCaminos(Aeropuerto o, Aeropuerto d) {
		System.out.println("Caminos disponibles");

		for (ArrayList<Ruta> ar : posiblesCaminos) {
			System.out.println(o.getNombre() + " - ");
			for (Ruta r : ar) {
				System.out.print(r.getOrigen().getNombre() + " - " + r.getDestino().getNombre());
				System.out.println(" ");
			}
			// System.out.println(d.getNombre()+ " - ");
			System.out.println(" OTRO CAMINO ");
		}
	}

	public void dfsVisit(Aeropuerto origen, Aeropuerto destino, ArrayList<Ruta> rutaDisponible, String aerolinea) {

		if (destino.equals(origen)) {
			posiblesCaminos.add((ArrayList<Ruta>) rutaDisponible.clone());
		} else {

			for (Ruta ru : origen.getVecinos()) {
				if (ru.getDestino().getColor() == "blanco") {
					if(ru.haySinAerolinea(aerolinea)){
					rutaDisponible.add(ru);
					ru.getDestino().setColor("amarillo");
					dfsVisit(ru.getDestino(), destino, rutaDisponible, aerolinea);
					ru.getDestino().setColor("blanco");
					rutaDisponible.remove(ru);
				}
				}
			}

		}

	}

	/*
	 * public void servicio2caminos(Aeropuerto origen, Aeropuerto destino, String
	 * aerolinea, ArrayList<Ruta> rutaDisponible) {
	 * 
	 * for (Ruta r : origen.getVecinos()) { if (r.haySinAerolinea(aerolinea)) {
	 * 
	 * if (r.getDestino().equals(destino)) { rutaDisponible.add(r);
	 * mostrarVuelos(rutaDisponible, aerolinea); // modificarlo al directo ya que
	 * puede que haya vuelos con escala } else if (r.getDestino() != origen) {
	 * rutaDisponible.add(r); servicio2caminos(r.getDestino(), destino, aerolinea,
	 * rutaDisponible); }
	 * 
	 * } }
	 * 
	 * } public void mostrarVuelos(ArrayList<Ruta> rutaDisponible, String aerolinea)
	 * { int escalas = 0; int kilometros = 0; for(Ruta r: rutaDisponible) { escalas=
	 * escalas++; kilometros += r.getKm();
	 * System.out.println("Las aerolineas disponibles para ir desde " +
	 * r.getOrigen().getNombre() + " hasta " + r.getDestino().getNombre() +
	 * " son:"); for(String a: r.disponibleSinAerolinea(aerolinea)) {
	 * System.out.println(a); } } System.out.println("El numero de escalas es : " +
	 * escalas); System.out.println("La cantidad de kilometros a recorrer es: " +
	 * kilometros);
	 * 
	 * }
	 * 
	 * public void VuelosDisponibles(Aeropuerto origen, Aeropuerto destino, String
	 * aerolinea) { ArrayList<Aeropuerto> caminosDisponibles = new
	 * ArrayList<Aeropuerto>(); ArrayList<String> aerolineasDisponibles = new
	 * ArrayList<String>(); int kilometros = 0; for (Aeropuerto a : aeropuertos) {
	 * a.setColor("amarillo"); } datosCaminos(origen, destino, aerolinea,
	 * caminosDisponibles, aerolineasDisponibles, kilometros);
	 * 
	 * }
	 * 
	 * public int datosCaminos(Aeropuerto origen, Aeropuerto destino, String
	 * aerolinea, ArrayList<Aeropuerto> caminosDisponibles, ArrayList<String>
	 * aerolineasDisponibles, int kilometros) {
	 * 
	 * for (Ruta r : origen.getVecinos()) { if (r.haySinAerolinea(aerolinea)) {
	 * caminosDisponibles.add(origen);
	 * aerolineasDisponibles.addAll(r.disponibleSinAerolinea(aerolinea)); kilometros
	 * += r.getKm(); } if (r.getDestino().equals(destino)) {
	 * caminosDisponibles.add(destino); int escalas = caminosDisponibles.size() - 1;
	 * mostrarVuelos(escalas, aerolineasDisponibles, kilometros);
	 * caminosDisponibles.remove(r.getDestino()); } else if
	 * (r.getDestino().getColor().equals("amarillo")) {
	 * r.getDestino().setColor("negro"); kilometros = datosCaminos(r.getDestino(),
	 * destino, aerolinea, caminosDisponibles, aerolineasDisponibles, kilometros);
	 * r.getDestino().setColor("amarillo"); }
	 * aerolineasDisponibles.removeAll(r.disponibleSinAerolinea(aerolinea));
	 * caminosDisponibles.remove(r.getDestino()); kilometros -= r.getKm(); } return
	 * kilometros; }
	 */
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
		ArrayList<Ruta> rutaDisponible = new ArrayList<Ruta>();
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

					rutaDisponible.add(ruta);
				}
			}
		}
		mostrarVuelos(rutaDisponible);
	}

	public void mostrarVuelos(ArrayList<Ruta> rutas) {
		for (Ruta r : rutas) {
			System.out.println("Vuelo disponible encontrado");
			System.out.println("Desde " + r.getOrigen().getNombre() + " hacia " + r.getDestino().getNombre());
			System.out.println("Los kilometros a recorrer son: " + r.getKm());
			System.out.println("Las aerolineas disponibles para este vuelo son:");
			for (String aerolineas : r.asientosDisponibles()) {
				System.out.println(aerolineas);
			}
		}
	}

}
