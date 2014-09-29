package algorithms.hash

class TwoSum {

	Set<Long> numbers = new HashSet<>()

	int distinctSums(int lower, int upper){
		int sums = 0
		for (int t = lower; t <= upper; t++){
			for (long x: numbers){
				Long y = t - x
				if (y != x && numbers.contains(y)){
					sums++
					println ("found $t = $x + $y. 2 sums found at the moment: $sums")
					break;
				}
			}
		}
		return sums
	}

	void readFromFile(String fileName){
		new File(fileName).eachLine { String line, int lineNumber ->
			if (!line.trim().isEmpty()){
				Long number = Long.valueOf(line)
				numbers.add(number)
			} else {
				println ("Line $lineNumber contents are empty. Content: $line.")
			}
		}
	}
}
