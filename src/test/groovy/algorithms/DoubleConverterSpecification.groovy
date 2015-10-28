package algorithms

import spock.lang.Specification

class DoubleConverterSpecification extends Specification{
    
	DoubleConverter converter;

	def setup(){
		converter = new DoubleConverter()
	}

    def "should convert a 1 level deep map"() {
        given:
        Map deepMap = ["1" : new BigDecimal(1), "2" : "2"]
        when:
        Map converted = converter.convert(deepMap)
        then:
        converted == ["1" : new Double(1), "2" : "2"]
    }
		
	def "should convert a 2 level deep map"() {
        given:
            Map deepMap = ["1" : new BigDecimal(1), "2" : "2", "3" : ["3.1" : new BigDecimal(3.1)]]
		when:
			Map converted = converter.convert(deepMap)
		then:
			converted == ["1" : new Double(1), "2" : "2", "3" : ["3.1" : new Double(3.1)]]
	}

    def "should convert a 3 level deep map"() {
        given:
        Map deepMap = ["1" : new BigDecimal(1), "2" : "2", "3" : ["3.1" : new BigDecimal(3.1), "3.2" : ["3.2.1" : new BigDecimal(3.21)]]]
        when:
        Map converted = converter.convert(deepMap)
        then:
        converted == ["1" : new Double(1), "2" : "2", "3" : ["3.1" : new Double(3.1), "3.2" : ["3.2.1" : new Double(3.21)]]]
    }
}