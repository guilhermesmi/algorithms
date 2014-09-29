package algorithms.connectedcomponents

import static org.junit.Assert.*

import java.util.Map.Entry

import org.junit.Before
import org.junit.Test

import spock.lang.Ignore;
import spock.lang.Specification;

public class GraphTest extends Specification {
	
	Graph graph
	
	def setup (){
		graph = new Graph()
	}

	def "should read scc-test-case-1 containing directed graph"(){
		String filename = "src/test/resources/scc-test-case-1.txt"
		graph.readFromFile(filename)
		assertEquals(9,graph.vertices.size())
		assertEquals(11,graph.totalEdges())
		assertEquals(graph.totalEdges(),graph.totalReverseEdges())
	}
	
	def "should read scc-forum-test-case-1 containing directed graph"(){
		String filename = "src/test/resources/scc-forum-test-case-1.txt"
		graph.readFromFile(filename)
//		assertEquals(8,graph.vertices.size())
//		assertEquals(9,graph.totalEdges())
		assertEquals(graph.totalEdges(),graph.totalReverseEdges())
		Map<Vertex,Set<Vertex>> sccs = graph.kosaraju()
		sccs.each {
			Entry<Vertex,Set<Vertex>> scc ->
			int size = scc.value.size()
			println ("SCC: Leader: $scc.key. Size: $size")
		}
	}

	@Ignore // takes too much take, and need to increase JVM stack size -Xss100m
	def "should calculate the strongest connected components of a big directed graph"(){
		String filename = "src/test/resources/SCC.txt"
		println ("Reading file $filename...")
		graph.readFromFile(filename)
		assertEquals(875714,graph.vertices.size())
		int totalEdges =graph.totalEdges()
		assertEquals(5105043,totalEdges)
		assertEquals(totalEdges,graph.totalReverseEdges())
		println ("Graph has $graph.vertices.size vertices and $totalEdges edges. Calculating SCCs...")
		Map<Vertex,Set<Vertex>> sccs = graph.kosaraju()
		TreeSet<Integer> sortedSccs = new TreeSet<>()  
		sccs.each {
			Entry<Vertex,Set<Vertex>> scc ->
			sortedSccs.add(scc.value.size())
		}
		println ("The SCCs sizes are...")
		for (Integer i: sortedSccs.descendingSet()){
			print ("$i,")
		}
	}
	
	def "should calculate the shortest path from vertex 1 to some vetices"(){
		String filename = "src/test/resources/dijkstraData.txt"
		println ("Reading file $filename...")
		graph.readWeightedGraph(filename)
		assertEquals(200,graph.vertices.size())
		int totalEdges =graph.totalEdges()
		println ("Graph has $graph.vertices.size vertices and $totalEdges edges. Calculating shortest path...")
		Vertex source = graph.vertexRead[1]
		List<Integer> dests = [7,37,59,82,99,115,133,165,188,197]
		println ("Calculating the shortest path from $source.id to $dests...")
		Map<Vertex,Integer> dists = graph.dijkstra(source)
		println ("Shortest path from $source.id to $dests: ...")
		dests.each {
			print(dists[graph.vertexRead[it]])
			print(",")
		}
		
	}
}
