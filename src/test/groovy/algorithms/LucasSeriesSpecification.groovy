package algorithms;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import spock.lang.Specification
import spock.lang.Unroll

class LucasSeriesSpecification extends Specification{

	LucasSeries lucas;

	def setup(){
		lucas = new LucasSeries();
	}
		
	def "should throw an exception when n < 0"() {
		when:
			lucas.l(-1)
		then:
			thrown(IllegalArgumentException)
	}
	
	@Unroll
	def "lucas of #number = #result"() {
		expect:
			lucas.l(number) == result
		where:
			number	|	result
				0	|	2
				1	|	1
				2	|	3
				3	|	4
				4	|	7
				100	|	1535881671
				1000|	1080713615
	}
}