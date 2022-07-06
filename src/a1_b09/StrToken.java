package a1_b09;

import java.util.Objects;

public class StrToken extends Token {
	public String s;
	public int line;
	
	public int line() {
		return this.line;
	}
	public StrToken(String i,int line) {
		this.s = i;
		this.line=line;
	}

	public Object getValue() {
		return s;
	}
	public void setValue(Object v) {
		if (v instanceof String) {
			this.s=(String) v; } }

	public StrToken(String s) {
		super();
		this.line = -1;
		this.s = s;
	}

	@Override
	public int hashCode() {
		return Objects.hash(s);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrToken other = (StrToken) obj;
		return Objects.equals(s, other.s);
	}

	@Override
	public String toString() {
		return "StrToken [s=" + s + " line=" + line + "]";
	}
}