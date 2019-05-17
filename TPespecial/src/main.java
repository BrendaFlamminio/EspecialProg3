
public class main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		
		CSVReader archivo = new CSVReader();
		
		//archivo.leerAeropuertos(grafo);
		//archivo.leerReservas(grafo);
		//archivo.leerRutas(grafo);
		Aeropuerto origen = new Aeropuerto("Jorge Newbery", "CABA", "ARG");
		Aeropuerto destino = new Aeropuerto("Pucon", "Pucon", "CHI");
		String aerolinea = "Aerolineas";
		grafo.VuelosDisponibles(origen, destino, aerolinea);

	}

}
