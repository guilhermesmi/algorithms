package algorithms.series;

import spock.lang.Specification;
import spock.lang.Unroll;

class LucasSeriesGrovyedSpecification extends Specification {

    LucasSeriesGroovyed lucas;

    def setup(){
        lucas = new LucasSeriesGroovyed();
    }

    def "should throw an exception when n < 0"() {
        when:
        lucas.l(-1)
        then:
        thrown(AssertionError)
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