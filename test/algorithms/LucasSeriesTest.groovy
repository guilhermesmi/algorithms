package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

class LucasSeriesTest {

	LucasSeries lucas;
	
	@Before
	public void setup(){
		lucas = new LucasSeries()
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void "should throw an exception when n < 0"() {
		lucas.l(-1)
	}
	
	@Test
	public void "l of 0 = 2"() {
		assertEquals(2, lucas.l(0));
	}
	
	@Test
	public void "l of 1 = 1"() {
		assertEquals(1, lucas.l(1));
	}
	
	@Test
	public void "l of 2 = 3"() {
		assertEquals(3, lucas.l(2));
	}

	@Test
	public void "l of 3 = 4"() {
		assertEquals(4, lucas.l(3));
	}
	
	@Test
	public void "l of 4 = 7"() {
		assertEquals(7, lucas.l(4));
	}
	
	@Test
	public void "l of 100 = 1535881671"() {
		assertEquals(1535881671, lucas.l(100));
	}

	
	@Test
	public void "l of 1000 = 1080713615 "() {
		assertEquals(1080713615, lucas.l(1000));
	}
}