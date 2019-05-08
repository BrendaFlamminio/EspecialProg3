import java.util.ArrayList;

public class Nodo {
	private ArrayList<Arista> vecinos;
		private String pais;
		private String ciudad;
		private String nombre;
		private String color;
		private int tiempoD;
		private int tiempoF;
	
	public Nodo(String nombre, String ciudad, String pais) {
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.pais = pais;
		this.vecinos = new ArrayList<Arista>();
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public String getPais() {
		return this.pais;
	}
	public String getCiudad() {
		return this.ciudad;
	}
	public boolean hasVecino(Nodo n) {
		return vecinos.contains(n);
	}
	
	public void addVecino(Arista a) {
		vecinos.add(a);
	}
	public String getColor() {
		return this.color;
	}
	public void setColor(String c) {
		this.color = c;
	}
	
}
