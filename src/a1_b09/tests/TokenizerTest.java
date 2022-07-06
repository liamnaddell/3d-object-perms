package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import a1_b09.StrToken;
import a1_b09.Token;
import a1_b09.Tokenizer;
import a1_b09.WrongFileFormatException;

class TokenizerTest {

	@Test
	void test_expect_null_tkn() {
		String sample_inp = "OBJ 1 2 3 \n"
				+ "4 5.0 OFF";
		Tokenizer tokenizer = new Tokenizer(sample_inp);
		tokenizer.tokenize();
		Token e = null;
		try {
			tokenizer.expect(e);
			fail("Should fail");
		} catch (WrongFileFormatException f) {
			assertTrue(true);
		}
	}
	@Test
	void test_expect_value() {
		String sample_inp = "OBJ 1 2 3 \n"
				+ "4 5.0 OFF";
		Tokenizer tokenizer = new Tokenizer(sample_inp);
		tokenizer.tokenize();
		Token e = new StrToken("");
		try {
			tokenizer.expect(e);
			assertEquals(e,new StrToken("OBJ",1));
		} catch (WrongFileFormatException f) {
			fail("Shouldn't fail");
		}
	}
	@Test
	void test_skip() {
		String sample_inp = "OBJ 1 2 3 \n"
				+ "4 5.0 OFF";
		Tokenizer tk = new Tokenizer(sample_inp);
		tk.tokenize();
		
		try {
			tk.skip(9);
			assertEquals(tk.length(),0);
		} catch (WrongFileFormatException e) {
			fail("FAILED");
		}
	}
	
}
