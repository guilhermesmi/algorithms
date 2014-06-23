package algorithms.hash

import javax.sql.PooledConnection;

class Heap {

	List<Integer> values
	boolean asc
	
	public Heap(int size, boolean asc){
		this.values = new ArrayList<Integer>(size)
		this.asc = asc
	}
	
	int parent(int i){
		return i / 2
	}
	
	boolean isEmpty(){
		return size() == 0
	}
	
	int size(){
		return values.size()
	}
	
	void swap(int i, int j){
		if (i >= size() || i < 0){
			throw new IllegalArgumentException("Invalid position $i. current size is: $size()")
		}
		if (j >= size() || j < 0){
			throw new IllegalArgumentException("Invalid position $j. current size is: $size()")
		}
		Integer temp = values[i]
		values[i] = values[j]
		values[j] = temp
	}
	
	Integer[] children(int i){
		return [values[i+1],values[i+2]]
	}
	
	int smallerChild (int i){
		int child1Pos = i + 1
		int child2Pos = i + 2
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
	
	int biggerChild (int i){
		int child1Pos = i + 1
		int child2Pos = i + 2
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

	int add(Integer value){
		values.add(value)
		int i = size() - 1;
		Integer currValue = value
		Integer parentValue = values[parent(i)]
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
		Integer value = values[i]
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
		Integer value = values[i]
		Integer[] child = children(i)
		boolean isSmaller = false;
		if (child[0] != null && value < child[0]){
			isSmaller = true
		} else if (child[1] != null && value < child[1]){
			isSmaller = true
		}
		isSmaller
	}
	
	Integer peek(){
		return values[0]
	}
	
	@Override
	public String toString() {
		return values.toString()
	}
	
	Integer poll(){
		Integer root = values[0]
		int lastLeaf = size() - 1;
		swap(0,lastLeaf)
		values.remove(lastLeaf)
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
		return root
	}

	List<Integer> sort(){
		List<Integer> sorted = new ArrayList<Integer>(values.size())
		while(!this.isEmpty()){
			sorted.add(this.poll())
		}
		return sorted
	}
}

