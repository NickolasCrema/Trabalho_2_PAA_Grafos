package Algoritmos;

import java.util.ArrayList;

import Graph.Vertice;

/*
 * Classe encarregada do algoritmo de busca em largura.
 */
public class BuscaEmLargura {
	private ArrayList<Vertice> tagged;
	private ArrayList<Vertice> queue;
	private Vertice current;
	
	/*
	 * Construtor da classe
	 */
	public BuscaEmLargura(Vertice src) {
		this.current = src;
		this.tagged = new ArrayList<Vertice>();
		this.queue = new ArrayList<Vertice>();
	}
	
	/*
	 * Método que realiza a execução do algoritmo de busca em largura.
	 * Params: Nenhum.
	 * Return: Nenhum.
	 * Pré-Condição: Montagem correta do objeto de grafo.
	 * Pós-Condição: Realiza a execução do algoritmo e apresenta os resultados no console.
	 */
	public void runAlgorithm() {
		tagged.add(current);
		System.out.print(current.getId() + " ");
		queue.add(current);
		while(queue.size() > 0) {
			Vertice visited = queue.get(0);
			for(int i=0; i < visited.getOutgoingEdge().size(); i++) {
				Vertice next = visited.getOutgoingEdge().get(i).getEnd();
				if(!tagged.contains(next)) {
					tagged.add(next);
					System.out.print(next.getId() + " ");
					queue.add(next);
				}
			}
			queue.remove(0);
		}
	}
}
