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
		def array = [1, 3, 5, 2, 4, 6]
		assertEquals([1,2,3,4,5,6], sort.sort(array))
		println ("$sort.comparisions comparisions needed to sort: $array")
	}
	
	@Test
	public void "should sort a completely unsorted array"() {
		def array = [6,5,4,3,2,1]
		assertEquals([1,2,3,4,5,6], sort.sort(array))
		println ("$sort.comparisions comparisions needed to sort: $array")
	}
	
	List<Integer> readFile(String filename){
		File input = new File(filename)
		List lines = input.readLines()
		List<Integer> array = new ArrayList<Integer>(lines.size())
		lines.each {
			array.add(Integer.valueOf(it))
		}
		array
	}
	
	@Test(timeout=4000L)
	public void "should sort a 100,000 array using first element pivot"() {
		List<Integer> array = readFile("resources/QuickSort.txt")
		List<Integer> sorted = sort.sort(array, sort.strategy.first)
		println ("Pivot first: $sort.comparisions comparisions needed to sort: $array.size")
	}
	
	@Test(timeout=4000L)
	public void "should sort a 100,000 array using last element pivot"() {
		List<Integer> array = readFile("resources/QuickSort.txt")
		List<Integer> sorted = sort.sort(array, sort.strategy.last)
		println ("Pivot last: $sort.comparisions comparisions needed to sort: $array.size")
	}
	
	@Test(timeout=4000L)
	public void "should sort a 100,000 array using medium of three elements pivot"() {
		List<Integer> array = readFile("resources/QuickSort.txt")
		List<Integer> sorted = sort.sort(array, sort.strategy.medianOfThree)
		println ("Pivot medium of three: $sort.comparisions comparisions needed to sort: $array.size")
	}

}
