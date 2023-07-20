package Algoritmos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import Graph.Aresta;
import Graph.Grafo;
import Graph.Vertice;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.graph.SparseGraph;

/*
 * Classe encarregada do algoritmo de Kruskal
 */
public class Kruskal {

	/*
	 * Construtor da classe
	 */
	public Kruskal() {
	};

	/*
	 * Estrutura de conjunto
	 */
	class Set {
		int parent, rank;
	};

	/*
	 * Método privado que retorna qual conjunto o nó pertence. 
	 * Params: 
	 * 		- subsets: Vetor da estrutura de conjunto. 
	 * 		- i: Inteiro do valor a ser procurado.
	 * Return: Retorna o ultimo elemento do conjunto. 
	 * Pré-Condição: Nenhum.
	 * Pós-Condição: Retorna o ultimo elemento do conjunto.
	 */
	private int Find(Set subsets[], int i) {
		if (subsets[i].parent != i)
			subsets[i].parent = Find(subsets, subsets[i].parent);
		return subsets[i].parent;
	}

	/*
	 * Método privado que faz a união dos conjuntos onde dois elementos se
	 * encontram. 
	 * Params: 
	 * 		- subsets: Vetor da estrutura de conjunto.
	 * 		- int u: Inteiro que representa o primeiro elemento. 
	 * 		- int v: Inteiro que representa o segundo elemento. 
	 * Return: Void. 
	 * Pré-Condição: Nenhum. 
	 * Pós-Condição: Realiza a união dos conjuntos dos dois elementos.
	 */
	private void Union(Set subsets[], int u, int v) {
		int uset = Find(subsets, u);
		int vset = Find(subsets, v);

		if (subsets[uset].rank < subsets[vset].rank)
			subsets[uset].parent = vset;
		else if (subsets[uset].rank > subsets[vset].rank)
			subsets[vset].parent = uset;
		else {
			subsets[vset].parent = uset;
			subsets[uset].rank++;
		}
	}

	/*
	 * Método que realiza a execução do algoritmo de Kruskal e apresenta seus resultados. 
	 * Params: 
	 * - graph: Objeto do tipo Grafo que representa a estrutura do grafo. 
	 * Return: Void.
	 * Pré-Condição: Montagem correta do objeto de grafo. 
	 * Pós-Condição: Realiza a execução doo algoritmo e apresenta os resultados no console.
	 */
	public void runAlgorithm(Grafo graph) {

		if (graph.getDirected() == true) {
			System.out.println("ERRO - O grafo selecionado eh orientado");
			return;
		}
		ArrayList<Aresta> kruskalEdges = new ArrayList<Aresta>();
		int vertices = graph.vertexCount();
		int weight = 0;
		Aresta edge[] = new Aresta[vertices];
		int vertexCount = 0;
		int i = 0;
		for (i = 1; i < vertices; i++)
			edge[i] = new Aresta();

		Collections.sort(graph.getEdges(), Comparator.comparingInt(Aresta::getWeight));
		Set subsets[] = new Set[vertices];
		for (i = 0; i < vertices; ++i)
			subsets[i] = new Set();

		for (int v = 0; v < vertices; ++v) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}
		i = 0;
		while (vertexCount < vertices - 1) {
			if (i < graph.getEdges().size()) {
				Aresta nextEdge = new Aresta();
				nextEdge = graph.getEdges().get(i++);
				int uset = Find(subsets, nextEdge.getStart().getId());
				int vset = Find(subsets, nextEdge.getEnd().getId());
				if (uset != vset) {
					weight += nextEdge.getWeight();
					edge[vertexCount++] = nextEdge;
					Union(subsets, uset, vset);
				}
			}
			else
				break;
		}
		System.out.println("Peso total: " + weight);
		System.out.print("Arestas: ");
		for (i = 0; i < vertexCount; ++i) {
			System.out.print("(" + edge[i].getStart().getId() + "," + edge[i].getEnd().getId() + ") ");
			kruskalEdges.add(edge[i]);
		}
		this.drawGraph(graph, kruskalEdges);
	}

	/*
	 * Método privado que desenha o grafo sinalizando a árvore geradora mínima obtida pelo algoritmo de Kruskal.
	 * Params:
	 * 		- graph: Objeto do tipo Grafo que representa a estrutura do grafo.
	 * 		- kruskalEdges: ArrayList contendo as arestas que formam a árvore geradora mínima.
	 * Return: Void.
	 * Pré-Condição: Execução correta do algoritmo de Kruskal.
	 * Pós-Condição: Cria um JFrame contendo o desenho do grafo.
	 */
	private void drawGraph(Grafo graph, ArrayList<Aresta> kruskalEdges) {
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
				String html = "<html><font face=\"" + font.getFamily() + "\" size=\"" + font.getSize() / 2 + "\">"
						+ label + "</font></html>";
				return html;
			}
		};
		vv.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);

		Transformer<Aresta, String> edgeLabelTransformer = new Transformer<Aresta, String>() {
			public String transform(Aresta e) {
				Font fonte = new Font("Arial", Font.PLAIN, 8);
				String label = e.toString();
				String html = "<html><font face=\"" + fonte.getFamily() + "\" size=\"" + fonte.getSize() / 2 + "\">"
						+ label + "</font></html>";
				return html;
			}
		};
		vv.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);

		Transformer<Aresta, Paint> edgePaint = new Transformer<Aresta, Paint>() {
			public Paint transform(Aresta edge) {
				if (kruskalEdges.contains(edge))
					return Color.RED;
				else
					return null;
			}
		};
		vv.getRenderContext().setEdgeFillPaintTransformer(edgePaint);

		JFrame frame = new JFrame("Árvore geradora mínima Kruskal");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
