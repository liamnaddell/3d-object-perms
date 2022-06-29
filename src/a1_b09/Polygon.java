package a1_b09;

import java.util.LinkedHashSet;

public class Polygon extends GraphicalObject {
	LinkedHashSet<Vertex> vertices;

	public Polygon(LinkedHashSet<Vertex> vertices) {
		super();
		this.vertices = vertices;
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
