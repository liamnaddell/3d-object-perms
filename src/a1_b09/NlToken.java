package a1_b09;


public class NlToken extends Token {
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