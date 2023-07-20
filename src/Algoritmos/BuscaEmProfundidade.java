package Algoritmos;

import java.util.ArrayList;

import Graph.Vertice;

/*
 * Classe encarregada do algoritmo de busca em profundidade.
 */
public class BuscaEmProfundidade {

	private ArrayList<Vertice> marcados;
	
	/*
	 * Construtor da classe.
	 */
	public BuscaEmProfundidade() {
		this.marcados = new ArrayList<Vertice>();
	}
	
	/*
	 * Realiza a execução do algoritmo de busca em profundidade.
	 * Params: 
	 * 		- prox: Objeto do tipo Vertice para representar o vértice de origem.
	 * Return: Void.
	 * Pré-Condição: Montagem correta do objeto de grafo.
	 * Pós-Condição: Realiza a execução do algoritmo e apresenta os resultados no console.
	 */
	public void runAlgorithm(Vertice prox) {
		marcados.add(prox);
		System.out.print(prox.getId() + " ");
		for(int i=0; i<prox.getOutgoingEdge().size(); i++) {
			Vertice proximo = prox.getOutgoingEdge().get(i).getEnd();
			if(!marcados.contains(proximo)) {
				runAlgorithm(proximo);
			}
		}
		
	}
}
