package a1_b09;

import java.util.HashSet;

public class ReadDriver {

	public static void main(String[] args) {
		OBJMeshReader reader = new OBJMeshReader();
		HashSet<Polygon> plgns = null;
		try {
			plgns = reader.read("./cube.obj");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			return;
		}
		for (Polygon p : plgns) {
			System.out.println("Made polygon: "+p);
		}
	}

}
