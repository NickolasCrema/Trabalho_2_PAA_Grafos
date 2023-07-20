package Graph;

import java.util.ArrayList;

/*
 * Estrutura de vertice
 */
public class Vertice {
	private Integer id;
	private ArrayList<Aresta> incomingEdge;
	private ArrayList<Aresta> outgoingEdge;
	
	public Vertice() {}
	
	public Vertice(Integer id) {
		this.id = id;
		this.incomingEdge = new ArrayList<Aresta>();
		this.outgoingEdge = new ArrayList<Aresta>();
	}
	
	public void addIncomingEdge(Aresta edge) {
		this.incomingEdge.add(edge);
	}
	
	public void addOutgoingEdge(Aresta edge) {
		this.outgoingEdge.add(edge);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Aresta> getIncomingEdge() {
		return incomingEdge;
	}

	public void setIncomingEdge(ArrayList<Aresta> incomingEdge) {
		this.incomingEdge = incomingEdge;
	}

	public ArrayList<Aresta> getOutgoingEdge() {
		return outgoingEdge;
	}

	public void setOutgoingEdge(ArrayList<Aresta> outgoingEdge) {
		this.outgoingEdge = outgoingEdge;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "vertex id: " + this.getId();
	}
}
