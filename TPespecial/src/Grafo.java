import java.util.ArrayList;


public class Grafo {
	private ArrayList<Nodo> nodos;
	
	public Grafo() {
		nodos = new ArrayList<Nodo>();
	}
	
	public void dfs() {
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
	
	
}
