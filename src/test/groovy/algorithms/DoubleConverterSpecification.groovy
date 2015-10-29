package algorithms

import spock.lang.Specification

class DoubleConverterSpecification extends Specification {

    DoubleConverter converter;

    def setup() {
        converter = new DoubleConverter()
    }

    def "should convert a 1 level deep map"() {
        given:
        Map deepMap = ["1": new BigDecimal(1), "2": "2"]
        when:
        Map converted = converter.convert(deepMap)
        then:
        converted == ["1": new Double(1), "2": "2"]
        converted["1"].getClass() == Double.class // cause spock will compare by value not by its type
    }

    def "should convert a 2 level deep map"() {
        given:
        Map deepMap = ["1": new BigDecimal(1), "2": "2", "3": ["3.1": new BigDecimal(3.1)]]
        when:
        Map converted = converter.convert(deepMap)
        then:
        converted == ["1": new Double(1), "2": "2", "3": ["3.1": new Double(3.1)]]
        converted["1"].getClass() == Double.class // cause spock will compare by value not by its type
        converted["3"]["3.1"].getClass() == Double.class
    }

    def "should convert a 3 level deep map"() {
        given:
        Map deepMap = ["1": new BigDecimal(1), "2": "2", "3": ["3.1": new BigDecimal(3.1), "3.2": ["3.2.1": new BigDecimal(3.21)]]]
        when:
        Map converted = converter.convert(deepMap)
        then:
        converted == ["1": new Double(1), "2": "2", "3": ["3.1": new Double(3.1), "3.2": ["3.2.1": new Double(3.21)]]]
        converted["1"].getClass() == Double.class // cause spock will compare by value not by its type
        converted["3"]["3.1"].getClass() == Double.class
        converted["3"]["3.2"]["3.2.1"].getClass() == Double.class
    }
}