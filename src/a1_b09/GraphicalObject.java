package a1_b09;

public abstract class GraphicalObject {
	abstract void transform(double[][] arr);
	public void rotateXAxis(double th) {
		/*
		 * [ 1   0        0]
		 * [ 0 cos(th) -sin(th) ]
		 * [ 0 sin(th) cos(th) ]
		 */
		double[][] arr = {{1.0,0.0,0.0},{0.0,Math.cos(th),-Math.sin(th)},{0.0,Math.sin(th),Math.cos(th)}};
		this.transform(arr);
		
	}
	public void rotateYAxis(double th) {
		/* 
		 * [ cos th 0 sin th ]
		 * [ 0 1 0 ]
		 * [ -sin th 0 cos th ] 
		 */
		//wrong
		double[][] arr = {{Math.cos(th),0.0,Math.sin(th)},{0.0,1.0,0.0},{-Math.sin(th),0.0,Math.cos(th)}};
		this.transform(arr);
	}
	public void rotateZAxis(double th) {
		/* 
		 * [cos th -sin th 0 ]
		 * [ sin th cos th 0 ]
		 * [0 0 1 ]
		 */
		double[][] arr = {{Math.cos(th),-Math.sin(th),0.0},{Math.sin(th),Math.cos(th),0.0},{0.0,0.0,1.0}};
		this.transform(arr);
	}
}