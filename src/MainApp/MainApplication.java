package MainApp;

import java.io.IOException;
import java.util.Scanner;

import Algoritmos.BellmanFord;
import Algoritmos.BuscaEmLargura;
import Algoritmos.BuscaEmProfundidade;
import Algoritmos.Dijkstra;
import Algoritmos.FloydWarshall;
import Algoritmos.Kruskal;
import Algoritmos.Prim;
import Arquivo.LerArquivo;
import Graph.Grafo;
import Graph.Vertice;
import View.GraphVisualization;

/*
 * Classe encarregada da execução geral do programa.
 */
public class MainApplication {
	/*
	 * 		Main
	 * menu do programa.
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner menu = new Scanner(System.in);
		Grafo graph = new Grafo();

		while (true) {
			
			for(int i=0; i<50; i++)		System.out.println();

			System.out.println("\n1 - Carregar grafo");
			System.out.println("0 - Sair");
			

			System.out.print("\nDigite uma opção: ");

			int opcao = menu.nextInt();
			
			for (int i = 0; i < 50; ++i) System.out.println();
			int opcaoAlgoritmo;
			String nomeArquivo;
			int startVertex;
			Vertice vertex;

			switch (opcao) {
			case 1:
				System.out.print("Digite o nome do arquivo com a extensao: ");
				nomeArquivo = menu.next();
				LerArquivo leArquivo = new LerArquivo();
				try {
					graph = leArquivo.geraGrafo(nomeArquivo);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.print("\n\n1 - Algoritmo busca em profundidade ");
				System.out.print("\n\n2 - Algoritmo busca em largura ");
				System.out.print("\n\n3 - Algoritmo Dijkstra");
				System.out.print("\n\n4 - Algoritmo BellmanFord");
				System.out.print("\n\n5 - Algoritmo Floyd-Warshall");
				System.out.print("\n\n6 - Algoritmo Kruskal");
				System.out.print("\n\n7 - Algoritmo Prim");
				System.out.print("\n\n8 - Desenhar grafo");
				System.out.print("\n\nDigite uma opcao: ");
				opcaoAlgoritmo = menu.nextInt();
				switch(opcaoAlgoritmo) {
				/*
				 * Algoritmo de busca em profundidade
				 */
				case 1:
					System.out.print("Digite o vertice de inicio: ");
					BuscaEmProfundidade bep = new BuscaEmProfundidade();
					startVertex = menu.nextInt();
					vertex = graph.getVertex(startVertex);
					for(int i=0; i<50; i++)		System.out.println();
					bep.runAlgorithm(vertex);
					System.out.println();
					System.out.println("Aperte qualquer tecla para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
		        /*
		         * Algoritmo de busca em largura 
		         */
				case 2:
					System.out.print("Digite o vertice de inicio: ");
					startVertex = menu.nextInt();
					vertex = graph.getVertex(startVertex);
					BuscaEmLargura bel = new BuscaEmLargura(vertex);
					for(int i=0; i<50; i++)		System.out.println();
					bel.runAlgorithm();
					System.out.println();
					System.out.println("Aperte enter para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
		        /*
		         * Algoritmo de Dijkstra
		         */
				case 3:
					System.out.println("Digite o vertice de inicio: ");
					startVertex = menu.nextInt();
					for(int i=0; i<50; i++)		System.out.println();
					Dijkstra.runAlgorithm(graph, startVertex);
					System.out.println();
					System.out.println();
					System.out.println("Aperte enter para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
			    /*
			     * Algoritmo de BellmanFord
			     */
				case 4:
					System.out.print("Digite o vertice de inicio: ");
					startVertex = menu.nextInt();
					int size = graph.getVertices().size();
					BellmanFord bf = new BellmanFord(size);
					for(int i=0; i<50; i++)		System.out.println();
					bf.runAlgorithm(graph, startVertex);
					System.out.println();
					System.out.println("Aperte enter para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
			    /*
			     * Algoritmo de Floyd-Warshall
			     */
				case 5:
					for(int i=0; i<50; i++)		System.out.println();
					FloydWarshall.runAlgorithm(graph);
					System.out.println();
					System.out.println();
					System.out.println("Aperte enter para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
			    /*
			     * Algoritmo de Kruskal
			     */
				case 6:
					Kruskal krusk = new Kruskal();
					for(int i=0; i<50; i++)		System.out.println();
					krusk.runAlgorithm(graph);
					System.out.println();
					System.out.println();
					System.out.println("Aperte enter para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
			    /*
			     * Algoritmo de Prim
			     */
				case 7:
					System.out.print("Digite o vertice de inicio: ");
					startVertex = menu.nextInt();
					Prim prim = new Prim(graph);
					for(int i=0; i<50; i++)		System.out.println();
					prim.runAlgorithm(graph, startVertex);
					System.out.println();
					System.out.println("Aperte enter para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
			    /*
			     * Desenho do grafo
			     */
				case 8:
					for(int i=0; i<50; i++)		System.out.println();
					GraphVisualization graphView = new GraphVisualization(graph);
					graphView.exibirGrafo();
					System.out.println("Aperte enter para continuar...");
			        new java.util.Scanner(System.in).nextLine();
			        break;
				default:
					break;
				}
					
			case 0:
				break;

			default:
				System.out.print("\nOpção Inválida!");
				break;
			}
		}
	}
}
