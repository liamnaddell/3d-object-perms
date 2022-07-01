package a1_b09;

import java.util.HashSet;
import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Mesh extends GraphicalObject {
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	public void setReader(MeshReader reader) {
		this.reader=reader;
	}
	public void setWriter(MeshWriter writer) {
		this.writer=writer;
	}
	void readFromFile(String file) throws WrongFileFormatException {
		HashSet<Polygon> polygons = this.reader.read(file);
		this.polygons=polygons;
	}
	void writeToFile(String file) {
		this.writer.write(file,this.polygons);
	}
	void transform(double[][] arr) {
		//stackoverflow go brr haha
		/*System.out.println(Arrays.stream(arr)
        .map(s -> Arrays.stream(s)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "))
        )
        .collect(Collectors.joining("\n")));*/
		for (Polygon p : this.polygons) {
			//System.out.print("Transforming "+p);
			p.transform(arr);
			//System.out.println(" to "+p);
		}
	}
	@Override
	public String toString() {
		return "Mesh [polygons=" + polygons + ", reader=" + reader + ", writer=" + writer + "]";
	}
	
}
