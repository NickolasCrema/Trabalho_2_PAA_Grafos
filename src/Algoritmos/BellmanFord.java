package Algoritmos;

import java.util.ArrayList;

import Graph.Aresta;
import Graph.Grafo;
import Graph.Vertice;

/*
 * Classe encarregada do algoritmo de BellmanFord
 */
public class BellmanFord {

	private Integer dist[];
	private Integer path[];
	
	/*
	 * Construtor da classe
	 */
	public BellmanFord(int size) {
		this.dist = new Integer[size];
		this.path = new Integer[size];
	}
	
	/*
	 * Método que realiza a execução do algoritmo de BellmanFord.
	 * Params: 
	 * 		- graph: Objeto do tipo Grafo para representar a estrutura do grafo. 
	 * 		- src: Inteiro para representar o id do vértice de origem.
	 * Return: Void.
	 * Pré-Condição: Montagem correta do objeto de grafo.
	 * Pós-Condição: Realiza a execução do algoritmo e apresenta os resultados no console.
	 */
	public void runAlgorithm(Grafo graph, int src) {
		if(graph.getDirected() == false) {
			System.out.print("ERRO - O grafo selecionado nao eh orientado");
			return;
		}
		
		for(int i=0; i<graph.getVertices().size(); i++) {
			this.dist[i] = Integer.MAX_VALUE;
			this.path[i] = -1;
		}
		dist[src] = 0;
		
		for(int i = 1; i < graph.getVertices().size(); ++i) {
			for(Vertice vertex : graph.getVertices()) {
				for(Aresta edge : vertex.getOutgoingEdge()) {
					int x = edge.getEnd().getId();
					int y = vertex.getId();
					int w = edge.getWeight();
					if(this.dist[y] != Integer.MAX_VALUE && this.dist[x] > this.dist[y] + w) {
						this.dist[x] = this.dist[y] + w;
						this.path[x] = y;
					}
				}
			}
		}
		for(Vertice vertex : graph.getVertices()) {
			for(Aresta edge : vertex.getOutgoingEdge()) {
				int x = edge.getEnd().getId();
				int y = vertex.getId();
				int w = edge.getWeight();
				if(this.dist[y] != Integer.MAX_VALUE && this.dist[x] > this.dist[y] + w) {
					System.out.println("O grafo contem ciclo com peso negativo");
					return;
				}
			}
		}
		this.printArr(graph.getVertices(), src);
	}
	
	/*
	 * Método privado que auxilia na impressão do caminho percorrido.
	 * Params:
	 * 		- src: Inteiro para o id do vértice de origem de uma aresta.
	 * 		- dest: Inteiro para o id do vértice de destino de uma aresta.
	 * Return: Void.
	 * Pré-Condição: Nenhum
	 * Pós-Condição: Imprime o caminho percorrido pelo algoritmo.
	 */
	private void printEdge(int src, int dest) {
		if(src != dest) {
			printEdge(src, this.path[dest]);
			System.out.print(" - " + dest);
		}
	}
	
	/*
	 * Método privado que imprime o resultado da execução do algoritmo.
	 * Params: 
	 * 		- vertices: ArrayList de vertices do grafo.
	 * 		- src: Inteiro para o id do vértice de origem
	 * Return: Void.
	 * Pré-Condição: Nenhuma
	 * Pós-Condição: Imprime os resultados da execução do algoritmo.
	 */
	private void printArr(ArrayList<Vertice> vertices, int src) {
		System.out.println("Origem: " + src);
		for(Vertice vertex : vertices) {
			System.out.print("Destino: " + vertex.getId() + "\t");
			System.out.print("Dist.: " + this.dist[vertex.getId()] + "\t");
			System.out.print("Caminho: " + src + " ");
			this.printEdge(src, vertex.getId());
			System.out.println();
		}
	}
}
