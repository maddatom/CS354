// This class is a scanner for the program
// and programming language being interpreted.

import java.util.HashSet;
import java.util.Set;

public class Scanner{
	private String program; // source program being interpreted
	private int pos; // index of next char in program
	private Token token; // last/current scanned token

	private Set<String> whitespace = new HashSet<>();
	private Set<String> letters = new HashSet<>();
	private Set<String> keywords = new HashSet<>();
	private Set<String> symbols = new HashSet<>();
	private Set<String> digits = new HashSet<>();
	private Set<String> relationalOperators = new HashSet<>();
	private Set<String> lettersDigits = new HashSet<>();

	// constructor:
	// - squirrel-away source program
	// - initialize sets
	public Scanner(String program){
		this.program = program;
		pos = 0;
		token = null;
		initWhitespace(whitespace);
		initLetters(letters);
		initDigits(digits);
		initSymbols(symbols);
		initRelationalOperators(relationalOperators);
		initKeywords(keywords);
		initLettersAndDigits(lettersDigits);
	}

	// uncomment for unit testing
	/*public static void main(String[] args){
		try
		{
			Scanner scanner = new Scanner(args[0]);
			int i = 1;
			while(scanner.next())
			{
				System.out.println(scanner.curr() + " POS: " + i);
				i++;
			}
		} catch(SyntaxException e)
		{
			System.err.println(e);
		}
	}*/

	private void initRelationalOperators(Set<String> set){
		set.add("<");
		set.add(">");
		set.add("<=");
		set.add(">=");
		set.add("<>"); // use to represent not equal (!=)
		set.add("==");
	}

	private void initLettersAndDigits(Set<String> s){
		s.addAll(letters);
		s.addAll(digits);
	}

	private void initDigits(Set<String> digits){
		fill(digits, '0', '9');
		digits.add(".");
	}

	private void initKeywords(Set<String> set){
		set.add("rd");
		set.add("wr");
		set.add("if");
		set.add("then");
		set.add("else");
		set.add("while");
		set.add("do");
		set.add("begin");
		set.add("end");
	}

	private void initSymbols(Set<String> set){
		set.add("=");
		set.add("+");
		set.add("-");
		set.add("*");
		set.add("/");
		set.add("(");
		set.add(")");
		set.add(";");
		set.add(">");
		set.add("<");
	}

	private void initLetters(Set<String> s){
		fill(s, 'A', 'Z');
		fill(s, 'a', 'z');
	}

	private void fill(Set<String> s, char lo, char hi){
		for(char c = lo; c <= hi; c++)
		{
			s.add(c + "");
		}
	}

	private void initWhitespace(Set<String> s){
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	public boolean done(){
		return pos >= program.length();
	}

	private void many(Set<String> s){
		while(! done() && s.contains(program.charAt(pos) + ""))
			pos++;
	}

	private void past(char c){
		while(! done() && c != program.charAt(pos))
			pos++;
		if(! done() && c == program.charAt(pos))
			pos++;
	}

	// This method determines the kind of the next token (e.g., "id"),
	// and calls a method to scan that token's lexeme (e.g., "foo").
	public boolean next(){
		if(done()) return false;
		many(whitespace);
		String c = program.charAt(pos) + "";
		if(letters.contains(c))
		{ // keywords & ID
			nextKwID();
		} else if(symbols.contains(c))
		{ // symbols and relational operators
			nextOperator();
		} else if(digits.contains(c))
		{ // digits
			nextNum();
		} else
		{
			System.err.println("illegal character at position " + pos);
			pos++;
			return next();
		}
		return true;
	}

	private void nextOperator(){
		int old = pos;
		pos = (old + 2);
		if(! (done()))
		{
			String lexeme = program.substring(old, pos);
			// short-circuit -> lexeme can either be a symbol or relational operator
			// but never both
			if(symbols.contains(lexeme) || relationalOperators.contains(lexeme))
			{
				token = new Token(lexeme);
				return;
			}
		}
		pos = (old + 1);
		String lexeme = program.substring(old, pos);
		token = new Token(lexeme);
	}

	private void nextNum(){
		int old = pos;
		many(digits);
		token = new Token("num", program.substring(old, pos));
	}

	private void nextKwID(){
		int old = pos;
		many(letters);
		many(lettersDigits);
		String lexeme = program.substring(old, pos);
		token = new Token((keywords.contains(lexeme) ? lexeme : "id"), lexeme);
	}

	// This method scans the next lexeme,
	// if the current token is the expected token.
	public void match(Token t) throws SyntaxException{
		if(! t.equals(curr()))
			throw new SyntaxException(pos, t, curr());
		next();
	}

	public Token curr() throws SyntaxException{
		if(token == null)
			throw new SyntaxException(pos, new Token("ANY"), new Token("EMPTY"));
		return token;
	}

	public int pos(){
		return pos;
	}
}
