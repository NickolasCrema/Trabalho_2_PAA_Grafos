package Algoritmos;

import java.util.Arrays;

import Graph.Aresta;
import Graph.Grafo;

/*
 * Classe encarregada do algoritmo de Floyd-Warshall
 */
public class FloydWarshall {
	
	/*
	 * Método estático que realiza a execução do algoritmo Floyd-Warshall.
	 * Params: 
	 * 		- graph: Objeto do tipo Grafo para representar a estrutura do grafo.
	 * Return: Void
	 * Pré-Condição: Leitura e montagem correta da estrutura de grafo.
	 * Pós-Condição: Realiza a execução do algoritmo e imprime os resultados no console.
	 * */
	public static void runAlgorithm(Grafo graph) {

        int n = graph.getVertices().size();
        int[][] distance = new int[n][n];
        int[][] path = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
            Arrays.fill(path[i], -1);
        }

        for (Aresta e : graph.getEdges()) {
            int srcIndex = e.getStart().getId();
            int destIndex = e.getEnd().getId();
            distance[srcIndex][destIndex] = e.getWeight();
            path[srcIndex][destIndex] = destIndex;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE &&
                            distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        System.out.print("\t");
        for(int i=0; i<n; i++)
        	System.out.print(i + "\t");
        System.out.println();
        for(int i=0; i<n;i++) {
        	System.out.print(i + "\t");
        	for(int j=0; j<n; j++) {
        		if(distance[i][j] != Integer.MAX_VALUE)
        			System.out.print(distance[i][j] + "\t");
        		else
        			System.out.print("-\t");
        	}
        	System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && path[i][j] != -1) {
                    System.out.print("caminho de " + i + " para " + j + ": ");
                    int curr = i;
                    while (curr != j) {
                        System.out.print(curr + " - ");
                        curr = path[curr][j];
                    }
                    System.out.println(j);
                } else if (i != j) {
                    System.out.println("caminho de " + i + " para " + j + ": ---");
                }
                else if(i == j) {
                	System.out.println("caminho de " + i + " para " + j + ": " + i);
                }
            }
        }
    }
}