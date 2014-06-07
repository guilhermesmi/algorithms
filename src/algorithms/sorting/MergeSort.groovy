package algorithms.sorting

class MergeSort {
	
	long inversions = 0

	public List<Integer> sort(List<Integer> numbers){
		assert (numbers != null || !numbers.isEmpty())
		if (numbers.size() == 1){
			return numbers
		} else if (numbers.size() == 2){
			if (numbers[0] > numbers[1]){
				inversions++
				return [numbers[1], numbers[0]]
			} else {
				return numbers
			}
		}
		int half = numbers.size() / 2
		List<Integer> left = numbers.subList(0, half)
		List<Integer> right = numbers.subList(half, numbers.size())
		left = sort(left)
		right = sort(right)
		return merge(left, right)
	}
	
	protected List<Integer> merge(List<Integer> left, List<Integer> right){
		List<Integer> result = new ArrayList<Integer>(left.size() + right.size())
		int j = 0
		int i = 0
		int n = left.size() + right.size()
		for(int idx = 0; idx < n; idx++) {
			if (i < left.size() && j < right.size()) {
				if(left[i] <= right[j]){
					result.add(left[i])
					i++
				} else {
					result.add(right[j])
					j++
					inversions+= left.size() - i
				}
			} else if(i >= left.size()) {
				result.add(right[j])
				j++
			} else {
				result.add(left[i])
				i++
			}
		}
		return result
	}
}
