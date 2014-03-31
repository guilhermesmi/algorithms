package algorithms

class LucasSeries {
	
	Map<Integer, Integer> track;
	
	public LucasSeries (){
		track = new HashMap<Integer, Integer>()
	}

	public int l(int n){
		if (n < 0){
			throw new IllegalArgumentException("Invalid n. it must be >= 0.")
		} else if (n == 0){
			return 2;
		} else if (n == 1){
			return 1;
		} else {
			Integer l2 = track.get(n-2)
			if (l2 == null){
				l2 = l(n-2)
				track.put(n-2,l2)
			}
			Integer l1 = track.get(n-1)
			if (l1 == null){
				l1 = l(n-1)
				track.put(n-1,l1)
			}
			return l1 + l2;
		}
	}
}
