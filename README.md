# Trabalho 2 da disciplina de Projeto e Análise de Algoritmos

---

O seguinte trabalho tem objetivo de demonstrar a execução dos seguintes algoritmos em grafos:
<li>Busca em Largura</li>
<li>Busca em Profundidade</li>
<li>Dijkstra</li>
<li>Floyd-Warshall</li>
<li>BellmanFord</li>
<li>Kruskal</li>
<li>Prim</li>
Bem como realizar o desenho do grafo de entrada.

No caso dos algoritmos Kruskal e Prim ele realiza o desenho da árvore geradora mínima em cima do desenho do grafo.

A entrada do grafo é feita a partir de um arquivo texto no seguinte formato:
<li>Linha um do arquivo: "orientado=Sim/Nao"</li>
<li>Linha dois do arquivo: "V=quantidade de vértices"</li>
<li>Demais linhas do arquivo: "(u,v):peso"</li>

---

<h4>Como executar</h4>

Ao iniciar o programa o console aparecerá a seguinte opção:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/menu-inicial.png?raw=true'/>

Ao selecionar carregar grafo, aparecerá outro input pedindo para inserir o nome do arquivo com a extensão. Após inserir o nome do arquivo aparecerá as seguintes informações para seleção do algortimo desejado:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/selecao-algoritmo.png?raw=true'/>

---

<h4>Exemplos de execução</h4>

---

<h5>Exemplo com algoritmo de Kruskal</h5>

Entrada:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-1-entrada.png?raw=true'/>

Output do console:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-1-kruskal-console.png?raw=true'/>

Desenho da árvore geradora mínima:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-1-kruskal.png?raw=true'/>

---

<h5>Exemplo com algirtmo de Prim</h5>

Mesma entrada do exemplo anterior.

Output do console:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-1-prim-console.png?raw=true'/>

Desenho da árvore geradora mínima:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-1-prim.png?raw=true'/>

---

<h5>Exemplo com algoritmo Dijkstra</h5>

Entrada:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-3-entrada.png?raw=true'/>

Output do console:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-3-djikstra-console.png?raw=true'/>

Desenho do grafo:

<img src='https://github.com/NickolasCrema/imagens_readmes/blob/main/Trabalho_2_PAA/grafo-3-desenho.png?raw=true'/>
