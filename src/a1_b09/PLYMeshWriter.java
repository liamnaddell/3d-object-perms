package a1_b09;

import java.util.HashSet;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class PLYMeshWriter implements MeshWriter {
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
			file.write("ply\nformat ascii 1.0\nelement vertex ");
			file.write(vs.size()+"\n");
			file.write("property float32 x\nproperty float32 y\nproperty float32 z\nelement face ");
			file.write(fs.size()+"\n");
			file.write("property list uint8 int32 vertex_indices\nend_header\n");
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