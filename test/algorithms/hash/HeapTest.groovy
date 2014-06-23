package algorithms.hash

import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*
import org.junit.Before
import org.junit.Test


class HeapTest {
	
	Heap minHeap
	Heap maxHeap

	@Before
	public void setup (){
		minHeap = new Heap(100, true)
		maxHeap = new Heap(100, false)
	}

	@Test
	public void "add all elements on the heap so that each element is smaller than its children"(){
		Integer[] numbers = [7,24,19,25,56,68,40,29,31,58]
		numbers.each {
			Integer it ->
			minHeap.add(it)
		}
		int[] sorted = minHeap.sort()
		[7,19,24,25,29,31,40,56,58,68].eachWithIndex {it,index ->
			assertEquals(it, sorted[index])
		}
	}
	
	@Test
	public void "should sort numbers in natural order"(){
		Integer[] numbers = [2,1,5,4,3,0]
		numbers.each {
			Integer it ->
			minHeap.add(it)
		}
		int[] sorted = minHeap.sort()
		[0,1,2,3,4,5].eachWithIndex {it,index ->
			assertEquals(it, sorted[index])
		}
	}
	
	@Test
	public void "should sort numbers in descending natural order"(){
		Integer[] numbers = [2,1,5,4,3,0]
		numbers.each {
			Integer it ->
			maxHeap.add(it)
		}
		int[] sorted = maxHeap.sort()
		[5,4,3,2,1,0].eachWithIndex {it,index ->
			assertEquals(it, sorted[index])
		}
	}
	
	@Test
	public void "should sort numbers in natural order 2"(){
		Integer[] numbers = [6,2,1,9,5,3,10,7,8,4,0]
		numbers.each {
			Integer it ->
			minHeap.add(it)
		}
		int[] sorted = minHeap.sort()
		[0,1,2,3,4,5,6,7,8,9,10].eachWithIndex {it,index ->
			assertEquals(it, sorted[index])
		}
	}
	
	@Test
	public void "should keep this sorted numbers sorted"(){
		Integer[] numbers = [0,1,2,3,4,5,6,7,8,9,10]
		numbers.each {
			Integer it ->
			minHeap.add(it)
		}
		int[] sorted = minHeap.sort()
		[0,1,2,3,4,5,6,7,8,9,10].eachWithIndex {it,index ->
			assertEquals(it, sorted[index])
		}
	}
	
	@Test
	public void "should sort these numbers ascending"(){
		Integer[] numbers = [10,9,8,7,6,5,4,3,2,1,0]
		numbers.each {
			Integer it ->
			minHeap.add(it)
		}
		int[] sorted = minHeap.sort()
		[0,1,2,3,4,5,6,7,8,9,10].eachWithIndex {it,index ->
			assertEquals(it, sorted[index])
		}
	}
	
}
