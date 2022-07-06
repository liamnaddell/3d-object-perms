package a1_b09;

import java.util.LinkedHashSet;
import java.util.Objects;

public class Polygon extends GraphicalObject {
	public LinkedHashSet<Vertex> vertices;

	public Polygon(LinkedHashSet<Vertex> vs) {
		super();
		this.vertices= new LinkedHashSet<Vertex>();
		for (Vertex v : vs) {
			Vertex nv = new Vertex(v.x,v.y,v.z);
			this.vertices.add(nv);
		}
	}

	@Override
	public void transform(double[][] arr) {
		for (Vertex v: this.vertices) {
			v.transform(arr);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(vertices);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polygon other = (Polygon) obj;
		for (Vertex x : vertices) {
			Boolean found = false;
			for (Vertex y : other.vertices) {
				if (x.equals(y)) {
					found = true;
				}
			}
			if (found != true) {
				return false;
			}
		}
		return true;
		//return vertices.equals(other.vertices);
	}

	@Override
	public String toString() {
		return "Polygon [vertices=" + vertices + "]";
	}
}
