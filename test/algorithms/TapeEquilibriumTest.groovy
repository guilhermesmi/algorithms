package algorithms

import static org.junit.Assert.*

import org.junit.Test

class TapeEquilibriumTest {

	TapeEquilibrium tape = new TapeEquilibrium()
	
	@Test
	public void "min = 1"() {
		assertEquals(1, tape.minDiff([3, 1, 2, 4, 3] as int[]))
	}
}
