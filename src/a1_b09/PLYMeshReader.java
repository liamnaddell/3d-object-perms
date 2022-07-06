package a1_b09;

import java.nio.file.Paths;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.HashSet;

public class PLYMeshReader implements MeshReader {
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException {
		Tokenizer tk = null;
		try {
			tk = new Tokenizer(Paths.get(filename));
		} catch (IOException e) {
			throw new WrongFileFormatException("Must input a valid file path");
		}
		tk.tokenize();
		//skipping through header
		/* 
		 * ply
		 * format ascii 1.0
		 * element vertex <num>
		 * property float32 x
		 * property float32 y
		 * property float32 z
		 * element face <num>
		 * property list uint8 int32 vertex_indices
		 * end_header
		 */
		tk.skip(8);
		IntToken num_vertex_t = new IntToken(0);
		tk.expect(num_vertex_t);
		int num_vertex = (int) num_vertex_t.getValue();

		tk.skip(15);
		IntToken num_face_t = new IntToken(0);
		tk.expect(num_face_t);
		int num_face = (int) num_face_t.getValue();
		tk.skip(9);
		
		ArrayList<Vertex> all_vertices = new ArrayList<Vertex>();
		HashSet<Polygon> polys = new HashSet<Polygon>();
		int mode = 0;
		for (;mode<1;) {
			Token[] xyz_t = {tk.pop(),tk.pop(),tk.pop()};

			double[] xyz = Arrays.stream(xyz_t).mapToDouble(c -> {
				if (c instanceof IntToken) {
					return (double) (int) c.getValue();
				} else if (c instanceof DoubleToken) {
					return (double) c.getValue();
				} else {
					//bug here?
					throw new RuntimeException(new WrongFileFormatException("Expecting IntToken or DoubleToken on line "+c.line()+", found "+c));
				}
			}).toArray();
			all_vertices.add(new Vertex(xyz[0],xyz[1],xyz[2]));

			tk.expect(new NlToken());
			Token peeked = tk.peek_e(0);
			if (peeked instanceof IntToken && (int) peeked.getValue() != 0) mode=1;
		}
		if (all_vertices.size()!=num_vertex) throw new WrongFileFormatException("Expected "+num_vertex+ " vertices, found "+ all_vertices.size());
		for (;mode<2;) {
			IntToken num_nodes_t = new IntToken(0);
			tk.expect(num_nodes_t);
			int n = (int) num_nodes_t.getValue();
			LinkedHashSet<Vertex> face = new LinkedHashSet<Vertex>();
			for (int i=0; i < n; i+=1) {
				IntToken vert_no_t = new IntToken(0);
				tk.expect(vert_no_t);
				int vert_no = (int) vert_no_t.getValue();
				if (vert_no < all_vertices.size()) {
					face.add(all_vertices.get(vert_no));
				} else {
					throw new WrongFileFormatException("Error encountered on line "+vert_no_t.line()+": Attempt to form polygon with vertex "+vert_no+" vertex does not exist");
				}
			}
			polys.add(new Polygon(face));
			tk.expect(new NlToken());
			if (tk.is_eof()) mode=2;
		}
		if (polys.size()!=num_face) throw new WrongFileFormatException("Expected "+num_face+ " vertices, found "+ polys.size());
		return polys;
	}
	
}
