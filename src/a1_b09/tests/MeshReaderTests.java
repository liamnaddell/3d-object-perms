package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import a1_b09.*;

class MeshReaderTests {

	@Test
	void test_obj_basic() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		try {
			m.readFromFile("./tests/cube.obj");
			assertEquals(m.polygons.size(),6);
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			fail("Exception encountered");
		}
	}
	@Test
	void test_obj_no_file() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		try {
			m.readFromFile("./where_is_my_wallet.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_obj_bad_vindex() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		try {
			m.readFromFile("./tests/bad_vert_index.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_obj_dbl_in_face() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		try {
			m.readFromFile("./tests/dbl_in_face.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_obj_face_in_vertex() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		try {
			m.readFromFile("./tests/face_in_vertex.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_obj_str_in_coob() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		try {
			m.readFromFile("./tests/imma_vertex.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_off_basic() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./tests/cube.off");
			assertEquals(m.polygons.size(),6);
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			fail("Exception encountered");
		}
	}
	@Test
	void test_off_no_file() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./where_is_my_wallet.off");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_off_bad_vindex() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./tests/bad_vert_index.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_off_dbl_in_face() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./tests/dbl_in_face.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_off_face_in_vertex() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./tests/face_in_vertex.off");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_off_str_in_coob() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./tests/imma_vertex.obj");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_ply_basic() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		try {
			m.readFromFile("./tests/cube.ply");
			assertEquals(m.polygons.size(),6);
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			fail("Exception encountered");
		}
	}
	@Test
	void test_ply_no_file() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		try {
			m.readFromFile("./where_is_my_wallet.off");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_ply_bad_vindex() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		try {
			m.readFromFile("./tests/bad_vert_index.ply");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_ply_dbl_in_face() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		try {
			m.readFromFile("./tests/dbl_in_face.ply");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}

	@Test
	void test_ply_face_in_vertex() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		try {
			m.readFromFile("./tests/face_in_vertex.ply");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			assertTrue(true);
		}
	}

	@Test
	void test_ply_str_in_coob() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		try {
			m.readFromFile("./tests/imma_vertex.ply");
			fail("Should throw exception");
		} catch (WrongFileFormatException e) {
			assertTrue(true);
		}
	}
	//fix small cube to not have duplicate vertices
	@Test
	void test_off_smallshape() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./tests/smallcube.off");
			assertEquals(m.polygons.size(),6);
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			fail("shouldnt fail");
		}
	}
	@Test
	void test_off_color() {
		Mesh m = new Mesh();
		m.setReader(new OFFMeshReader());
		try {
			m.readFromFile("./tests/redcube.off");
			assertEquals(m.polygons.size(),6);
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			fail("shouldnt fail");
		}
	}
	@Test
	void test_cant_access() {
		String f = "/icantaccess";
		MeshReader mws[] = {new OBJMeshReader(),new OFFMeshReader(),new PLYMeshReader()};
		Mesh m = new Mesh();
		for (MeshReader mw : mws) {
			m.setReader(mw);
			//shouldn't fail
			try {
				m.readFromFile(f);
				fail("shoulda failed");
			} catch (WrongFileFormatException e) {
				System.out.println(e.msg);
				assertTrue(true);
			}
		}
	}

}
