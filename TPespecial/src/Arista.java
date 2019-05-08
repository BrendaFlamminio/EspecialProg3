
public class Arista {
	private Nodo origen;
	private Nodo destino;
	private int kilometros;
	
	public Arista(Nodo n1, Nodo n2, Integer k) {
		this.origen = n1;
		this.destino = n2;
		this.kilometros = k;
	}
	
	public int getKm() {
	return	this.kilometros;
	}
	public Nodo getOrigen() {
		return this.origen;
	}
	public Nodo getDestino() {
		return this.destino;
	}
	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}
	public void setDestino(Nodo destino) {
		this.destino = destino;
	}
}
