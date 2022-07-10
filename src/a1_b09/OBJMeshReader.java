package a1_b09;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;
import java.io.IOException;

public class OBJMeshReader implements MeshReader {
	public HashSet<Polygon> read(String file) throws WrongFileFormatException {
		/*
		 * Preliminary obj validation by regex
		 * Check line-by-line that it looks correct
		 */
		Tokenizer tk = null;
		try {
			tk = new Tokenizer(Paths.get(file));
		} catch (IOException e) {
			throw new WrongFileFormatException(e.getMessage());
		}
		tk.tokenize();
		Pattern vecp = Pattern.compile("v -?(\\d*(\\.\\d*)?) -?(\\d*(\\.\\d*)?) -?(\\d*(\\.\\d*)?) *");
		Pattern facep = Pattern.compile("f (\\d* )+\\d* *");
		int i = 0;
		int lmode = 0;
		for (String s : tk.lines) {
			Boolean matches_vec = vecp.matcher(s).matches();
			Boolean matches_face = facep.matcher(s).matches();
			if (lmode == 0 ) {
				if (matches_vec) {
					//fine
				} else if (matches_face) {
					lmode=1;
				} else if (!matches_vec) {
					throw new WrongFileFormatException("Error on line "+(i+1)+" expecting vector");
				}
			} else if (lmode == 1) {
				if (matches_face) {
					//fine
				} else if (matches_vec) {
					throw new WrongFileFormatException("Error on line "+(i+1)+" found vector in face section");
				} else if (!matches_face) {
					throw new WrongFileFormatException("Bad face on line "+(i+1));
				}
			}
			i++;
		}
		//System.out.println(tk);
		//looking for vertices,1=looking for faces
		int pmode = 0;
		ArrayList<Vertex> all_vertices = new ArrayList<Vertex>();
		HashSet<Polygon> all_gons = new HashSet<Polygon>();
		while (tk.length() > 0) {
			StrToken st = new StrToken("");
			tk.expect(st);
		    //if (st.s == "f") {
			if (st.s.equals("f")) {
				pmode = 1;
			} 
			if (pmode == 0) {
				Token x = tk.pop();
				Token y = tk.pop();
				Token z = tk.pop();
				Token[] coords = {x,y,z};
				double[] vcoords = new double[3];
				for (i = 0; i < 3;i++) {
					Token c = coords[i];
					if (!(c instanceof IntToken || c instanceof DoubleToken)) {
						throw new WrongFileFormatException("Error encountered on line "+c.line()+ " expecting IntToken or DoubleToken, found " + c);
					} else if (c instanceof IntToken) {
						vcoords[i] = Double.valueOf((int) c.getValue());
					} else {
						vcoords[i] = (double) c.getValue();
					}
				}
				tk.expect(new NlToken());
				all_vertices.add(new Vertex(vcoords[0],vcoords[1],vcoords[2]));
			} else if (pmode == 1) {
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
				int bsize = all_gons.size();
				Polygon p = new Polygon(this_verts);
				all_gons.add(p);
				if (bsize == all_gons.size()) {
					System.out.println("objmeshreader: duplicate face detected: "+p);
				}
			}
		}
		return all_gons;
	}
}