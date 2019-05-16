import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public static void leerAeropuertos(Grafo g) {
        String csvFile = "[PATH-AL-ARCHIVO]/dataset.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);

                // ---------------------------------------------
                // Poner el codigo para cargar los datos
             Aeropuerto a = new Aeropuerto(items[0], items[1], items[2]);
                // ---------------------------------------------
            g.addAeropuerto(a);
            }
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void leerReservas(Grafo g) {
    
        String csvFile = "[PATH-AL-ARCHIVO]/dataset.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                
                
                // ---------------------------------------------
                // Poner el codigo para cargar los datos
            Aeropuerto origen = g.getAeropuerto(items[0]);
            Aeropuerto destino = g.getAeropuerto(items[1]);
            
             Reserva r = new Reserva(origen, destino, items[2], Integer.parseInt(items[3]));
             
                // ---------------------------------------------
            g.addReserva(r);
            
            }
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void leerRutas(Grafo g) {
        
        String csvFile = "[PATH-AL-ARCHIVO]/dataset.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                Aeropuerto origen = g.getAeropuerto(items[0]);
                Aeropuerto destino = g.getAeropuerto(items[1]);
                
                
                Ruta r = new Ruta(origen, destino, Float.parseFloat(items[2]));
                Ruta rVuelta = new Ruta(destino, origen, Float.parseFloat(items[2]));
              
                
                String[] aerolineasA = items[4].substring(1, items[4].length()-1).split(",");
                for(int i=0; i<aerolineasA.length; i++) {
                
                String[] asientos = aerolineasA[i].split("-");
                r.addAerolinea(asientos[0], Integer.parseInt(asientos[1]));
                rVuelta.addAerolinea(asientos[0], Integer.parseInt(asientos[1]));
                Reserva reservaExistente = g.getReserva(asientos[0], origen, destino);
                if(reservaExistente!=null) {
                	r.addReserva(asientos[0], reservaExistente.getReservados());
                }
                }
                // ---------------------------------------------
                // Poner el codigo para cargar los datos
            
              //Reserva reserva = new Reserva(origen, destino, reservas[0])
                
                
            origen.addRuta(r);
            destino.addRuta(rVuelta);
           
             
                // ---------------------------------------------
      
            }
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
