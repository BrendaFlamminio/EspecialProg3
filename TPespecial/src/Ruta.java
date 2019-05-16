import java.util.ArrayList;
import java.util.HashMap;

public class Ruta {
	private HashMap<String, Integer> aerolineas;
	private HashMap<String, Integer> reservas;
	private Aeropuerto origen;
	private Aeropuerto destino;
	private float kilometros;
	
	public Ruta(Aeropuerto n1, Aeropuerto n2, Float k) {
		this.origen = n1;
		this.destino = n2;
		this.kilometros = k;
	}
	public int existeAerolinea(String nombre) {
		if(aerolineas.containsKey(nombre)) {
			return asientosDisp(nombre);
		}
		return -1;
	}
	public float getKm() {
	return	this.kilometros;
	}
	public Aeropuerto getOrigen() {
		return this.origen;
	}
	public Aeropuerto getDestino() {
		return this.destino;
	}
	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}
	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}
	public boolean contiene(Aeropuerto o, Aeropuerto d) {
		if ((this.destino.equals(d)) && (this.origen.equals(o))) {
			return true;
		}
		return false;
	}

	public void addAerolinea(String n, int a) {
		aerolineas.put(n, a);
	}
	public void addReserva(String aerolinea, int reservados) {
		reservas.put(aerolinea, reservados);
	}
public int asientosDisp(String aerolinea) {
	int disponibles = aerolineas.get(aerolinea);
			if(reservas.containsKey(aerolinea)) {
				disponibles -= reservas.get(aerolinea);
			}
			return disponibles;
}
}
