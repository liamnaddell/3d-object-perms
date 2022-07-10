package a1_b09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;



public class Tokenizer {
	public String[] lines;
	private ArrayList<Token> tkns;
	@Override
	public String toString() {
		this.tokenize();
		String buf = "[";
		for (Token t: this.tokens()) {
			buf=buf.concat("(");
			buf=buf.concat(t.toString());
			buf=buf.concat("), ");
		}
		return buf.concat("]");
	}
	public Tokenizer(String file) {
//		this.lines=Arrays.stream(file.split("\n")).toArray(String[]::new);
		this.lines=file.split("\n");
        this.tkns = new ArrayList<Token>();
	}
	public Tokenizer(Path fname) throws IOException {
        Stream<String> text = Files.lines(fname);
        this.lines=text.toArray(String[]::new);
        this.tkns = new ArrayList<Token>();
        text.close();
	}
	public int length() {
		return this.tkns.size();
	}
	public void skip(int n) throws WrongFileFormatException {
		for (;n>0;n-=1,this.pop());
	}
	private void add_tkn_from_string(String token,int line) {
		Token new_tkn = new StrToken(token,line);
		if (((StrToken) new_tkn).s.equals("")) {
			return;
		}
		
		try {
			int i = Integer.parseInt(token);
			new_tkn = new IntToken(i,line);
		} catch (NumberFormatException e) {
			try {
				float f = Float.parseFloat(token);
				new_tkn = new DoubleToken(f,line);
			} catch (NumberFormatException m) {
			}
		}
		this.tkns.add(new_tkn);
	}
	private void add_nl_tkn(int line) {
		Token new_tkn = new NlToken(line);
		this.tkns.add(new_tkn);
	}
	public void expect(Token tkn) throws WrongFileFormatException {
		if (tkn == null) {
			throw new WrongFileFormatException("Don't be stupid");
		}
		Token actual = this.pop();
		if (actual.getClass() == tkn.getClass()) {
			tkn.setValue(actual.getValue());
			tkn.line=actual.line();
			tkn=actual;
		} else {
			throw new WrongFileFormatException("Expecting "+tkn.getClass()+ " found "+actual+" on line "+actual.line());
		}
		return;
	}
	public Token peek_e(int i) throws WrongFileFormatException {
		if ( this.tkns.size() == i) {
			throw new WrongFileFormatException("Attempt to peek EOF");
		} else {
			return this.tkns.get(i);
		}
	}
	public Boolean is_eof() {
		return this.tkns.size() == 0;
	}
	public Token pop() throws WrongFileFormatException {
		if (this.tkns.size() == 0) {
			throw new WrongFileFormatException("Encountered premature EOF");
		}
		Token toret = this.tkns.get(0);
		this.tkns.remove(0);
		return toret;
	}
	public void tokenize() {
		if (tkns.size() != 0) {
			return;
		}
		String buf = new String();
		int line = 1;
		//re-write to use line info 
		for (String linetext: this.lines) {
			for (char ch: linetext.toCharArray()) {
				if (ch == ' ') {
					this.add_tkn_from_string(buf,line);
					buf = new String();
				} else {
					char[] toappend = new char[1];
					toappend[0] = ch;
					buf = buf.concat(new String(toappend));
				}
			}
			this.add_tkn_from_string(buf,line);
			this.add_nl_tkn(line);
			buf = new String();
			line+=1;
		}
		//this.add_tkn_from_string(buf,line);
		//this.add_nl_tkn(line);
	}
	public ArrayList<Token> tokens() {
		this.tokenize();
		return this.tkns;
	}
}
