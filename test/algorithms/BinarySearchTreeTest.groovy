package algorithms

import static org.junit.Assert.*

import org.junit.Test

import algorithms.BinarySearchTree.TreeNode

class BinarySearchTreeTest {

	BinarySearchTree tree = new BinarySearchTree()
	
	@Test
	public void "should print the tree"() {
		tree.add(8,3,1,6,4,7)
		println(tree)
	}
	
	@Test
	public void "should find a element added in a tree"() {
		tree.add(8,3,1,6,4,7)
		TreeNode node = tree.search(4)
		assertEquals(node.key, 4)
	}
	
	@Test
	public void "should not find an element that hasnt been added to a tree"() {
		tree.add(8,3,1,6,4,7)
		TreeNode node = tree.search(5)
		assertNull(node)
	}

	
	@Test
	public void "when added in asc order, depth should be the number of elements"() {
		tree.add(1, 3, 4, 6, 7, 8)
		List asc = tree.deepFirstSearch(true)
		assertEquals([1,3,4,6,7,8], asc)
		assertEquals([1,3,4,6,7,8].size(), tree.depth())
	}

	@Test
	public void "should return elements in natural order"() {
		tree.add(8,3,1,6,4,7)
		List asc = tree.deepFirstSearch(true)
		assertEquals([1,3,4,6,7,8], asc)
	}
	
	@Test
	public void "should return elements in descending order"() {
		tree.add(8,3,1,6,4,7)
		List desc = tree.deepFirstSearch(false)
		assertEquals([8,7,6,4,3,1], desc)
	}
}