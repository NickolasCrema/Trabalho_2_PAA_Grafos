package Graph;

/*
 * Estrutura de aresta
 */
public class Aresta {
	private Integer weight;
	private Vertice src;
	private Vertice dest;
	
	public Aresta() {}
	
	public Aresta(Vertice start, Vertice end, Integer weight) {
		this.src = start;
		this.dest = end;
		this.weight = weight;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer peso) {
		this.weight = peso;
	}

	public Vertice getStart() {
		return src;
	}

	public void setStart(Vertice inicio) {
		this.src = inicio;
	}

	public Vertice getEnd() {
		return dest;
	}

	public void setEnd(Vertice fim) {
		this.dest = fim;
	}
	
	public int compareTo(Aresta other) {
		return this.weight - other.weight;
	}
	
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.weight.toString();
		}
}
