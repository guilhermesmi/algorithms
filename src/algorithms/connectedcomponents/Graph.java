package algorithms.connectedcomponents;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Graph {

	int finishingTimes;
	Set<Vertex> exploredVertices;
	Vertex s;

	List<Vertex> vertices;
	Map<Integer,Vertex> vertexRead;
	Map<Vertex, Set<Edge>> edges;
	Map<Vertex, Set<Edge>> reverseEdges;

	public Graph() {

	}

	Map<Vertex, Set<Vertex>> kosaraju() {
		// invoke dfsloop on transpose graph to calculate the finishing times
		dfsLoop(true);
		// sort by finishing times
		Vertex[] array = this.vertices.toArray(new Vertex[this.vertices.size()]);
		Arrays.sort(array, new VertexFinishTimeComparator());
		this.vertices = Arrays.asList(array);
		dfsLoop(false);
		Map<Vertex, Set<Vertex>> sccs = new HashMap<>();
		for (Vertex i : this.vertices) {
			Set<Vertex> scc = null;
			if (i.leader == null) {
				throw new IllegalStateException(
						"Vertex $i doesn't have a leader. Why?");
			}
			if (sccs.containsKey(i.leader)) {
				scc = sccs.get(i.leader);
			} else {
				scc = new HashSet<>();
			}
			scc.add(i);
			sccs.put(i.leader, scc);
		}
		return sccs;
	}

	void dfsLoop(boolean transpose) {
		this.finishingTimes = 0;
		this.exploredVertices = new HashSet<>();
		this.s = null;
		for (Vertex i : this.vertices) {
			if (!this.exploredVertices.contains(i)) {
				this.s = i;
				if (transpose) {
					transposeDFS(i);
				} else {
					dfs(i);
				}
			}
		}
		int numberExplored = this.exploredVertices.size();
		int numberVertices = this.vertices.size();
		if (numberExplored != numberVertices) {
			throw new IllegalStateException(
					String.format(
							"All vertices must be explored after dfsloop. # vertices: %d. # explored: %d.",
							numberVertices, numberExplored));
		}
	}

	void dfs(Vertex i) {
		this.exploredVertices.add(i);
		i.leader = s;
		Set<Edge> iEdges = this.edges.get(i);
		if (iEdges != null) {
			for (Edge ij : iEdges) {
				Vertex j = ij.to;
				boolean isJExplored = this.exploredVertices.contains(j);
				if (!isJExplored) {
					dfs(j);
				}
			}
		}
	}

	void transposeDFS(Vertex i) {
		this.exploredVertices.add(i);
		Set<Edge> iEdges = this.reverseEdges.get(i);
		if (iEdges != null) {
			for (Edge ij : iEdges) {
				Vertex j = ij.to;
				boolean isJExplored = this.exploredVertices.contains(j);
				if (!isJExplored) {
					transposeDFS(j);
				}
			}
		}
		this.finishingTimes++;
		i.finishingTime = this.finishingTimes;
	}

	int totalEdges() {
		int total = 0;
		for (Entry<Vertex, Set<Edge>> edges : this.edges.entrySet()) {
			total += edges.getValue().size();
		}
		return total;
	}

	int totalReverseEdges() {
		int total = 0;
		for (Entry<Vertex, Set<Edge>> edges : this.reverseEdges.entrySet()) {
			total += edges.getValue().size();
		}
		return total;
	}

	void readFromFile(String sourceFile) throws IOException {
		vertices = new LinkedList<>();
		edges = new HashMap<>();
		vertexRead = new HashMap<>();
		reverseEdges = new HashMap<>();
		FileInputStream in = new FileInputStream(sourceFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				Charset.forName("UTF-8")));
		int lineNumber = 0;
		try {

			String line;
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				// Deal with the line
				String[] columns = line.split(" ");
				if (columns.length == 2) {
					Integer uId = Integer.valueOf(columns[0].trim());
					Integer vId = Integer.valueOf(columns[1].trim());
					
					Vertex u = vertexRead.get(uId);
					if (u == null){
						u = new Vertex(uId);
						vertices.add(u);
						vertexRead.put(uId, u);
					}

					Vertex v = vertexRead.get(vId);
					if (v == null){
						v = new Vertex(vId);
						vertices.add(v);
						vertexRead.put(vId, v);
					}

					
					// normal directed edge
					Edge uv = new Edge(u, v);

					Set<Edge> vertexEdges = null;
					if (edges.containsKey(u)) {
						vertexEdges = edges.get(u);
					} else {
						vertexEdges = new HashSet<>();
					}
					vertexEdges.add(uv);
					edges.put(u, vertexEdges);

					// reverse
					Edge vu = new Edge(v, u);
					Set<Edge> reverseVertexEdges = null;
					if (reverseEdges.containsKey(v)) {
						reverseVertexEdges = reverseEdges.get(v);
					} else {
						reverseVertexEdges = new HashSet<>();
					}
					reverseVertexEdges.add(vu);
					reverseEdges.put(v, reverseVertexEdges);
				} else {
					System.out
							.println(String.format("Line %s is empty.", line));
				}
			}
		} finally {
			reader.close();
			System.out.println(lineNumber + " lines read from file: " + sourceFile);
		}
	}
}