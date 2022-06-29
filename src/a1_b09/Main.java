package a1_b09;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		Tokenizer tk = new Tokenizer(Paths.get("penis.ply"));
		System.out.println(tk);
	}
}
