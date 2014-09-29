package algorithms.connectedcomponents

class Edge {
	
	Vertex from
	
	Vertex to
	
	Integer weight
	
	public Edge(Vertex from, Vertex to){
		this.from = from
		this.to = to
	}
	
	public Edge(Vertex from, Vertex to, Integer weight){
		this.from = from
		this.to = to
		this.weight = weight
	}
	
	@Override
	public String toString() {
		return from.id + "---> " + to.id
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Edge){
			Edge other = (Edge)obj
			return from.equals(other.from) && to.equals(other.to)
		}
		return false
	}
	
	@Override
	public int hashCode() {
		return from.hashCode() + to.hashCode()
	}

}
