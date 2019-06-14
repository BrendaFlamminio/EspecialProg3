import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		String paisO;
		String paisD;
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		try {
			
	
			imprimir(grafo.mostrarAeropuertos(), "mostrarAeropuertos");
		
		
			imprimir(grafo.mostrarReservas(), "mostrarReservas");
			
			
			System.out.println("Servicio 1");

			System.out.println("Ingrese Aeropuerto origen: ");
			origen = new String(entrada.readLine());
			o = grafo.getAeropuerto(origen);

			System.out.println("Ingrese Aeropuerto destino: ");
			destino = new String(entrada.readLine());
			d = grafo.getAeropuerto(destino);
			
			System.out.println("Ingrese Aerolinea con la que desea viajar: ");
			aerolinea = new String(entrada.readLine());
			
			imprimir(grafo.serivicio1(o, d, aerolinea), "servicio1");	
			
			
			
			System.out.println("Servicio 2");
			System.out.println("Ingrese Aeropuerto origen: ");
			origen = new String(entrada.readLine());
			o = grafo.getAeropuerto(origen);

			System.out.println("Ingrese Aeropuerto destino: ");
			destino = new String(entrada.readLine());
			d = grafo.getAeropuerto(destino);
			
			System.out.println("Ingrese Aerolinea con la que desea no viajar: ");
			aerolinea = new String(entrada.readLine()); 
			imprimirServicio(grafo.dfs(o, d, aerolinea), "servicio2");
				

			
			//PRUEBA RAPIDA
			/*o = grafo.getAeropuerto("John F. Kennedy");

			d = grafo.getAeropuerto("Ministro Pistarini");
			aerolinea = "LATAM";
			grafo.dfs(o, d, aerolinea);

			grafo.serivicio1(o, d, aerolinea);
			grafo.servicio3("CHI", "ARG");*/
			
			
			
			
		
			
			System.out.println("Servicio 3");
			System.out.println("Ingrese pais origen: ");
			paisO = new String(entrada.readLine());
			

			System.out.println("Ingrese pais destino: ");
			paisD = new String(entrada.readLine());
			
							
			imprimirServicio(grafo.servicio3(paisO, paisD), "servicio3");
			
			
	
			
		}

		catch (Exception exc) {
			System.out.println(exc);
		}
		
		

	}
	
	public static void imprimirServicio(ArrayList<ArrayList<String>> mostrar, String nombre) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:/TUDAI-ws/TPespecial/src/Datasets/"+nombre+".csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for(ArrayList<String> a: mostrar) {
				for(String s: a) {
			
				String contenido = s;
				bw.write(contenido);
				bw.newLine();
				}
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}



public static void imprimir(ArrayList<String> mostrar, String nombre) {
	BufferedWriter bw = null;
	try {
		File file = new File("C:/TUDAI-ws/TPespecial/src/Datasets/"+nombre+".csv");
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		bw = new BufferedWriter(fw);

	
			for(String s: mostrar) {
		
			String contenido = s;
			bw.write(contenido);
			bw.newLine();
			}
	
		

	} catch (IOException ioe) {
		ioe.printStackTrace();
	} finally {
		try {
			if (bw != null)
				bw.close();
		} catch (Exception ex) {
			System.out.println("Error cerrando el BufferedWriter" + ex);
		}
	}
}

}
