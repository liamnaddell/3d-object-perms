package a1_b09;

import java.util.Arrays;

public class Vertex extends GraphicalObject {
	public double x;
	public double y;
	public double z;
	@Override
	public int hashCode() {
		//hacky hack hack here
		return (int) Math.round(x*y*z*10);
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
/*		return x == other.x
				&& y == other.y
				&& z == other.z;*/
		double eps = 0.0000001;
		Boolean xe=false;
		Boolean ye=false;
		Boolean ze=false;
		if (Math.abs(x-other.x) < eps) {
			xe=true;
		}
		if (Math.abs(y-other.y) < eps) {
			ye=true;
		} 
		if (Math.abs(z-other.z) < eps) {
			ze=true;
		} 

		if (xe && ye && ze) {
			//System.out.print("x:"+x+"y:"+y+"z:"+z+"=");
			//System.out.println("x:"+other.x+"y:"+other.y+"z:"+other.z);
			return true;
		} else {
			return false;
		}
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
	public void transform(double[][] arr) {
		//Jaskell programming KING
		double[] new_xyz = Arrays.stream(arr).map(row->row[0]*x+row[1]*y+row[2]*z).mapToDouble(x->x).toArray();
		this.x=new_xyz[0];
		this.y=new_xyz[1];
		this.z=new_xyz[2];
	}

	
}
