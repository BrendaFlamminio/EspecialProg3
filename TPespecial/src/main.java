import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();

		CSVReader archivo = new CSVReader();

		archivo.leerAeropuertos(grafo);
		System.out.println("Lista de aeropuertos: ");
		grafo.mostrarAeropuertos();
		archivo.leerReservas(grafo);
		archivo.leerRutas(grafo);
		 
		String origen;
		String destino;
		Aeropuerto o;
		Aeropuerto d;
		String aerolinea;
		String paisO;
		String paisD;
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			
		/*	grafo.mostrarAeropuertos();
			//grafo.mostrarReservas();
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
			
		
			System.out.println("Servicio 2");
			System.out.println("Ingrese Aeropuerto origen: ");
			origen = new String(entrada.readLine());
			o = grafo.getAeropuerto(origen);

			System.out.println("Ingrese Aeropuerto destino: ");
			destino = new String(entrada.readLine());
			d = grafo.getAeropuerto(destino);
			
			System.out.println("Ingrese Aerolinea con la que desea no viajar: ");
			aerolinea = new String(entrada.readLine()); 
				*/
			
			o = grafo.getAeropuerto("John F. Kennedy");

			d = grafo.getAeropuerto("Ministro Pistarini");
			aerolinea = "LATAM";
			grafo.dfs(o, d, aerolinea);

			/*
			
			
			System.out.println("Servicio 3");
			System.out.println("Ingrese pais origen: ");
			paisO = new String(entrada.readLine());
			

			System.out.println("Ingrese pais destino: ");
			paisD = new String(entrada.readLine());
			
							
			grafo.servicio3(paisO, paisD);
			
			*/
	
			
		}

		catch (Exception exc) {
			System.out.println(exc);
		}
		
		

	}

}
