package Algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import Graph.Aresta;
import Graph.Grafo;
import Graph.Vertice;

/*
 * Classe encarregada do algoritmo de Dijkstra
 */
public class Dijkstra {
	
	/*
	 * Método estático que realiza a execução do algoritmo Dijkstra.
	 * Params:
	 * 		- graph: Objeto do tipo Grafo que representa a estrutura do grafo.
	 * 		- src: Inteiro que representa o id do vértice de origem.
	 * Return: Void
	 * Pré-Condição: Montagem correta do objeto de grafo.
	 * Pós-Condição: Realiza a execução do algoritmo e apresenta os resultados no console.
	 */
	public static void runAlgorithm(Grafo graph, int src) {
		
		for(Aresta aresta : graph.getEdges()) {
			if(aresta.getWeight() < 0) {
				System.out.println("Não é possível aplicar o algoritmo de Dijkstra - grafo possui arestas de peso negativo.");
				return;
			}
		}
		
		Vertice source = graph.getVertex(src);
        Map<Vertice, Integer> distance = new HashMap<>();
        Map<Vertice, Vertice> previous = new HashMap<>();
        PriorityQueue<Vertice> queue = new PriorityQueue<>((v1, v2) -> distance.getOrDefault(v1, Integer.MAX_VALUE) -
                distance.getOrDefault(v2, Integer.MAX_VALUE));

        for (Vertice v : graph.getVertices()) {
            if (v == source) {
                distance.put(v, 0);
            } else {
                distance.put(v, Integer.MAX_VALUE);
            }
            previous.put(v, null);
            queue.offer(v);
        }

        while (!queue.isEmpty()) {
            Vertice current = queue.poll();
            if (distance.get(current) == Integer.MAX_VALUE) {
                break;
            }

            for (Aresta e : current.getOutgoingEdge()) {
                Vertice neighbor = e.getEnd();
                int alt = distance.get(current) + e.getWeight();
                if (alt < distance.get(neighbor)) {
                    distance.put(neighbor, alt);
                    previous.put(neighbor, current);
                    queue.remove(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        for (Vertice v : graph.getVertices()) {
            if (distance.get(v) == Integer.MAX_VALUE) {
                System.out.println("Destino: " + v.getId() + " dist.: -- caminho: --");
            } else {
            	ArrayList<Integer> path = new ArrayList<Integer>();
                System.out.print("Destino: " + v.getId() + " dist.: " + distance.get(v));
                Vertice vertex = v;
                path.add(vertex.getId());
                while((vertex = previous.get(vertex)) != null) {
                	path.add(vertex.getId());
                }
                System.out.print(" caminho: ");
                for(int i=path.size()-1; i>=0; i--) {
                	System.out.print(path.get(i));
                	if(i!=0)
                		System.out.print(" - ");
                }
                System.out.println();
            }
        }
    }
}

