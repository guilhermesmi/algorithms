package algorithms.mincut




class AdjacencyList {

	List<Vertex> vertices

	List<Edge> edges
	
	List<Edge> uncontractedEdges
	
	Vertex findVertex(String id){
		int index = vertices.indexOf(new Vertex(id))
		if (index >= 0){
			return this.vertices[index]
		}
		null
	}
	
	Edge fingEdge (String uid, String vid){
		this.edges.find {
			return (it.u.id.equals(uid) && it.v.id.equals(vid)) || (it.u.id.equals(vid) && it.v.id.equals(uid))
		}
	}
	
	Vertex contract(Edge edge){
		Vertex u = edge.u
		Vertex v = edge.v
		this.vertices.remove(u)
		this.vertices.remove(v)
		this.edges.remove(edge)
		u.edges.remove(edge)
		v.edges.remove(edge)
		
		Vertex uv = new ContractedVertex(u,v)

		u.edges.each {
			Edge e ->
			boolean replaced = e.contract(u, uv)
//			if (e.u instanceof ContractedVertex && e.v instanceof ContractedVertex){
//				this.uncontractedEdges.remove(e)
//			}
		}
		v.edges.each {
			Edge e ->
			boolean replaced = e.contract(v, uv)
//			if (e.u instanceof ContractedVertex && e.v instanceof ContractedVertex){
//				this.uncontractedEdges.remove(e)
//			}

		}
		List<Edge> selfLoopsRemoved = uv.removeSelfLoops()
		if (selfLoopsRemoved != null && !selfLoopsRemoved.isEmpty()){
			selfLoopsRemoved.each {
				this.edges.remove(it)
				this.uncontractedEdges.remove(it)
			}
		}
		this.vertices.add(uv)
		return uv
	}
	
	void addEdge (Vertex u, Vertex v){
		Edge uv = fingEdge(u.id, v.id)
		if (uv == null){
			uv = new Edge(u: u, v: v)
			edges.add(uv)
		}
		u.addEdge(uv)
		v.addEdge(uv)
	}
	
	Vertex addVertex(String id){
		Vertex v = findVertex(id)
		if (v == null){
			v = new Vertex(id)
			vertices.add(v)
		}
		return v
	}
	
	int kargerMinCut() {
		
		Random random = new Random()
		// randomsly select an edge that hasn't been contracted
		while(this.vertices.size() > 2){
			// random seed
			int edgeIndex = random.nextInt(this.uncontractedEdges.size())
			Edge edge = this.uncontractedEdges.get(edgeIndex)
			this.uncontractedEdges.remove(edgeIndex)
			// contract itcontract
			Vertex contracted = contract(edge)
		}
		// min cut
		return this.edges.size()
	}

	void readFromFile(String fileName){
		vertices = new ArrayList<Vertex>()
		edges = new ArrayList<Edge>()

		File file = new File(fileName).eachLine { String line, int number ->
			String[] columns = line.split("\t")
			if (columns.length > 1){
				String id = columns[0]
				Vertex v = addVertex(id)
				
				for (int i = 1; i < columns.length; i++){
					String uId = columns[i]
					Vertex u = addVertex(uId)
					addEdge(u, v)
				}
			} else {
				println ("Line #$number is empty. Contents: $line")
			}
		}
		// keep this list to be used in the flip coins karger min cut
		uncontractedEdges = new ArrayList(this.edges)
	}
	
	void writeToFile(){
		new File('resources/output.txt').withWriter{
			edges.each {
				Edge e ->
				it << e.u.id + "," + e.v.id + "\n"
			}
		}
	}
}
