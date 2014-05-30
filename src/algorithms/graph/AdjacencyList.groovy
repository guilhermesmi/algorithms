package algorithms.graph


class AdjacencyList {

	List<Vertex> vertices

	List<Edge> edges
	
	Vertex findVertex(String id){
		int index = vertices.indexOf(new Vertex(id))
		if (index >= 0){
			return this.vertices[index]
		}
		null
	}
	
	Edge fingEdge (String u, String v){
		Edge uv = null
		edges.each {
			Edge e ->
			if ((e.u.id.equals(u) && e.v.id.equals(v)) || (e.u.id.equals(v) && e.v.id.equals(u))){
				uv = e
			}
		}
		return uv
	}
	
	Vertex contract(Edge edge){
		Vertex u = edge.u
		Vertex v = edge.v
		this.vertices.remove(u)
		this.vertices.remove(v)
		this.edges.remove(edge)
		
		Vertex uv = new Vertex(u.id + "-" + v.id)
		uv.contracted = true

		u.edges.each {
			def e ->
			e.value.each {
				boolean replaced = it.replace(u, uv)
			}
		}
		v.edges.each {
			def e ->
			e.value.each {
				boolean replaced = it.replace(v, uv)
			}
		}
		this.vertices.add(uv)
		return uv
	}
	
	void addEdge (Vertex u, Vertex v){
		Edge uv = new Edge(u: u, v: v)
		u.addEdge(v)
		v.addEdge(u)
		edges.add(uv)
	}
	
	Vertex addVertex(String id){
		Vertex v = findVertex(id)
		if (v == null){
			v = new Vertex(id)
			vertices.add(v)
		}
		return v
	}
	
	void kargerContraction () {
		
	}

	void readFromFile(String fileName){
		vertices = new LinkedList<Vertex>()
		edges = new LinkedList<Edge>()

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
	}
}
