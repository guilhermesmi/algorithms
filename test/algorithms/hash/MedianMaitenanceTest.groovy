package algorithms.hash

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test


class MedianMaitenanceTest {


	MedianMaintenance medianMaintenance

	@Before
	public void setup (){
		medianMaintenance = new MedianMaintenance()
	}
	
	
	@Test
	public void "should calculate the median of the k number given Median-test-1.txt"(){
		String filename = "resources/Median-test-1.txt"
		int sumMedian = medianMaintenance.medianSum(filename)
		assertEquals(148,sumMedian % 10000)
	}

	@Test
	public void "should calculate the median of the k number given Median-test-2.txt"(){
		String filename = "resources/Median-test-2.txt"
		int sumMedian = medianMaintenance.medianSum(filename)
		assertEquals(82,sumMedian % 10000)
	}
	
	@Test
	public void "should calculate the median of the k number given Median-test-3.txt"(){
		String filename = "resources/Median-test-3.txt"
		int sumMedian = medianMaintenance.medianSum(filename)
		assertEquals(55,sumMedian % 10000)
	}
	
	@Test
	public void "should calculate the median of the k number given a list of integers"(){
		String filename = "resources/Median.txt"
		int sumMedian = medianMaintenance.medianSum(filename)
		assertEquals(1213,sumMedian % 10000)	
	}
}
