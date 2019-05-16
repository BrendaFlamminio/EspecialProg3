
public class main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		
		CSVReader archivo = new CSVReader();
		
		archivo.leerAeropuertos(grafo);
		archivo.leerReservas(grafo);
		archivo.leerRutas(grafo);
		

	}

}
