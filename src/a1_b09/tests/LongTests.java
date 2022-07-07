package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import a1_b09.*;

class LongTests {
	@Test
	void test_off_mesh_writer_car() throws WrongFileFormatException {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.setWriter(new OFFMeshWriter());
		m.readFromFile("car.obj");
		m.writeToFile("./out/car.off");
		Mesh m2 = new Mesh();
		m2.setReader(new OFFMeshReader());
		m2.readFromFile("out/car.off");
		assertTrue(m.equals(m2));
		assertEquals(m.hashCode(),m2.hashCode());
	}
	@Test
	void test_off_mesh_reader_car() throws WrongFileFormatException {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		m.setWriter(new PLYMeshWriter());
		m.readFromFile("car.off");
		m.writeToFile("./out/car.ply");
		Mesh m2 = new Mesh();
		m2.setReader(new PLYMeshReader());
		m2.readFromFile("out/car.ply");
		assertTrue(m.equals(m2));
		assertEquals(m.hashCode(),m2.hashCode());
	}
	@Test
	void test_ply_mesh_reader_car() throws WrongFileFormatException {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.setWriter(new OFFMeshWriter());
		m.readFromFile("car.ply");
		m.writeToFile("./out/car.off");
		Mesh m2 = new Mesh();
		m2.setReader(new OFFMeshReader());
		m2.readFromFile("out/car.off");
		assertTrue(m.equals(m2));
		assertEquals(m.hashCode(),m2.hashCode());
	}
//use objmeshwriter,plymeshwriter

}
