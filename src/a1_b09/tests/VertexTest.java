package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import a1_b09.Vertex;

class VertexTest {

	@Test
	void test_v_transform() {
		Vertex v = new Vertex(1.5,2.5,3.5);
		double [][]arr = {{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		v.transform(arr);
		assertEquals(v.x, 17.0);
		assertEquals(v.y, 39.5);
		assertEquals(v.z, 62.0);
	}
	@Test
	void test_equals() {
		Vertex v = new Vertex(1.5,2.5,3.5);
		Vertex b = new Vertex(1.5,2.5,3.5);
		assertEquals(v.hashCode(),b.hashCode());
		assertEquals(v,b);
	}
}
