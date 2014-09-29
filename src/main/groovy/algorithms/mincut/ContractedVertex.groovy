package algorithms.mincut

class ContractedVertex extends Vertex {
	
	Vertex sourceU
	Vertex sourceV
	
	public ContractedVertex (Vertex u, Vertex v){
		super("(" + u.id + "," + v.id + ")")
		this.sourceU = u
		this.sourceV = v
	}
	
	boolean isOriginatedFrom(Vertex v){
		return v.equals(sourceU) || v.equals(sourceV)
	}
}
