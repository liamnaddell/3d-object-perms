package a1_b09;

import java.util.Objects;
import java.util.Arrays;

public class Vertex extends GraphicalObject {
	double x;
	double y;
	double z;
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y)
				&& Double.doubleToLongBits(z) == Double.doubleToLongBits(other.z);
	}
	@Override
	public String toString() {
		return "Vertex [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	public Vertex(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	void transform(double[][] arr) {
		//Jaskell programming KING
		double[] new_xyz = Arrays.stream(arr).map(row->row[0]*x+row[1]*y+row[2]*z).mapToDouble(x->x).toArray();
		this.x=new_xyz[0];
		this.y=new_xyz[1];
		this.z=new_xyz[2];
	}

	
}
