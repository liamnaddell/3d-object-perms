package a1_b09;

import java.nio.file.Paths;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;
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
		Pattern headp[] = {
				Pattern.compile("ply"),
				Pattern.compile("format ascii 1.0"),
				Pattern.compile("element vertex \\d+"),
				Pattern.compile("property float32 x"),
				Pattern.compile("property float32 y"),
				Pattern.compile("property float32 z"),
				Pattern.compile("element face \\d+"),
				Pattern.compile("property list uint8 int32 vertex_indices"),
				Pattern.compile("end_header"),
		};
		for (int j = 0; j<8; j++) {
			if (!(headp[j].matcher(tk.lines[j]).matches())) {
				throw new WrongFileFormatException("Broken header on line "+(j+1));
			}
		}
		
		Pattern vecp = Pattern.compile("-?(\\d*(\\.\\d*)?) -?(\\d*(\\.\\d*)?) -?(\\d*(\\.\\d*)?) *");
		Pattern facep = Pattern.compile("(\\d+ )+\\d+ *");
		int i = 0;
		int lmode = 0;
		for (String s : tk.lines) {
			if (i >= 9) {
				Boolean matches_vec = vecp.matcher(s).matches();
				Boolean matches_face = facep.matcher(s).matches();
				if (lmode == 0 ) {
					if (matches_vec) {
						//fine
					} else if (matches_face) {
						lmode=1;
					} else if (!matches_vec) {
						throw new WrongFileFormatException("PLYMESHREADER: Error on line "+(i+1)+" expecting vector");
					}
				} else if (lmode == 1) {
					if (matches_face) {
						//fine
					} else if (matches_vec) {
						throw new WrongFileFormatException("PLYMR: Error on line "+(1+i)+" found vector in face section");
					} else if (!matches_face) {
						throw new WrongFileFormatException("PLYMR: Bad face on line "+(1+i));
					}
				}
			}
			i++;
		}
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

			double[] xyz = new double[3];
			for (i=0; i<3;i++) {
				Token c = xyz_t[i];
				if (c instanceof IntToken) {
					xyz[i]=(double) (int) c.getValue();
				} else if (c instanceof DoubleToken) {
					xyz[i]=(double) c.getValue();
				} else {
					throw new WrongFileFormatException("Expecting IntToken or DoubleToken on line "+c.line()+", found "+c);
				}
			}
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
			for (i=0; i < n; i+=1) {
				IntToken vert_no_t = new IntToken(0);
				tk.expect(vert_no_t);
				int vert_no = (int) vert_no_t.getValue();
				if (vert_no < all_vertices.size()) {
					face.add(all_vertices.get(vert_no));
				} else {
					throw new WrongFileFormatException("Error encountered on line "+vert_no_t.line()+": Attempt to form polygon with vertex "+vert_no+" vertex does not exist");
				}
			}
			Polygon p = new Polygon(face);
			int bsize=polys.size();
			polys.add(p);
			if (polys.size() == bsize) {
				System.out.println("duplicate node p: "+p);
				num_face-=1;
			}
			tk.expect(new NlToken());
			if (tk.is_eof()) mode=2;
		}
		if (polys.size()!=num_face) throw new WrongFileFormatException("Expected "+num_face+ " vertices, found "+ polys.size());
		return polys;
	}
	
}
