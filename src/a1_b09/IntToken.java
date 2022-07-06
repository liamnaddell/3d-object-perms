package a1_b09;

import java.util.Objects;


public class IntToken extends Token {
	public int s;
	public int line;
	
	public int line() {
		return this.line;
	}

	public IntToken(int i) {
		this.line=-1;
		this.s = i;
	}
	public IntToken(int i,int line) {
		this.s = i;
		this.line=line;
	}
	public Object getValue() {
		return s;
	}
	public void setValue(Object v) {
		if (v instanceof Integer) {
			this.s=(int) v;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(s);
	}

	@Override
	public String toString() {
		return "IntToken [s=" + s + " line=" + line + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntToken other = (IntToken) obj;
		return s == other.s;
	}
}
