package algorithms.graph

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test


class AdjacencyListTest {

	
	AdjacencyList adjacencyList
	
	@Before
	public void setup (){
		adjacencyList = new AdjacencyList()
	}
	
	@Test
	public void "should read file containing adjacency list"(){
		String filename = "resources/kargerMinCut.txt"
		adjacencyList.readFromFile(filename)
		assertEquals(200, adjacencyList.vertices.size())
		assertEquals(2517, adjacencyList.edges.size())
		assertEquals(39, adjacencyList.findVertex("193").edges.size())
		assertEquals(24, adjacencyList.findVertex("1").edges.size())
	}
	
	@Test
	public void "should read small file containing adjacency list"(){
		String filename = "resources/graph-test-1.txt"
		adjacencyList.readFromFile(filename)
		assertEquals(4, adjacencyList.vertices.size())
		assertEquals(5, adjacencyList.edges.size())
		assertEquals(2, adjacencyList.findVertex("1").edges.size())
		assertEquals(3, adjacencyList.findVertex("2").edges.size())
		assertEquals(3, adjacencyList.findVertex("3").edges.size())
		assertEquals(2, adjacencyList.findVertex("4").edges.size())
	}
	
	
	@Test
	public void "should calculate min cut for graph-test-1"(){
		String filename = "resources/graph-test-1.txt"
		adjacencyList.readFromFile(filename)
		int repetitions = adjacencyList.vertices.size() * adjacencyList.edges.size()
		int smallest = repetitions
		for (int i = 0; i < repetitions; i++){
			int minCut = adjacencyList.kargerMinCut()
			if (minCut < smallest){
				smallest = minCut
			}
			adjacencyList.readFromFile(filename)
		}
		assertEquals(2,smallest)
		
	}
	
	@Test
	public void "should calculate min cut for forum-test-case-1"(){
		String filename = "resources/forum-test-case-1.txt"
		adjacencyList.readFromFile(filename)
//		assertEquals(8, adjacencyList.vertices.size())
		int repetitions = adjacencyList.vertices.size() * adjacencyList.edges.size()
		int smallest = repetitions
		for (int i = 0; i < repetitions; i++){
			int minCut = adjacencyList.kargerMinCut()
			if (minCut < smallest){
				smallest = minCut
				println("smallest cut: $smallest")
			}
			adjacencyList.readFromFile(filename)
		}
		assertEquals(2,smallest)
	}
	
	@Test
	public void "should calculate min cut for kargerMinCut"(){
		String filename = "resources/kargerMinCut.txt"
		adjacencyList.readFromFile(filename)
		int repetitions = adjacencyList.vertices.size() * adjacencyList.edges.size()
		int smallest = repetitions
		for (int i = 0; i < repetitions; i++){
			int minCut = adjacencyList.kargerMinCut()
			println("iteration $i. mincut: $minCut")
			if (minCut < smallest){
				smallest = minCut
				println("smallest cut: $smallest")
			}
			adjacencyList.readFromFile(filename)
		}
		assertEquals(2,smallest)
	}
	
	@Test
	public void "contract should merge the vertices in the edge and link all connections to the new vertex"(){
		String filename = "resources/graph-test-1.txt"
		adjacencyList.readFromFile(filename)
		Edge e = adjacencyList.fingEdge("1", "3")
		Vertex contracted = adjacencyList.contract(e)
		assertEquals(3, adjacencyList.vertices.size())
		assertEquals(4, adjacencyList.edges.size())
		assertEquals(3, contracted.totalEdges())
	}
	
	@Test
	public void "should calculate the min cut for a graph randmonsly"(){
		String filename = "resources/graph-test-1.txt"
		adjacencyList.readFromFile(filename)
		int minCut = adjacencyList.kargerMinCut()
	}
	
	
	@Test
	public void "should calculate the min cut for a big graph randmonsly"(){
		String filename = "resources/kargerMinCut.txt"
		adjacencyList.readFromFile(filename)
		int cut = adjacencyList.kargerMinCut()
		println("Overall mincut for a $adjacencyList.vertices.size X $adjacencyList.edges.size: $cut.")
	}
	
}
