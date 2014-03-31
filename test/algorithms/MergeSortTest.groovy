package algorithms

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MergeSortTest {

	
	MergeSort sort
	
	@Before
	public void setup (){
		sort = new MergeSort()
	}
	
	@Test
	public void "should return number of inversions of an array"() {
		assertEquals([1,2,3,4,5,6], sort.sort([1, 3, 5, 2, 4, 6]))
		assertEquals(3, sort.inversions)
	}
	
	@Test
	public void "should return max number of possible inversions of an array when its unsorted"() {
		assertEquals([1,2,3,4,5,6], sort.sort([6,5,4,3,2,1]))
		assertEquals(15, sort.inversions)
	}
	
	@Test
	public void "should return max number of possible inversions of a big array"() {
		File input = new File("resources/IntegerArray.txt")
		List lines = input.readLines()
		List<Integer> array = new ArrayList<Integer>(lines.size())
		lines.each {
			array.add(Integer.valueOf(it))
		}
		List<Integer> sorted = sort.sort(array)
		sorted.each {
			println(it)
		}
		println ("Inversions on array of size $array.size: $sort.inversions")
	}

}
