import java.util.ArrayList;

public class Aeropuerto {
	private ArrayList<Ruta> vecinos;
		private String pais;
		private String ciudad;
		private String nombre;
		
		private String color;

	
	public Aeropuerto(String nombre, String ciudad, String pais) {
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.pais = pais;
		this.color = "blanco";      //blanco: sin visitar ------ negro: visitado
		this.vecinos = new ArrayList<Ruta>();
		
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

	
	
	
	// itera cada arista de la lista vecinos, y le pregunta si existe una ruta directa entre estos dos nodos.
	public Ruta hasVecino(Aeropuerto n) {
		for(Ruta v: vecinos) {
			if (v.contiene(this, n))
				return v;
		}
		return null;
	}
	
	public void addRuta(Ruta a) {
		vecinos.add(a);
	}
	public String getColor() {
		return this.color;
	}
	public void setColor(String c) {
		this.color = c;
	}
	public boolean equals(Aeropuerto otro) {
		return ((this.nombre.equals(otro.getNombre()))&&(this.ciudad.equals(otro.getCiudad())));
	}
	
	public ArrayList<Ruta> getVecinos() {
		return vecinos;
	}
}
