package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import a1_b09.*;

import org.junit.jupiter.api.Test;

class MeshWriterTests {

	@Test
	void test_cant_access() {
		String f = "/icantaccess";
		MeshWriter mws[] = {new OBJMeshWriter(),new OFFMeshWriter(),new PLYMeshWriter()};
		Mesh m = new Mesh();
		for (MeshWriter mw : mws) {
			m.setWriter(mw);
			//shouldn't fail
			m.writeToFile(f);
			assertTrue(true);
		}
	}

}
