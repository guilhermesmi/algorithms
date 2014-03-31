package algorithms

class QuickSort {

	public List<Integer> sort(List<Integer> numbers){
		assert (numbers != null || !numbers.isEmpty())
		sort(numbers, 0, numbers.size())
		return numbers
	}

	protected int choosePivot(int start, int end){
		return start
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

	protected void sort(List<Integer> numbers, int start, int end){
		assert (end >= start && start >= 0 && end <= numbers.size())
		if (numbers.size() == 1 || end - start <= 1){
			return
		} else {
			int pivotPos = choosePivot(start, end)
			pivotPos = partition(numbers, start, end, pivotPos)
			sort(numbers, start, pivotPos)
			sort(numbers, pivotPos + 1, end)
		}
	}
}
