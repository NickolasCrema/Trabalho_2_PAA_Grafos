package Algoritmos;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import Graph.Aresta;
import Graph.Grafo;
import Graph.Vertice;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

/*
 * Classe encarregada do algoritmo de Prim
 */
public class Prim {

	private Integer edgeWeight[];
	private Integer[] vertex;
	private Integer destVertex[];
	private ArrayList<Integer> queue;
	int totalWeight;
	
	/*
	 * Construtor da classe
	 */
	public Prim(Grafo graph) {
		int size = graph.getVertices().size();
		this.vertex = new Integer[size];
		this.edgeWeight = new Integer[size];
		this.destVertex = new Integer[size];
		this.queue = new ArrayList<Integer>();
		this.totalWeight = 0;
	}

	/*
	 * Método privado que retorna o index do menor valor de aresta do grafo.
	 * Params: Nenhum
	 * Return: Inteiro com o index do menor valor de aresta do grafo.
	 * Pré-Condição: Nenhum.
	 * Pós-Condição: Retorna o index da aresta com menor peso do grafo.
	 */
	private Integer minEdge() {
		int minIndex = -1;
		int minValue = Integer.MAX_VALUE;
		
		for (Integer value : this.queue) {
			if (minValue > this.edgeWeight[value]) {
				minValue = this.edgeWeight[value];
				minIndex = value;
			}
		}
		return minIndex;
	}

	/*
	 * Método que realiza a execução do algoritmo de Prim e apresenta seus resultados.
	 * Params: 
	 * 		- graph: Objeto do tipo Grafo que representa a estrutura do grafo.
	 * 		- src: Inteiro que representa o id do vértice de origem.
	 * Return: Nenhum
	 * Pré-Condição: Montagem correta do objeto de grafo.
	 * Pós-Condição: Realiza a execução e apresenta os resultados no console.
	 */
	public void runAlgorithm(Grafo graph, int src) {

		if(graph.getDirected() == true) {
			System.out.println("ERRO - O grafo selecionado eh orientado");
			return;
		}
		
		for (int i = 0; i < graph.getVertices().size(); i++) {
			this.queue.add(graph.getVertex(i).getId());
			this.edgeWeight[i] = Integer.MAX_VALUE;
		}
		this.vertex[src] = -1;
		this.edgeWeight[src] = 0;

		while (!this.queue.isEmpty()) {
			Integer minWeight = this.minEdge();
			this.queue.remove(minWeight);
			for (Aresta edge : graph.getVertex(minWeight).getOutgoingEdge()) {
				int dest = edge.getEnd().getId();
				int weight = edge.getWeight();
				if (weight < this.edgeWeight[dest] && this.queue.contains(dest)) {
					this.vertex[dest] = minWeight;
					this.edgeWeight[dest] = weight;
					this.destVertex[dest] = dest;
				}
			}
		}
		for(int weight : this.edgeWeight) 
			this.totalWeight += weight;
		
		this.printArr(src);
		this.drawGraph(graph);
	}

	/*
	 * Método privado que realiza a impressão do resultado da execução do algoritmo.
	 * Params: 
	 * 		- src: Inteiro que representa o id do vértice de origem.
	 * Return: Nenhum.
	 * Pré-Condição: Execução correta do algoritmo de prim.
	 * Pós-Condição: Imprime os resultados do algoritmo no console.
	 */
	private void printArr(int src) {
		System.out.println("Origem: " + src);
		
		System.out.println("Peso total: " + this.totalWeight);
		System.out.print("Arestas: ");
		for (int i = 0; i < this.vertex.length; i++) {
			if (this.vertex[i] != -1) 
				System.out.print("(" + this.vertex[i] + "," + this.destVertex[i] + ") ");
			
		}
		System.out.println();
	}
	
	/*
	 * Método privado que desenha o grafo sinalizando a árvore geradora mínima obtida pelo algoritmo de Prim.
	 * Params:
	 * 		- graph: Objeto do tipo Grafo que representa a estrutura do grafo.
	 * Return: Void.
	 * Pré-Condição: Execução correta do algoritmo de Prim.
	 * Pós-Condição: Cria um JFrame contendo o desenho do grafo.
	 */
	private void drawGraph(Grafo graph) {
		ArrayList<Aresta> primEdges = new ArrayList<Aresta>();
		var edges = graph.getEdges();
		for(int i=0; i<this.vertex.length; i++) {
			if(this.vertex[i] != -1) {
				for(Aresta edge : edges) {
					if((edge.getStart().getId() == this.vertex[i] && edge.getEnd().getId() == this.destVertex[i])
							|| (edge.getEnd().getId() == this.vertex[i] && edge.getStart().getId() == this.destVertex[i]))
						primEdges.add(edge);
				}
			}
		}
		edu.uci.ics.jung.graph.Graph<Vertice, Aresta> jungGraph = new SparseGraph<>();

        for (Vertice v : graph.getVertices()) {
            jungGraph.addVertex(v);
        }

        for (Aresta e : graph.getEdges()) {
            jungGraph.addEdge(e, e.getStart(), e.getEnd());
        }

        FRLayout<Vertice, Aresta> layout = new FRLayout<>(jungGraph);
        layout.setSize(new Dimension(1000, 700));

        VisualizationViewer<Vertice, Aresta> vv = new VisualizationViewer<>(layout);
        vv.setPreferredSize(new Dimension(1200, 750));
        
        Transformer<Vertice, String> vertexLabelTransformer = new Transformer<Vertice, String>() {
            public String transform(Vertice v) {
                Font font = new Font("Arial", Font.PLAIN, 8);
                String label = v.toString();
                String html = "<html><font face=\"" + font.getFamily() + "\" size=\"" + font.getSize()/2 + "\">" + label + "</font></html>";
                return html;
            }
        };
        vv.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);
        
        Transformer<Aresta, String> edgeLabelTransformer = new Transformer<Aresta, String>() {
            public String transform(Aresta e) {
                Font fonte = new Font("Arial", Font.PLAIN, 8);
                String label = e.toString();
                String html = "<html><font face=\"" + fonte.getFamily() + "\" size=\"" + fonte.getSize()/2 + "\">" + label + "</font></html>";
                return html;
            }
        };
        vv.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
        
        Transformer<Aresta, Paint> edgePaint = new Transformer<Aresta, Paint>() {
            public Paint transform(Aresta edge) {
                if (primEdges.contains(edge))
                    return Color.RED;
                else
                    return null;
            }
        };
        vv.getRenderContext().setEdgeFillPaintTransformer(edgePaint);
        
        JFrame frame = new JFrame("Árvore geradora mínima Prim");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
	}
}
