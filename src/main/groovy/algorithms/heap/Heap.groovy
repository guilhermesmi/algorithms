package algorithms.heap

/**
 * Heap class that supports Integer values. 
 * It guarantees that the root if the heap will be either the smallest or the biggest element, depending if parameter asc is true or false.
 * Min / Max operations take O(1), insert / remove takes O(log n) time. Typical HeapSort implementation takes O(n log n) running time.
 * Typically it needs  O(n) space, using ArrayList to store elements on it.
 * 
 * Note that it's designed for learning purposes, so it could be improved to take generics.
 * @author epirgui
 */
class Heap {

	List<Integer> values
	boolean asc

	public Heap(int size, boolean asc){
		this.values = new ArrayList<Integer>(size)
		this.asc = asc
	}

	public Heap(){
		this.values = new ArrayList<Integer>()
		this.asc = true
	}

	private int childrenPos(int i){
		return 2 * i + 1
	}

	private int parent(int i){
		if (i == 0){
			return 0
		} else {
			return (i - 1) >>> 1;
		}
	}

	boolean isEmpty(){
		return values.isEmpty()
	}

	int size(){
		return values.size()
	}

	private void swap(int i, int j){
		int size = this.size()
		if (i >= size || i < 0){
			throw new IllegalArgumentException("Invalid position $i. current size is: $size")
		}
		if (j >= size || j < 0){
			throw new IllegalArgumentException("Invalid position $j. current size is: $size")
		}
		int temp = values[i]
		values[i] = values[j]
		values[j] = temp
	}

	private Integer[] children(int i){
		int childrenStart = childrenPos(i)
		return [
			values[childrenStart],
			values[childrenStart+1]
		]
	}

	private int smallerChild (int i){
		int child1Pos = childrenPos(i)
		int child2Pos = child1Pos + 1
		Integer child1 = values[child1Pos]
		Integer child2 = values[child2Pos]
		if (child1 != null){
			if (child2 != null){
				if (child1 < child2){
					return child1Pos
				} else {
					return child2Pos
				}
			} else {
				return child1Pos
			}
		} else {
			return child2Pos
		}
	}

	private int biggerChild (int i){
		int child1Pos = childrenPos(i)
		int child2Pos = child1Pos + 1
		Integer child1 = values[child1Pos]
		Integer child2 = values[child2Pos]
		if (child1 != null){
			if (child2 != null){
				if (child1 > child2){
					return child1Pos
				} else {
					return child2Pos
				}
			} else {
				return child1Pos
			}
		} else {
			return child2Pos
		}
	}

	/**
	 * Adds element to the heap, and guarantees that the element is the smallest / largest depending how the heap param asc was defined on heap construction.
	 * @param value to be added to the Heap. It takes O (log n) time.
	 * @return its position
	 */
	int add(int value){
		values.add(value)

		// move up
		int i = size() - 1;
		int currValue = value
		int parentValue = values[parent(i)]
		if (asc){
			while (currValue < parentValue){
				swap(i,parent(i))
				i = parent(i);
				currValue = values[i]
				parentValue = values[parent(i)]
			}
		} else {
			while (currValue > parentValue){
				swap(i,parent(i))
				i = parent(i);
				currValue = values[i]
				parentValue = values[parent(i)]
			}
		}
		return i
	}

	boolean isBiggerThanAnyChild(int i){
		int value = values[i]
		Integer[] child = children(i)
		boolean isBigger = false;
		if (child[0] != null && value > child[0]){
			isBigger = true
		} else if (child[1] != null && value > child[1]){
			isBigger = true
		}
		isBigger
	}

	boolean isSmallerThanAnyChild(int i){
		int value = values[i]
		Integer[] child = children(i)
		boolean isSmaller = false;
		if (child[0] != null && value < child[0]){
			isSmaller = true
		} else if (child[1] != null && value < child[1]){
			isSmaller = true
		}
		isSmaller
	}

	/**
	 * @return Just return the topmost element of the heap. It takes O(1) time.
	 */
	int peek(){
		return values[0]
	}

	@Override
	public String toString() {
		return values.toString()
	}

	/**
	 * @return Return and remove the top element of the Heap. Heap property (any element must be smaller/bigger than its children) needs to be restored also.
	 * It takes O(log n) time.
	 */
	int poll(){
		int root = values[0]
		int lastLeaf = size() - 1;
		swap(0,lastLeaf)
		values.remove(lastLeaf)
		if (!values.isEmpty()){
			int vPos = 0
			// min heap or max heap?
			if (asc){
				while (isBiggerThanAnyChild(vPos)){
					int smallerPos = smallerChild(vPos);
					swap(vPos,smallerPos)
					vPos = smallerPos
				}
			} else {
				while (isSmallerThanAnyChild(vPos)){
					int biggerPos = biggerChild(vPos);
					swap(vPos,biggerPos)
					vPos = biggerPos
				}
			}
		}
		return root
	}

	/**
	 * HeapSort implementation
	 * @return sorted list of elements (ascending if it's a min heap or descending if it's a max heap)
	 */
	List<Integer> sort(){
		List<Integer> sorted = new ArrayList<Integer>(values.size())
		while(!this.isEmpty()){
			sorted.add(this.poll())
		}
		return sorted
	}
}

