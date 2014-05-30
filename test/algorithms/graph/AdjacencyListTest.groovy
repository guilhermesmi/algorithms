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
		assertEquals(5034, adjacencyList.edges.size())
		assertEquals(39, adjacencyList.findVertex("193").edges.size())
		assertEquals(24, adjacencyList.findVertex("1").edges.size())
	}
	
	@Test
	public void "contract should merge the vertices in the edge and link all connections to the new vertex"(){
		String filename = "resources/graph-test-1.txt"
		adjacencyList.readFromFile(filename)
		Edge e = adjacencyList.fingEdge("1", "3")
		Vertex contracted = adjacencyList.contract(e)
		assertEquals(3, adjacencyList.vertices.size())
		assertEquals(4, adjacencyList.edges.size())
		assertEquals(3, contracted.edges.size())
	}
}
