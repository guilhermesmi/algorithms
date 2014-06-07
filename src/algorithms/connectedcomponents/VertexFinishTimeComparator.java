package algorithms.connectedcomponents;

import java.util.Comparator;

public class VertexFinishTimeComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex o1, Vertex o2) {
		return o2.finishingTime.compareTo(o1.finishingTime);
	}

}
