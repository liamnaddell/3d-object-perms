package a1_b09;

@SuppressWarnings("serial")
public class WrongFileFormatException extends Exception {
	String msg;
	public WrongFileFormatException(String msg) {
		this.msg=msg;
	}
}
