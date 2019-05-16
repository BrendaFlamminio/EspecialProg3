import java.util.ArrayList;

public class Grafo {
	private ArrayList<Aeropuerto> aeropuertos;
	private ArrayList<Reserva> reservas;
	
	public Grafo() {
		aeropuertos = new ArrayList<Aeropuerto>();
	}
	
	public void addAeropuerto(Aeropuerto a) {
		aeropuertos.add(a);
	}
	
	
	
	public Ruta hasVecino(Aeropuerto origen, Aeropuerto destino) {
		return origen.hasVecino(destino);
	}
	
	public void addReserva(Reserva r) {
		reservas.add(r);
	}
	public ArrayList<Reserva> getReservas(){
		return this.reservas;
	}
	
	public Aeropuerto getAeropuerto(String nombre) {
		for(int i=0; i<aeropuertos.size();i++) {
			if(aeropuertos.get(i).getNombre()==nombre) {
				return aeropuertos.get(i);
			}
		}
		return null;
	}
	
	public Reserva getReserva(String aerolinea, Aeropuerto origen, Aeropuerto destino) {
		for(int i=0; i<reservas.size();i++) {
			Reserva r = reservas.get(i);
			if((r.getAerolinea()==aerolinea)
					&&(r.getOrigen()==origen)
					&&(r.getDestino()==destino)){
				return r;
			}

		}
				return null;
			
		
	}
	
	public Ruta existeDirecto(Aeropuerto origen, Aeropuerto destino) {
		Ruta ruta;
		if(hasVecino(origen, destino)!=null) {
			 ruta = hasVecino(origen, destino);
			 return ruta;
			}
		else 
			return null;
			
		}
		
	public int existeAerolinea(Ruta ruta, String aerolinea) {
		return ruta.existeAerolinea(aerolinea);
		
	}
	
	public void serivicio1(Aeropuerto o, Aeropuerto d, String aerolinea) {
		Ruta ruta = existeDirecto(o, d);
		if(ruta!=null) {
			float kilometros = ruta.getKm();
			int asientosDisponibles = existeAerolinea(ruta, aerolinea);
			if (asientosDisponibles >0) {
				System.out.println("Existe vuelo directo entre "+ o + " y " + d + "de " + kilometros + " kilometros, con " + asientosDisponibles + " asientos disponibles");
			}
			else if (asientosDisponibles == 0 ) {
				System.out.println("No hay asientos disponibles en dicha aerolinea para este vuelo directo");
			}
			else {
				System.out.println("No existe dicha aerolinea en este vuelo directo");
			}
		}
		else {
			System.out.println("No existe vuelo directo");
		}
	}

	
	
	
	
/*	public void dfs() {
		for(int i =0;i<nodos.size();i++) {
			nodos.get(i).setColor("blanco");
		}
		int tiempo=0;
		for(int i =0;i<nodos.size();i++) {
			Nodo n=nodos.get(i);
			if(n.getColor().equals("blanco")) {
				dfs_visit(n,tiempo);
			}
		}
	}
	
	public int dfs_visit(Nodo n,int t) {
		n.setColor("amarillo");
		int tiempo = t+1;
		int numero = t+1;
		n.setDestino(tiempo);
		for(int i=0;i<n.getaristas().size();i++) {
			node aux=n.getaristas().elementAt(i).getdestination();
			if(aux.getcolor().equals("blanco")) {
					 tiempo = dfs_visit(aux,tiempo);
			}
		}
		n.setcolor("negro");
		System.out.println(n.getinfo());
		tiempo++;
		n.settiempofinal(tiempo);
		System.out.println("--- " + n.gettiempodestino());
		System.out.println("--- "+n.gettiempofinal());
		return tiempo;
	}
	*/
}
