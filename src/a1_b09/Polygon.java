package a1_b09;

import java.util.LinkedHashSet;

public class Polygon extends GraphicalObject {
	LinkedHashSet<Vertex> vertices;

	public Polygon(LinkedHashSet<Vertex> vs) {
		super();
		this.vertices= new LinkedHashSet<Vertex>();
		for (Vertex v : vs) {
			Vertex nv = new Vertex(v.x,v.y,v.z);
			this.vertices.add(nv);
		}
	}

	@Override
	void transform(double[][] arr) {
		for (Vertex v: this.vertices) {
			v.transform(arr);
		}
	}

	@Override
	public String toString() {
		return "Polygon [vertices=" + vertices + "]";
	}
}
