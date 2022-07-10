package a1_b09;

//import java.io.IOException;
//import java.nio.file.Paths;

/*public class Main {

	public static void main(String[] args) throws Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.setWriter(new OFFMeshWriter());
		try {
			mesh.readFromFile("./tests/cube.obj");
			double arr[][] ={{0.1,0,0},{0,0.1,0},{0,0,0.1}};
			mesh.transform(arr);
			mesh.writeToFile("./tests/smallcube.off");
		} catch (WrongFileFormatException e) {
			System.out.println(e.msg);
			System.exit(1);
		}
		//System.out.println(mesh);
		//mesh.rotateZAxis(Math.PI);
		//double[][] arr = {{1.0,0.0,0.0},{0.0,0.4,0.0},{0.0,0.0,1.0}};
		//mesh.transform(arr);
		//System.out.println(mesh);
		//mesh.setWriter(new OFFMeshWriter());
		//mesh.writeToFile("./carfromobj.off");
	}
}*/