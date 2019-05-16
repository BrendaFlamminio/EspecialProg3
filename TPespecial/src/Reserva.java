import java.util.HashMap;

public class Reserva {
	private String aerolinea;
	private Aeropuerto origen;
	private Aeropuerto destino;
	private int asientosR;
	
	public Reserva(Aeropuerto o, Aeropuerto d, String a, int r ) {
		aerolinea = a;
		origen = o;
		destino = d;
		asientosR= r;
	}
	public String getAerolinea() {
		return aerolinea;
	}
	public Aeropuerto getOrigen() {
		return origen;
	}
	public Aeropuerto getDestino() {
		return destino;
	}
	public int getReservados() {
		return asientosR;
	}
	
	
}


	