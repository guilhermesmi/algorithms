package algorithms.hash

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import spock.lang.Ignore;
import spock.lang.Specification;
import spock.lang.Unroll;


class TwoSumSpecification extends Specification {

	TwoSum twoSum

	def setup (){
		twoSum = new TwoSum()
	}

	@Unroll
	def "should calculate the distinct sum of 2 integers for #filename"(){
		given:
			def filePrefix = "src/test/resources/"
		expect:
			twoSum.readFromFile(filePrefix + filename)
			distincts == twoSum.distinctSums(-10000, 10000)
		where:
			filename 										| distincts
			"algo1-programming_prob-2sum-test-case-1.txt"	| 5
//			"algo1-programming_prob-2sum.txt"				| _
	}
}
