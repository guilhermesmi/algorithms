package algorithms

class DoubleConverter {

	public Map convert(Map map){
        Stack<Map> stack = new Stack<>();
        stack.push(map)
        while (!stack.isEmpty()) {
            Map innerMap = stack.pop()
            innerMap.each { k, v ->
                if (v instanceof Map) {
                    stack.push(v)
                } else if (v instanceof BigDecimal) {
                    innerMap[k] = v.toDouble()
                }
            }
        }
        map
	}
}
