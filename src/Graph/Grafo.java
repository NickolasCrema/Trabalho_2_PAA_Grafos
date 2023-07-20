package Graph;

import java.util.ArrayList;

/*
 * Estrutura de grafo
 */
public class Grafo {

	private ArrayList<Vertice> vertices;
	private ArrayList<Aresta> edges;
	private Boolean directed;

	public Grafo() {
		this.vertices = new ArrayList<Vertice>();
		this.edges = new ArrayList<Aresta>();
	}

	public void addVertex(Integer data) {
		Vertice vertex = this.getVertex(data);
		if (vertex == null) {
			Vertice newVertex = new Vertice(data);
			this.vertices.add(newVertex);
		}
	}
	
	public Integer vertexCount() {
		return this.vertices.size();
	}

	public void addEdge(Integer start, Integer end, Integer weight) {
		if (!directed) {
			Vertice src = this.getVertex(start);
			Vertice dest = this.getVertex(end);
			if (src != null && dest != null) {
				Aresta edge = new Aresta(src, dest, weight);
				Aresta reverseEdge = new Aresta(dest, src, weight);
				this.edges.add(edge);
				this.edges.add(reverseEdge);
				src.addOutgoingEdge(edge);
				dest.addOutgoingEdge(reverseEdge);
			}
		} 
		else {
			System.out.println("Error #000");
		}
	}

	public void addEdgeDirected(Integer start, Integer end, Integer weight) {
		if (directed) {
			Vertice src = this.getVertex(start);
			Vertice dest = this.getVertex(end);
			if (src != null && dest != null) {
				Aresta edge = new Aresta(src, dest, weight);
				this.edges.add(edge);
				src.addOutgoingEdge(edge);
				dest.addIncomingEdge(edge);
			}
		}
		else {
			System.out.println("Error #000");
		}
	}

	public Vertice getVertex(Integer data) {
		for (Vertice vertex : vertices) {
			if (vertex.getId() == data) {
				return vertex;
			}
		}
		return null;
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Aresta> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Aresta> edges) {
		this.edges = edges;
	}

	public Boolean getDirected() {
		return directed;
	}

	public void setDirected(Boolean directed) {
		this.directed = directed;
	}
}
