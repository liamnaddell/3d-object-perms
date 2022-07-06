package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.HashSet;

import a1_b09.*;

import org.junit.jupiter.api.Test;

class MeshTest {

	@Test
	void test_mesh_format_equality() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.setWriter(new PLYMeshWriter());
		//double [][]arr = {{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		try {
			m.readFromFile("tests/cube.obj");
			//m.transform(arr);
			m.writeToFile("out/cube.ply");
			Mesh m2 = new Mesh();
			m2.setReader(new PLYMeshReader());
			m2.readFromFile("out/cube.ply");
			assertTrue(m.equals(m2));
			System.out.println(m);
			System.out.println(m2);
		} catch (WrongFileFormatException e) {
			fail(e+"");
		}
	}

	@Test
	void test_mesh_transform_equality() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.setWriter(new OBJMeshWriter());
		try {
			m.readFromFile("tests/triangle.ply");
			m.rotateXAxis(2*Math.PI);
			m.rotateYAxis(2*Math.PI);
			m.rotateZAxis(2*Math.PI);
			m.writeToFile("out/triangle.obj");
			Mesh m2 = new Mesh();
			m2.setReader(new OBJMeshReader());
			m2.readFromFile("out/triangle.obj");

			System.out.println(m);
			System.out.println(m2);
			//assertEquals(m.hashCode(),m2.hashCode());
			
			assertEquals(m,m2);
		} catch (WrongFileFormatException e) {
			fail(e.msg+"");
		}
	}
	@Test
	void test_mesh_transform_equality2() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.setWriter(new OBJMeshWriter());
		//double [][]arr= {{1,2,3},{0,1,4},{5,6,0}};
		double [][]arr= {{2,0,0},{0,2,0},{0,0,2}};
		try {
			m.readFromFile("tests/triangle.ply");
			m.transform(arr);
			m.writeToFile("out/triangle.obj");
			Mesh m2 = new Mesh();
			m2.setReader(new OBJMeshReader());
			m2.readFromFile("out/triangle.obj");

			assertEquals(m.hashCode(),m2.hashCode());
			System.out.println(m);
			System.out.println(m2);

			assertEquals(m,m2);
		} catch (WrongFileFormatException e) {
			fail(e.msg+"");
		}
	}
	@Test
	void test_vtest() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		try {
			m.readFromFile("tests/vtest.obj");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
		}
		
		double [][]arr = {{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		m.transform(arr);
		
		Vertex b1 = new Vertex(17.0,39.5,62.0);
		Vertex b2 = new Vertex(13.0,35.5,58.0);
		Vertex b3 = new Vertex(15.0,37.5,60.0);
		LinkedHashSet<Vertex> pss = new LinkedHashSet<Vertex>();
		pss.add(b1);
		pss.add(b2);
		pss.add(b3);
		Polygon b = new Polygon(pss);
		HashSet<Polygon> bb = new HashSet<Polygon>();
		bb.add(b);
		Mesh m2 = new Mesh();
		m2.polygons=bb;
	}
	
	@Test
	void test_off_mesh_writer_car() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("car.obj");
			m.rotateXAxis(4*Math.PI);
			m.writeToFile("./out/car.off");
			Mesh m2 = new Mesh();
			m2.setReader(new OFFMeshReader());
			m2.readFromFile("out/car.off");
			assertTrue(m.equals(m2));
			assertEquals(m.hashCode(),m2.hashCode());
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			fail("I failed xd");
		}
	}
	@Test
	void test_off_rgb_bad() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/rgbad.off");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_off_int_in_vertex() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/intvertex.off");
			assertTrue(m.polygons.size() == 6);
		} catch (WrongFileFormatException e) {
			fail("should have thrown exception");
		}
	}
	@Test
	void test_off_strvertex() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/strvertex.off");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_ply_strvertex() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/strvertex.ply");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_ply_badvertindex() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/badvindex.ply");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_ply_badfaceno() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/badfaceno.ply");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_ply_badvertno() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/badvertno.ply");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_no_file() {
		Mesh m = new Mesh();
		try {
			m.setReader(new OBJMeshReader());
			m.readFromFile("IDONTEXIST");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
		try {
			m.setReader(new OFFMeshReader());
			m.readFromFile("IDONTEXIST");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
		try {
			m.setReader(new PLYMeshReader());
			m.readFromFile("IDONTEXIST");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_off_badvertno() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/badvertno.off");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			assertTrue(true);
		}
	}
	@Test
	void test_off_bad_vindex() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/badvindex.off");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			assertTrue(true);
		}
	}

	@Test
	void test_off_bad_faceno() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new OFFMeshWriter());
		try {
			m.readFromFile("tests/badfaceno.off");
			fail("should have thrown exception");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			assertTrue(true);
		}
	}
	@Test
	void test_mesh_eq() {
		
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new OFFMeshWriter());
		Mesh m2 = new Mesh();
		m2.setReader(new OFFMeshReader());
		m2.setWriter(new OFFMeshWriter());

		try {
			m2.readFromFile("car.off");
			m.readFromFile("tests/cube.off");
			assertFalse(m.equals(null));
			assertFalse(m.equals(0));
			assertFalse(m.equals(m2));
			assertFalse(m2.equals(m));
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			fail("shouldn't have thrown exception");
		}
	}
}
