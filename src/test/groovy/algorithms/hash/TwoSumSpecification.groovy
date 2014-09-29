package algorithms.hash

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import spock.lang.Ignore;
import spock.lang.Specification;


class TwoSumSpecification extends Specification {


	TwoSum twoSum

	def setup (){
		twoSum = new TwoSum()
	}

	def "should calculate the distinct sum of 2 integers"(){
		String filename = "src/test/resources/algo1-programming_prob-2sum-test-case-1.txt"
		twoSum.readFromFile(filename)
		int distincts = twoSum.distinctSums(-10000, 10000)
		println("Distincts: $distincts")
	}
	
	@Ignore // need to run manually since it takes too long
	def "should calculate the distinct sum of 2 integers for big file"(){
		String filename = "src/test/resources/algo1-programming_prob-2sum.txt"
		twoSum.readFromFile(filename)
		int distincts = twoSum.distinctSums(-10000, 10000)
		println("Distincts: $distincts")
	}
}
