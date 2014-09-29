package algorithms

class SumCombinations {

	public int totalCombinations(List<Integer> numbers, int targetSum){
		List sums = [];
		numbers.eachWithIndex { baseNumber, i ->
			int sum = baseNumber;
			sums.add(sum);
			println "$sum"
			for (int j = i + 1; j < numbers.size(); j++){
				int number = numbers[j];
				println "$sum + $number"
				sum+=number;
				sums.add(sum);
				println "$sum + $number"
				sums.add(baseNumber + number);
			}
		}
		return sums.findAll{it == targetSum}.size()
	}
}
