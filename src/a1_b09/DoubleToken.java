package a1_b09;

import java.util.Objects;

class DoubleToken extends Token {
	public double s;
	public int line;

	public DoubleToken(double i,int line) {
		this.s = i;
		this.line=line;
	}
	
	public int line() {
		return this.line;
	}

	public DoubleToken(double s) {
		super();
		this.line=-1;
		this.s = s;
	}
	public Object getValue() {
		return s;
	}
	public void setValue(Object v) {
		if (v instanceof Double) {
			this.s=(double) v;
		}
	}

	@Override
	public String toString() {
		return "DoubleToken [s=" + s + " line=" + line + "]";
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
		DoubleToken other = (DoubleToken) obj;
		return Double.doubleToLongBits(s) == Double.doubleToLongBits(other.s);
	}

}