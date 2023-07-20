package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import Graph.Aresta;
import Graph.Grafo;
import Graph.Vertice;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

/*
 * Classe encarregada da visualização do grafo.
 */
public class GraphVisualization {

    private Grafo grafo;
    private boolean directed;

    /*
     * Construtor da classe
     */
    public GraphVisualization(Grafo grafo) {
        this.grafo = grafo;
        this.directed = grafo.getDirected();
    }

    /*
     * Método que constrói a visualização do grafo.
     * Params: Nenhum.
     * Return: Void.
     * Pré-Condição: Montagem correta do objeto de grafo.
     * Pós-Condição: Constrói a tela e a visualização do grafo.
     */
    public void exibirGrafo() {
        edu.uci.ics.jung.graph.Graph<Vertice, Aresta> jungGraph;
        if (directed) {
            jungGraph = new DirectedSparseGraph<>();
        } else {
            jungGraph = new SparseGraph<>();
        }

        for (Vertice v : grafo.getVertices()) {
            jungGraph.addVertex(v);
        }

        for (Aresta e : grafo.getEdges()) {
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

        JFrame frame = new JFrame("Visualização do Grafo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}