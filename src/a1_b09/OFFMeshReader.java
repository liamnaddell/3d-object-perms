package a1_b09;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

public class OFFMeshReader implements MeshReader {
	public HashSet<Polygon> read(String file_path) throws WrongFileFormatException {
		Tokenizer tk = null;
		try {
			tk = new Tokenizer(Paths.get(file_path));
		} catch (IOException e) {
			throw new WrongFileFormatException("Must input a valid file path");
		}
		tk.tokenize();

		Pattern headp = Pattern.compile("OFF\n\\d* \\d* \\d*");
		String header = tk.lines[0]+"\n"+tk.lines[1];
		if (!(headp.matcher(header).matches())) {
			throw new WrongFileFormatException("Broken header");
		}
		Pattern vecp = Pattern.compile(" *-?(\\d*(\\.\\d*)?) -?(\\d*(\\.\\d*)?) -?(\\d*(\\.\\d*)?) *");
		Pattern facep = Pattern.compile("(\\d* )+\\d* *");
		//skipping through header
		int i = 0;
		int lmode = 0;
		for (String s : tk.lines) {
			if (! (i < 2)) {
				Boolean matches_vec = vecp.matcher(s).matches();
				Boolean matches_face = facep.matcher(s).matches();
				if (lmode == 0 ) {
					if (matches_vec) {
						//fine
					} else if (matches_face) {
						lmode=1;
					} else if (!matches_vec) {
						throw new WrongFileFormatException("OFFMR: Error on line "+(i+1)+" expecting vector");
					}
				} else if (lmode == 1) {
					if (matches_face) {
						//fine
					} else if (matches_vec) {
						throw new WrongFileFormatException("OFFMR: Error on line "+(i+1)+" found vector in face section");
					} else if (!matches_face) {
						throw new WrongFileFormatException("OFFMR: Bad face on line "+(i+1));
					}
				}
			}
			i++;
		}
		/* 
		 * OFF
		 * <vert> <face> 0
		 * 
		 */
		tk.skip(2);
		IntToken num_vertex_t = new IntToken(0);
		tk.expect(num_vertex_t);
		int num_vertex = (int) num_vertex_t.getValue();

		IntToken num_face_t = new IntToken(0);
		tk.expect(num_face_t);
		int num_face = (int) num_face_t.getValue();
		tk.skip(2);
		
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
				} 
			}
			int bsize = all_vertices.size();
			all_vertices.add(new Vertex(xyz[0],xyz[1],xyz[2]));
			if (bsize == all_vertices.size()) {
				System.out.println("OFFMR: Found duplicate face: "+xyz[0]+","+xyz[1]+","+xyz[2]);
			}

			tk.expect(new NlToken());
			Token peeked = tk.peek_e(0);
			if (peeked instanceof IntToken && (int) peeked.getValue() != 0) mode=1;
		}
		if (all_vertices.size()!=num_vertex) throw new WrongFileFormatException("OFFMR: Expected "+num_vertex+ " vertices, found "+ all_vertices.size());
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
					throw new WrongFileFormatException("OFFMR: Error encountered on line "+vert_no_t.line()+": Attempt to form polygon with vertex "+vert_no+" vertex does not exist");
				}
			}
			int bsize = polys.size();
			Polygon p = new Polygon(face);
			polys.add(p);
			if (bsize == polys.size()) {
				System.out.println("offmeshreader: duplicate face detected: "+p+" "+polys.contains(p));
				num_face-=1;
			}

			Token maybe_r_or_nl_t = tk.peek_e(0);
			if (maybe_r_or_nl_t instanceof NlToken) {
				tk.skip(0);
			} else if (maybe_r_or_nl_t instanceof IntToken) {
				for (i=0; i < 3; i++) {
					IntToken c_t = new IntToken(0);
					tk.expect(c_t);
					int c = (int) c_t.getValue();
					if (c < 0 || c > 220) {
						throw new WrongFileFormatException("OFFMR: "+c+" is not a colo(u)r");
					}
				}
			} else {
				//throw new WrongFileFormatException("OFFMR: Error encountered on line "+maybe_r_or_nl_t.line()+", expecting NlToken or IntToken, found "+maybe_r_or_nl_t);
			}
			tk.expect(new NlToken());
			if (tk.is_eof()) mode=2;
		}
		if (polys.size()!=num_face) throw new WrongFileFormatException("OFFMR: Expected "+num_face+ " faces, found "+ polys.size());
		return polys;
	}
}
