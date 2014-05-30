package algorithms.graph

class Edge {
	
	Vertex u
	
	Vertex v
	
	/***
	 * Replaces a vertex to another one, to be used in contraction
	 * @param from the existing vertex to be replaced
	 * @param to the new vertex
	 */
	boolean replace(Vertex from, Vertex to){
		if (from.equals(u)){
			this.u = to
			to.addEdge(v)
		} else if (from.equals(v)){
			this.v = to
			to.addEdge(u)
		} else {
			println("Edge $this doesn't have this vertex $from, so can't be replaced by $to.")
			return false
		}
		return true
	}
	
	@Override
	public String toString() {
		return u.id + " <===> " + v.id
	}

}
