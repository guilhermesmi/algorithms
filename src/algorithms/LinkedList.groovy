package algorithms;

public class LinkedList {

	ListElement head;

	void add(Object obj){
		if (head == null){
			head = new ListElement(obj);
		} else {
			head.add(obj)
		}
	}

	class ListElement{
		Object element;
		ListElement next;

		void add(Object node){
			if (next == null){
				next = new ListElement(node)
			} else {
				next.add(node)
			}
		}
	}
}
