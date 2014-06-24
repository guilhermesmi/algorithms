package algorithms.list

import static org.junit.Assert.*

import org.junit.Test

import algorithms.BinarySearchTree.TreeNode

class LinkedListTest {

	LinkedList list = new LinkedList()
	
	@Test
	public void "should add element on the botton of the list"() {
		list.add(1)
		list.add(2)
		list.add(10)
		assertEquals(3,list.size)
		assertEquals(1,list.head.element)
		assertEquals(10,list.last.element)
	}
	
	@Test
	public void "should remove an element from the list"() {
		list.add(1)
		list.add(2)
		list.add(10)
		assertEquals(3,list.size)
		list.remove(2)
		assertEquals(2,list.size)
	}
	
	@Test
	public void "should get an element by its position"() {
		list.add(1)
		list.add(2)
		list.add(10)
		assertEquals(3,list.size)
		assertEquals(1,list.get(0))
		assertEquals(2,list.get(1))
		assertEquals(10,list.get(2))
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void "should throw ArrayIndexOutOfBounds when the index is out of bounds"() {
		list.add(1)
		list.add(2)
		list.add(10)
		assertEquals(3,list.size)
		list.get(3)
	}
}