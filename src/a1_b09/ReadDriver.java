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
		int n = 1;
		for (Polygon p : plgns) {
			System.out.println("Made polygon " + n + ": "+p);
			n+=1;
		}
		System.out.println("Loaded " + (n-1) + " polygons");
	}

}
