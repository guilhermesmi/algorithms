package algorithms

class QuickSort {
	
	int comparisions = 0
	
	PivotStrategy strategy = new PivotStrategy()
	
	class PivotStrategy {
		
		
		def first = { List<Integer> numbers, int start, int end -> return start }
		
		def last = { List<Integer> numbers, int start, int end -> return end - 1}
		
		def medianOfThree = { 
			List<Integer> numbers, int start, int end ->
			
			int first = numbers[start]
			int last = numbers[end - 1]
			int subArraySize = end - start
			int mediumPos =  start - 1 + (subArraySize % 2 == 0 ? subArraySize / 2 : subArraySize / 2 + 1)
			int medium = numbers[mediumPos]
			 
			int medianOfThreePos = 0
			int medianOfThree = 0
			if ((medium >= first && medium <= last) || (medium >= last && medium <= first)){
				medianOfThreePos = mediumPos
				medianOfThree = medium
			} else if ((first >= medium && first <= last ) || (first >= last && first <= medium)){
				medianOfThree = first
				medianOfThreePos = start
			} else {
				medianOfThreePos = end - 1
				medianOfThree = last
			}
			return medianOfThreePos
		}
	}
	
	public List<Integer> sort(List<Integer> numbers){
		sort(numbers, strategy.first)
		return numbers
	}

	public List<Integer> sort(List<Integer> numbers, Closure choosePivot){
		assert (numbers != null || !numbers.isEmpty())
		sort(numbers, 0, numbers.size(), choosePivot)
		return numbers
	}

	protected int partition(List<Integer> numbers, int start, int end, int pivotPos){
		int pivot = numbers[pivotPos]
		int i = start + 1
		for (int j = start + 1; j < end; j++){
			//swap
			if (numbers[j] <= pivot){
				int temp = numbers[j]
				numbers[j] = numbers[i]
				numbers[i] = temp
				i++
			}
		}
		int temp = numbers[i - 1]
		numbers[i - 1] = pivot
		numbers[pivotPos] = temp
		return i - 1
	}

	protected void sort(List<Integer> numbers, int start, int end, Closure choosePivot){
		assert (end >= start && start >= 0 && end <= numbers.size())
		if (numbers.size() == 1 || end - start <= 1){
			return
		} else {
			int pivotPos = choosePivot(numbers, start, end)
			// exchange pivots to get it on first position
			if (pivotPos != start){
				int tmp = numbers[pivotPos]
				numbers[pivotPos] = numbers[start]
				numbers[start] = tmp
			}
			pivotPos = start
			pivotPos = partition(numbers, start, end, pivotPos)
			comparisions += end - start - 1
			sort(numbers, start, pivotPos, choosePivot)
			sort(numbers, pivotPos + 1, end, choosePivot)
		}
	}
}
