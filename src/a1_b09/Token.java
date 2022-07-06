package a1_b09;

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