package a1_b09;

import java.util.HashSet;

public interface MeshReader {
	//throw FileNotFoundException
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException;
}
