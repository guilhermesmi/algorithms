package algorithms.connectedcomponents;


public class Vertex {
	Integer id;
	Integer finishingTime = 0;
	Vertex leader;
	
	public Vertex(Integer id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vertex){
			Vertex other = (Vertex)obj;
			return id.equals(other.id);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "{id: "+id+", ft: "+ finishingTime + "}";
	}
	
}
