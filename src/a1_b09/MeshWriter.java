package a1_b09;

import java.util.HashSet;

public interface MeshWriter {
	public void write(String filename, HashSet<Polygon> polygons);
}
