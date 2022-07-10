package a1_b09;

import java.util.HashSet;
import java.util.ArrayList;
import java.io.*;

public class OBJMeshWriter implements MeshWriter {
	public void write(String path, HashSet<Polygon> polygons) {
		try {
			FileWriter file = new FileWriter(path);
			ArrayList<Vertex> vs = new ArrayList<Vertex>();
			HashSet<ArrayList<Integer>> fs = new HashSet<ArrayList<Integer>>();
			//decompose polygons into vertices and faces
			for (Polygon p : polygons) {
				ArrayList<Integer> face = new ArrayList<Integer>();
				for (Vertex v : p.vertices) {
					if (!(vs.contains(v))) {
						vs.add(v);
					}
					face.add(vs.indexOf(v)+1);
				}
				fs.add(face);
			}
			for (Vertex v : vs) {
				file.write("v ");
				file.write(DRounder.round(v.x)+" ");
				file.write(DRounder.round(v.y)+" ");
				file.write(DRounder.round(v.z)+"\n");
			}
			for (ArrayList<Integer> face : fs ) {
				file.write("f ");
				for (int i : face) {
					file.write(i+" ");
				}
				file.write("\n");
			}
			file.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
