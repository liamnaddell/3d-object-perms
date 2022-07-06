package a1_b09;

//import java.io.IOException;
//import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		try {
			mesh.readFromFile("./cube.obj");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			System.exit(1);
		}
		//System.out.println(mesh);
		//mesh.rotateZAxis(Math.PI);
		//double[][] arr = {{1.0,0.0,0.0},{0.0,0.4,0.0},{0.0,0.0,1.0}};
		//mesh.transform(arr);
		//System.out.println(mesh);
		mesh.setWriter(new OFFMeshWriter());
		mesh.writeToFile("./carfromobj.off");
	}
}
