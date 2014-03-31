package algorithms


public class BinarySearchTree {

	TreeNode root

	int depth() {
		int max = 0
		root.deepFS().each {TreeNode node ->
			max = node.level > max ? node.level : max
		}
		return max
	}

	void add(Object... keys){
		keys.each{ Object key->
			if (root == null){
				root = new TreeNode(key: key, level: 1)
			} else {
				root.add(key)
			}
		}
	}

	TreeNode search (Object key){
		if (root != null){
			return root.search(key)
		} else {
			return null
		}
	}

	List deepFirstSearch (boolean asc){
		root != null ? root.deepFirstSearch(asc) : null
	}

	String toString(){
		root != null ? root.toString() : ""
	}

	class TreeNode{

		int level

		Object key
		TreeNode left
		TreeNode right

		int size() {
			return 1 + left?.size() + right?.size()
		}
		
		List deepFS () {
			List child = new ArrayList()
			if (left != null){
				child.addAll(left.deepFS())
			}
			child.add(this)
			if (right != null){
				child.addAll(right.deepFS())
			}
			return child
		}

		String toString(){
			StringBuilder str = new StringBuilder("$level - ")
			str.append(left != null ? left.key.toString() : "null")
			str.append(" - $key - ")
			str.append (right != null ? right.key.toString() : "null")
			str.append("\n")
			if (left != null) str.append(left.toString())
			if (right != null) str.append(right.toString())
			str.toString()
		}

		List deepFirstSearch (boolean asc) {
			List child = new ArrayList()
			if (asc){
				if (left != null){
					child.addAll(left.deepFirstSearch(asc))
				}
				child.add(key)
				if (right != null){
					child.addAll(right.deepFirstSearch(asc))
				}
			} else {
				if (right != null){
					child.addAll(right.deepFirstSearch(asc))
				}	
				child.add(key)
				if (left != null){
					child.addAll(left.deepFirstSearch(asc))
				}
			}
			return child
		}

		void add(Object key){
			assert(key != null)
			if (key.compareTo(this.key) < 0){
				if (left == null){
					left = new TreeNode(key: key, level: level + 1)
				} else {
					left.add(key)
				}
			} else if (key.compareTo(this.key) > 0){
				if (right == null){
					right = new TreeNode(key: key, level: level + 1)
				} else {
					right.add(key)
				}
			} else {
				throw new IllegalArgumentException("Node $key already exists in this tree.")
			}
		}

		TreeNode search (Object key){
			assert(key != null)
			int comparision = key.compareTo(this.key)
			if (comparision == 0){
				return this
			} else if (comparision < 0){
				this.left?.search(key)
			} else {
				this.right?.search(key)
			}
		}
	}
}
