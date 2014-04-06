package algorithms

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class QuickSortTest {

	
	QuickSort sort
	
	@Before
	public void setup (){
		sort = new QuickSort()
	}
	
	@Test
	public void "should sort a small array"() {
		assertEquals([1,2,3,4,5,6], sort.sort([1, 3, 5, 2, 4, 6]))
	}
	
	@Test
	public void "should sort a completely unsorted array"() {
		assertEquals([1,2,3,4,5,6], sort.sort([6,5,4,3,2,1]))
	}
	
	@Test(timeout=2000L)
	public void "should sort a 100,000 array fast"() {
		File input = new File("resources/IntegerArray.txt")
		List lines = input.readLines()
		List<Integer> array = new ArrayList<Integer>(lines.size())
		lines.each {
			array.add(Integer.valueOf(it))
		}
		List<Integer> sorted = sort.sort(array)
	}

}
