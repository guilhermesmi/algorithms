package algorithms.connectedcomponents

import java.nio.charset.Charset
import java.util.Map.Entry




public class Graph {

	int finishingTimes
	Set<Vertex> exploredVertices
	Vertex s

	List<Vertex> vertices
	Map<Integer,Vertex> vertexRead
	Map<Vertex, Set<Edge>> edges
	Map<Vertex, Set<Edge>> reverseEdges

	public Graph() {
	}

	def comparator = new ClosureComparator<Entry<Vertex,Integer>>({a,b -> a.value.compareTo(b.value)})

	Map<Vertex,Integer> dijkstra(Vertex source){
		Map <Vertex,Integer> dists = new HashMap<>()
		this.vertices.each{
			Vertex it ->
			dists.put(it,1000000)
		}
		dists.put(source,0)
		Map<Integer,Vertex> queue = new HashMap<>()
		queue.putAll(this.vertexRead)
		while(!queue.isEmpty()){
			Vertex u = getMinDist(queue,dists)
			queue.remove(u.id)
			this.edges[u].each { Edge uv ->
				Vertex v = uv.to
				if (queue.containsKey(v.id)){
					if (dists[v] > dists[u] + uv.weight){
						dists[v] =  dists[u] + uv.weight
					}
				}
			}
		}
		return dists
	}
	
	Vertex getMinDist(Map<Integer,Vertex> queue, Map<Vertex,Integer> dists){
		Entry<Vertex,Integer>[] unsorted = dists.entrySet().toArray()
		Arrays.sort(unsorted,comparator)
		Vertex minDist = null
		unsorted.find {
			Entry<Vertex,Integer> dist ->
			if (queue.containsKey(dist.key.id)){
				minDist = dist.key
				return true 
			}
		}
		return minDist
	}

	Map<Vertex, Set<Vertex>> kosaraju() {
		// invoke dfsloop on transpose graph to calculate the finishing times
		dfsLoop(true)
		// sort by finishing times
		Vertex[] array = this.vertices.toArray(new Vertex[this.vertices.size()])
		Arrays.sort(array, new VertexFinishTimeComparator())
		this.vertices = Arrays.asList(array)
		dfsLoop(false)
		Map<Vertex, Set<Vertex>> sccs = new HashMap<>()
		for (Vertex i : this.vertices) {
			Set<Vertex> scc = null
			if (i.leader == null) {
				throw new IllegalStateException(
				"Vertex $i doesn't have a leader. Why?")
			}
			if (sccs.containsKey(i.leader)) {
				scc = sccs.get(i.leader)
			} else {
				scc = new HashSet<>()
			}
			scc.add(i)
			sccs.put(i.leader, scc)
		}
		return sccs
	}

	void dfsLoop(boolean transpose) {
		this.finishingTimes = 0
		this.exploredVertices = new HashSet<>()
		this.s = null
		for (Vertex i : this.vertices) {
			if (!this.exploredVertices.contains(i)) {
				this.s = i
				if (transpose) {
					transposeDFS(i)
				} else {
					dfs(i)
				}
			}
		}
		int numberExplored = this.exploredVertices.size()
		int numberVertices = this.vertices.size()
		if (numberExplored != numberVertices) {
			throw new IllegalStateException(
			String.format(
			"All vertices must be explored after dfsloop. # vertices: %d. # explored: %d.",
			numberVertices, numberExplored))
		}
	}

	void dfs(Vertex i) {
		this.exploredVertices.add(i)
		i.leader = s
		Set<Edge> iEdges = this.edges.get(i)
		if (iEdges != null) {
			for (Edge ij : iEdges) {
				Vertex j = ij.to
				boolean isJExplored = this.exploredVertices.contains(j)
				if (!isJExplored) {
					dfs(j)
				}
			}
		}
	}

	void transposeDFS(Vertex i) {
		this.exploredVertices.add(i)
		Set<Edge> iEdges = this.reverseEdges.get(i)
		if (iEdges != null) {
			for (Edge ij : iEdges) {
				Vertex j = ij.to
				boolean isJExplored = this.exploredVertices.contains(j)
				if (!isJExplored) {
					transposeDFS(j)
				}
			}
		}
		this.finishingTimes++
		i.finishingTime = this.finishingTimes
	}

	int totalEdges() {
		int total = 0
		for (Entry<Vertex, Set<Edge>> edges : this.edges.entrySet()) {
			total += edges.getValue().size()
		}
		return total
	}

	int totalReverseEdges() {
		int total = 0
		for (Entry<Vertex, Set<Edge>> edges : this.reverseEdges.entrySet()) {
			total += edges.getValue().size()
		}
		return total
	}

	void readWeightedGraph (String sourceFile) throws IOException {
		vertices = new LinkedList<>()
		edges = new HashMap<>()
		vertexRead = new HashMap<>()
		FileInputStream fsin = new FileInputStream(sourceFile)
		BufferedReader reader = new BufferedReader(new InputStreamReader(fsin,Charset.forName("UTF-8")))
		int lineNumber = 0
		try {

			String line
			while ((line = reader.readLine()) != null) {
				lineNumber++
				// Deal with the line
				String[] columns = line.split("\t")
				Integer uId = Integer.valueOf(columns[0].trim())
				Vertex u = addVertex(uId)
				for (int i = 1; i < columns.length; i++){
					String[] edge = columns[i].split(",")
					Integer vId = Integer.valueOf(edge[0])
					Integer weight = Integer.valueOf(edge[1])
					Vertex v = addVertex(vId)
					Edge uv = new Edge(u,v,weight)
					addEdge(uv)
				}
			}
		} finally {
			reader.close()
			System.out.println(lineNumber + " lines read from file: " + sourceFile)
		}
	}
	
	Vertex addVertex (Integer id){
		Vertex v = vertexRead.get(id)
		if (v == null){
			v = new Vertex(id)
			vertices.add(v)
			vertexRead.put(id, v)
		}
		return v
	}
	
	Edge addEdge(Edge uv){
		Set<Edge> vertexEdges = null
		if (edges.containsKey(uv.from)) {
			vertexEdges = edges.get(uv.from)
		} else {
			vertexEdges = new HashSet<>()
		}
		vertexEdges.add(uv)
		edges.put(uv.from, vertexEdges)
		return uv
	}

	void readFromFile(String sourceFile) throws IOException {
		vertices = new LinkedList<>()
		edges = new HashMap<>()
		vertexRead = new HashMap<>()
		reverseEdges = new HashMap<>()
		FileInputStream fsin = new FileInputStream(sourceFile)
		BufferedReader reader = new BufferedReader(new InputStreamReader(fsin,Charset.forName("UTF-8")))
		int lineNumber = 0
		try {

			String line
			while ((line = reader.readLine()) != null) {
				lineNumber++
				// Deal with the line
				String[] columns = line.split(" ")
				if (columns.length == 2) {
					Integer uId = Integer.valueOf(columns[0].trim())
					Integer vId = Integer.valueOf(columns[1].trim())
					Vertex u = addVertex(uId)
					Vertex v = addVertex(vId)

					// normal directed edge
					Edge uv = new Edge(u, v)
					addEdge(uv)

					// reverse
					Edge vu = new Edge(v, u)
					Set<Edge> reverseVertexEdges = null
					if (reverseEdges.containsKey(v)) {
						reverseVertexEdges = reverseEdges.get(v)
					} else {
						reverseVertexEdges = new HashSet<>()
					}
					reverseVertexEdges.add(vu)
					reverseEdges.put(v, reverseVertexEdges)
				} else {
					System.out
							.println(String.format("Line %s is empty.", line))
				}
			}
		} finally {
			reader.close()
			System.out.println(lineNumber + " lines read from file: " + sourceFile)
		}
	}
}