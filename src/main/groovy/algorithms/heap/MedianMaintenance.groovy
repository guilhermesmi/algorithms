package algorithms.heap

class MedianMaintenance {

	Comparator<Integer> inverseComparator = new ClosureComparator<Integer>({a,b -> b.compareTo(a)})

	Heap min = new Heap(10000, true)
	Heap max = new Heap(10000, false)
//	PriorityQueue<Integer> min = new PriorityQueue<Integer>(10000)
//	PriorityQueue<Integer> max = new PriorityQueue<Integer>(10000,inverseComparator)
	

	int medianSum(String fileName){
		long sum = 0;
		int minNumber = 0
		new File(fileName).eachLine { String line, int i ->
			if (!line.trim().isEmpty()){
				int number = Integer.valueOf(line)
				// adds number to either min or max heap
				if (i == 1){
					max.add(number)
				} else {
					int top = max.peek()
					if (number <= top){
						max.add(number)
					} else {
						min.add(number)
					}
				}
				
				// balance the heaps
				if (min.size() - max.size() > 1){
					max.add(min.poll())
				} else if (max.size() - min.size() > 1){
					min.add(max.poll())
				}
				// calculate the median
				int median = 0
				if (min.size() == max.size()){
					median = max.peek()
				} else if (max.size() - min.size() > 0){
					median = max.peek()
				} else {
					median = min.peek()
				}
				sum+= median
			} else {
				println ("Line $i contents are empty. Content: $line.")
			}
		}
		return sum
	}
}
