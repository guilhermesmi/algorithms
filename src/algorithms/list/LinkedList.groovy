package algorithms.list;

import java.lang.annotation.ElementType;

public class LinkedList {
	
	ListElement head;
	ListElement last;
	int size = 0;
	
	
	Object get(int i){
		int count = 0
		if (i >= size){
			throw new ArrayIndexOutOfBoundsException(i)
		}
		ListElement node = head
		while (count != i){
			node = node.next
			count++
		}
		return node.element
	}

	void add(Object obj){
		if (head == null){
			head = new ListElement(element: obj);
			last = head
		} else {
			ListElement newLast = new ListElement(element: obj)
			last.next = newLast
			last = newLast
		}
		this.size++
	}

	void remove(Object obj){
		ListElement current = head
		ListElement previous = null
		while(current != null){
			if (current.element.equals(obj)){
				ListElement next = current.next
				if (last == current){
					last = previous
				}
				if (previous != null){
					previous.next = next
				}
				size--
				current = null
			} else {
				current = current.next
			}
		}
	}

	class ListElement{
		Object element;
		ListElement next;
	}
}
