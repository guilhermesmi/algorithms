package algorithms.connectedcomponents;

import java.util.Comparator;

public class VertexIdComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex o1, Vertex o2) {
		return o1.id.compareTo(o2.id);
	}

}
