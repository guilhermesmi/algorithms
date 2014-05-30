package algorithms.graph

class Vertex {
	
	String id
	
	boolean contracted
	
	Map<Vertex,List<Edge>> edges
	
	public Vertex (String id){
		this.id = id
		edges = new HashMap<>()
	}
	
	void addEdge(Vertex to){
		List<Edge> connections
		if (edges[to] == null){
			connections = new ArrayList<Vertex>()
			edges[to] = connections
		} else {
			connections = edges[to]
		}
		connections.add(new Edge(u: this, v: to))
	}
	
	boolean isConnectedTo(Vertex b){
		List<Edge> connections = edges[b]
		return connections != null && !connections.isEmpty()
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
