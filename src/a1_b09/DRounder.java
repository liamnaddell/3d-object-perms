package a1_b09;

public class DRounder {
	/*public double round(double d) {
		double zeps = 0.00001;
		double eps = Math.pow(10,6);
		if (Math.abs(d-zeps) < zeps) {
			return 0.0;
		} 
		return Math.rint(d*eps)/eps;
	}*/
	public static String round(double d) {
		//double eps = Math.pow(10,6);
		//double rounded=Math.rint(d*eps)/eps;
		return String.format("%.6f",d);
	}
}
