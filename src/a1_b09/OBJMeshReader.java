package a1_b09;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.IOException;

public class OBJMeshReader implements MeshReader {
	public HashSet<Polygon> read(String file) throws WrongFileFormatException {
		Tokenizer tk = null;
		try {
		tk = new Tokenizer(Paths.get(file));
		} catch (IOException e) {
			throw new WrongFileFormatException(e.getMessage());
		}
		tk.tokenize();
		System.out.println(tk);
		//looking for vertices,1=looking for faces
		int pmode = 0;
		ArrayList<Vertex> all_vertices = new ArrayList<Vertex>();
		HashSet<Polygon> all_gons = new HashSet<Polygon>();
		while (tk.length() > 0) {
			StrToken st = new StrToken("");
			tk.expect(st);
			System.out.println(st);
			if (st.s.equals("f")) {
				System.out.println("changed mode on line "+st.line());
				pmode = 1;
			} else if (st.s == "v" && pmode == 1) {
				throw new WrongFileFormatException("Attempt to add vertex after vertex declaration section on line "+st.line());
			}
			if (pmode == 0) {
				System.out.println("Parsing in vector mode");
				DoubleToken x = new DoubleToken(0);
				DoubleToken y = new DoubleToken(0);
				DoubleToken z = new DoubleToken(0);
				tk.expect(x);
				tk.expect(y);
				tk.expect(z);
				tk.expect(new NlToken());
				all_vertices.add(new Vertex((double) x.getValue(),(double) y.getValue(),(double) z.getValue()));
			} else if (pmode == 1) {
				System.out.println("Parsing in face mode");
				Token ast = tk.pop();
				LinkedHashSet<Vertex> this_verts = new LinkedHashSet<Vertex>();
				while (!(ast instanceof NlToken)) {
					if (ast instanceof IntToken) {
						int vert_index = (int) ast.getValue();
						vert_index-=1;
						if (vert_index >= all_vertices.size()) {
							throw new WrongFileFormatException("Error encountered on line "+ast.line()+" vertex "+vert_index +" is a non-existent index");
						} else {
							Vertex vn = all_vertices.get(vert_index);
							this_verts.add(vn);
						}
					} else {
						throw new WrongFileFormatException("Error encountered on line "+ast.line()+" expecting IntToken found "+ast.toString());
					}
					ast = tk.pop();
				}
				all_gons.add(new Polygon(this_verts));
			}
		}
		return all_gons;
	}
}