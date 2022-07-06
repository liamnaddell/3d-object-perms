package a1_b09;

import java.util.HashSet;
import java.util.Objects;
import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Mesh extends GraphicalObject {
	public HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	public void setReader(MeshReader reader) {
		this.reader=reader;
	}
	public void setWriter(MeshWriter writer) {
		this.writer=writer;
	}
	public void readFromFile(String file) throws WrongFileFormatException {
		HashSet<Polygon> polygons = this.reader.read(file);
		this.polygons=polygons;
	}
	public void writeToFile(String file) {
		this.writer.write(file,this.polygons);
	}
	public void transform(double[][] arr) {
		for (Polygon p : this.polygons) {
			p.transform(arr);
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(polygons);
	}
	//compare readers and writers
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesh other = (Mesh) obj;
		for (Polygon x : this.polygons) {
			Boolean found=false;
			for (Polygon y : other.polygons) {
				if (x.equals(y)) {
					found=true;
				}
			}
			if (found == false) {
				System.out.println("in.java Polygon "+x+" has no friend");
				return false;
			}
		}
		for (Polygon x : other.polygons) {
			Boolean found=false;
			for (Polygon y : this.polygons) {
				if (x.equals(y)) {
					found=true;
				}
			}
			if (found == false) {
				System.out.println("in.java Polygon "+x+" has no friend");
				return false;
			}
		}
		return true;
	}
	@Override
	public String toString() {
		return "Mesh [polygons=" + polygons + ", reader=" + reader + ", writer=" + writer + "]";
	}
	
}
