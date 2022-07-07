package a1_b09;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class OFFMeshWriter implements MeshWriter {
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
					face.add(vs.indexOf(v));
				}
				fs.add(face);
			}
			//generate header
			file.write("OFF\n");
			file.write(vs.size()+" ");
			file.write(fs.size()+" ");
			file.write("0\n");
			for (Vertex v : vs) {
				double eps = 1000000.0;
				file.write(Math.rint(v.x*eps)/eps+" ");
				file.write(Math.rint(v.y*eps)/eps+" ");
				file.write(Math.rint(v.z*eps)/eps+"\n");
			}
			for (ArrayList<Integer> face : fs) {
				file.write(face.size()+" ");
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
