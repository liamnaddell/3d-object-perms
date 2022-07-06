package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashSet;
import a1_b09.*;

import org.junit.jupiter.api.Test;

import a1_b09.Vertex;

class PolygonTests {

	@Test
	void test_transform() {
		Vertex v1 = new Vertex(1.5,2.5,3.5);
		Vertex v2 = new Vertex(3.5,2.5,1.5);
		Vertex v3 = new Vertex(2.5,2.5,2.5);
		LinkedHashSet<Vertex> ps = new LinkedHashSet<Vertex>();
		ps.add(v1);
		ps.add(v2);
		ps.add(v3);
		Polygon p = new Polygon(ps);
		double [][]arr = {{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		p.transform(arr);
		Vertex b1 = new Vertex(17.0,39.5,62.0);
		Vertex b2 = new Vertex(13.0,35.5,58.0);
		Vertex b3 = new Vertex(15.0,37.5,60.0);
		/*Vertex[]bs = {b1,b2,b3};
		Vertex[]vs = p.vertices.toArray(new Vertex[3]);
		for (int i=0; i<3; i++) {
			assertTrue(vs[i].equals(bs[i]));
		}*/
		LinkedHashSet<Vertex> pss = new LinkedHashSet<Vertex>();
		pss.add(b1);
		pss.add(b2);
		pss.add(b3);
		Polygon b = new Polygon(pss);
		assertTrue(b.equals(p));
	}
}
