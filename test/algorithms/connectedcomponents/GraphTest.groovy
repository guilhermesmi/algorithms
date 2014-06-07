package algorithms.connectedcomponents

import static org.junit.Assert.*

import java.util.Map.Entry

import org.junit.Before
import org.junit.Test

public class GraphTest {
	
	Graph graph
	
	@Before
	public void setup (){
		graph = new Graph()
	}

	@Test
	public void "should read scc-test-case-1 containing directed graph"(){
		String filename = "resources/scc-test-case-1.txt"
		graph.readFromFile(filename)
		assertEquals(9,graph.vertices.size())
		assertEquals(11,graph.totalEdges())
		assertEquals(graph.totalEdges(),graph.totalReverseEdges())
	}
	
	@Test
	public void "should read scc-forum-test-case-1 containing directed graph"(){
		String filename = "resources/scc-forum-test-case-1.txt"
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

		
	@Test
	public void "should calculate the strongest connected components of a big directed graph"(){
		String filename = "resources/SCC.txt"
		println ("Reading file.")
		graph.readFromFile(filename)
		assertEquals(875714,graph.vertices.size())
		int totalEdges =graph.totalEdges()
		assertEquals(5105043,totalEdges)
		assertEquals(totalEdges,graph.totalReverseEdges())
		println ("Calculating SCCs.")
		Map<Vertex,Set<Vertex>> sccs = graph.kosaraju()
		TreeSet<Integer> sortedSccs = new TreeSet<>()  
		sccs.each {
			Entry<Vertex,Set<Vertex>> scc ->
			sortedSccs.add(scc.value.size())
		}
		
		for (Integer i: sortedSccs.descendingSet()){
			println (i)
		}
		
		
	}
	
}
