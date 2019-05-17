import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();

		CSVReader archivo = new CSVReader();

		archivo.leerAeropuertos(grafo);
		 archivo.leerReservas(grafo);
		 archivo.leerRutas(grafo);
		String origen;
		String destino;
		Aeropuerto o;
		Aeropuerto d;
		String aerolinea;
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Servicio 1");

			System.out.println("Ingrese Aeropuerto origen: ");
			origen = new String(entrada.readLine());
			o = grafo.getAeropuerto(origen);

			System.out.println("Ingrese Aeropuerto destino: ");
			destino = new String(entrada.readLine());
			d = grafo.getAeropuerto(destino);
			
			System.out.println("Ingrese Aerolinea con la que desea viajar: ");
			aerolinea = new String(entrada.readLine());
			
			grafo.serivicio1(o, d, aerolinea);	
		}

		catch (Exception exc) {
			System.out.println(exc);
		}
		
		

	}

}
