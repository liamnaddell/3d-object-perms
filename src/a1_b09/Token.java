package a1_b09;

import java.util.Objects;

//must have toString, Eq
public abstract class Token {
	public abstract Object getValue();
	public abstract void setValue(Object v);
	public abstract int line();
	int line;
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract String toString();
}
//REDO ALL hashcode,eq,tostring for all tokens!

class StrToken extends Token {
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

class IntToken extends Token {
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

class NlToken extends Token {
	public int line;
	
	public NlToken(int line) {
		this.line=line;
	}
	public int line() {
		return this.line;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	public Object getValue() {
		return "\n";
	} 
	public void setValue(Object v) {
		return;
	}

	@Override
	public String toString() {
		return "NlToken [line=" + line + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	public NlToken() {
		this.line=-1;
	}
}