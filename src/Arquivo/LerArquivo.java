package Arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Graph.Grafo;

public class LerArquivo {

	public LerArquivo() {
	}

	/*
	 * Método que realiza a leitura do arquivo txt e gera o grafo contido nele.
	 * Params: 
	 * 		- path: String com o caminho do arquivo.
	 * Return: Estrutura de grafo preenchida com os dados.
	 * Pré-Condição: O arquivo texto deve estar formatado corretamente.
	 * Pós-Condição: Retorna a estrutura de grafo preenchida.
	 */
	public Grafo geraGrafo(String path) throws IOException {
		Grafo graph = new Grafo();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = "";
		line = br.readLine();
		String[] vet = new String[2];
		vet = line.split("=");
		if (vet[1].equals("sim")) {
			graph.setDirected(true);
		} else {
			graph.setDirected(false);
		}
		line = br.readLine();
		vet = line.split("=");
		int vertices = Integer.parseInt(vet[1]);
		for (int i = 0; i < vertices; i++) {
			graph.addVertex(i);
		}
		vet = new String[4];
		if (graph.getDirected() == false) {
			while ((line = br.readLine()) != null) {
				vet = line.split("[(,):]+");
				graph.addEdge(Integer.parseInt(vet[1]), Integer.parseInt(vet[2]), Integer.parseInt(vet[3]));
			}
		}
		else {
			while ((line = br.readLine()) != null) {
				vet = line.split("[(,):]+");
				graph.addEdgeDirected(Integer.parseInt(vet[1]), Integer.parseInt(vet[2]), Integer.parseInt(vet[3]));
			}
		}

		return graph;
	}
}
