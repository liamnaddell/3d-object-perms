package a1_b09.tests;

import static org.junit.jupiter.api.Assertions.*;
import a1_b09.*;

import org.junit.jupiter.api.Test;

class TokenTests {

	@Test
	void test_dbl() {
		DoubleToken t = new DoubleToken(1.0);
		DoubleToken v = new DoubleToken(1.0,100);
		var s = new IntToken(1,100);
		assertTrue(!t.equals(null));
		assertEquals(t.hashCode(),v.hashCode());
		assertTrue(!t.equals(s));
		assertEquals(t,v);
		v.setValue(1010.0);
		v.setValue("abc");
		assertTrue(!t.equals(v));
	}
	@Test
	void test_int() {
		IntToken t = new IntToken(1);
		IntToken v = new IntToken(1,100);
		var s = new DoubleToken(10,100);
		assertTrue(!t.equals(null));
		assertEquals(t.hashCode(),v.hashCode());
		assertTrue(!t.equals(s));
		assertEquals(t,v);
		v.setValue(1010);
		v.setValue("abc");
		assertTrue(!t.equals(v));
		assertTrue(v+"" instanceof String);
	}
	@Test
	void test_nl() {
		NlToken t = new NlToken(1);
		NlToken v = new NlToken(100);
		var s = new DoubleToken(10,100);
		assertTrue(!t.equals(null));
		assertEquals(t.hashCode(),v.hashCode());
		assertTrue(!t.equals(s));
		assertEquals(t,v);
		assertEquals(v.getValue(),"\n");
		v.setValue("abc");
		assertTrue(v+"" instanceof String);
	}
	@Test
	void test_str() {
		StrToken t = new StrToken("a");
		StrToken v = new StrToken("a",100);
		var s = new DoubleToken(10,100);
		assertTrue(!t.equals(null));
		assertEquals(t.hashCode(),v.hashCode());
		assertTrue(!t.equals(s));
		assertEquals(t,v);
		v.setValue("b");
		v.setValue(101.0);
		assertTrue(!t.equals(v));
	}

}
