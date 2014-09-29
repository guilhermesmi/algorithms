package algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

class SumCombinationsTest {

	SumCombinations sum = new SumCombinations();
	
	@Test
	public void "should return number of combinations for sum of elements of a given array and target sum"() {
		assertEquals(2, sum.totalCombinations([1, 2, 3, 4], 6))
		assertEquals(3, sum.totalCombinations([5, 5, 15, 10], 15))
		assertEquals(4, sum.totalCombinations([5, 5, 15, 10, 3, 3, 3, 3, 3], 15))
		assertEquals(4, sum.totalCombinations([5, 5, 15, 10, 3, 2], 15))
		
	}

}
