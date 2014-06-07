package algorithms.mincut

class Edge {
	
	Vertex u
	
	Vertex v
	
	boolean contracted
	
	/***
	 * Replaces a vertex to another one, to be used in contraction
	 * @param from the existing vertex to be replaced
	 * @param to the new vertex
	 */
	boolean contract(Vertex from, Vertex to){
		if (from.equals(u)){
			this.u = to
			to.addEdge(this)
		} else if (from.equals(v)){
			this.v = to
			to.addEdge(this)
		} else if (!(to.equals(u) || to.equals(v))){
			throw new IllegalArgumentException("Edge $this doesn't have this vertex $from, so can't be replaced by $to.")
		}
		this.contracted = true
		return true
	}
	
	Vertex to(Vertex from){
		Vertex to = null
		if (from.equals(u)){
			to = v 
		} else if (from.equals(v)){
			to = u
		} else {
			throw new IllegalArgumentException("Edge $this doesnt contain Vertex $from.")
		}
		to
	}
	
	@Override
	public String toString() {
		return u.id + " <===> " + v.id
	}

}
