package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import a1_b09.*;

import org.junit.jupiter.api.Test;

class ProfTests {

	@Test
	void test_should_fail() {
		File dir = new File("./prof_tests/should_fail");
		for (File f: dir.listFiles()) {
			Mesh m = new Mesh();
			String fname = f.getName();
			if (fname.contains("obj")) {
				m.setReader(new OBJMeshReader());
			} else if (fname.contains("off")) {
				m.setReader(new OFFMeshReader());
			} else if (fname.contains("ply")) {
				m.setReader(new PLYMeshReader());
			}
			try {
				System.out.println("Trying file: "+fname);
				m.readFromFile("./prof_tests/should_fail/"+fname);
				fail("Should have failed");
			} catch (WrongFileFormatException e) {
				System.out.println(e.msg);
				assertTrue(true);
			}
		}
	}
	@Test
	void test_should_work() {
		File dir = new File("./prof_tests/should_work");
		for (File f: dir.listFiles()) {
			Mesh m = new Mesh();
			String fname = f.getName();
			if (fname.contains("obj")) {
				m.setReader(new OBJMeshReader());
			} else if (fname.contains("off")) {
				m.setReader(new OFFMeshReader());
			} else if (fname.contains("ply")) {
				m.setReader(new PLYMeshReader());
			}
			try {
				System.out.println("Trying file: "+fname);
				m.readFromFile("./prof_tests/should_work/"+fname);
				assertTrue(true);
			} catch (WrongFileFormatException e) {
				System.out.println(e.msg);
				fail("Should not have failed");
			}
		}
	}

}
