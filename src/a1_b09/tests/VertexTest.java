package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import a1_b09.*;

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
	@Test
	void test_equal2s() {
		Vertex v = new Vertex(0.0005,2.5,3.5);
		Vertex b = new Vertex(0.00005,2.5,3.5);
		assertFalse(v.hashCode() == b.hashCode());
		assertFalse(v.equals(b));
	}
	@Test
	void test_equals3() {
		Vertex v = new Vertex(2.0,2.0,2.0);
		Vertex b = new Vertex(2.0,2.0,2.0);
		assertTrue(v.hashCode() == b.hashCode());
		assertTrue(v.equals(b));
	}
	@Test
	void test_equals4() {
		Vertex v = new Vertex(2.0,2.0,2.0);
		Vertex b = null;
		assertFalse(v.equals(b));
	}
	@Test
	void test_equals5() {
		Vertex v = new Vertex(2.0,2.0,2.0);
		StrToken b = new StrToken("");
		assertFalse(v.hashCode() == b.hashCode());
		assertFalse(v.equals(b));
	}
}
