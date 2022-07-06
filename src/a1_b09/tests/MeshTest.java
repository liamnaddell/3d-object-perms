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
}
