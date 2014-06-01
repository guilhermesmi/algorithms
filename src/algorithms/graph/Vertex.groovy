package algorithms.graph

class Vertex {
	
	String id
	
	List<Edge> edges
	
	public Vertex (String id){
		this.id = id
		edges = new ArrayList<>()
	}
	
	int totalEdges (){
		edges.size()
	}
	
	void addEdge(Edge uv){
		if (!edges.contains(uv)){
			edges.add(uv)
		}
	}
	
	void removeEdge(Edge e){
		boolean removed = edges.remove(e)
		if (!removed){
			throw new IllegalArgumentException("Edge $e can't be removed from vertex $this, since can't be found.")
		}
	}
	
	List<Edge> removeSelfLoops(){
		List<Edge> selfLoops = new LinkedList()
		edges.each {
			if (this.equals(it.u) && this.equals(it.v)){
				selfLoops.add(it)
			}
		}
		this.edges.removeAll(selfLoops)
		return selfLoops
	}
	
	@Override
	public int hashCode() {
		return id.hashCode()
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vertex){
			Vertex other = (Vertex)obj
			return id.equals(other.id)
		}
		return false
	}
	
	@Override
	public String toString() {
		return this.id
	}
}
