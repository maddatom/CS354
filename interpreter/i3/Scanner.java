// This class is a scanner for the program
// and programming language being interpreted.

import java.util.HashSet;
import java.util.Set;

public class Scanner{
	private String program; // source program being interpreted
	private int pos; // index of next char in program
	private Token token; // current scanned token

	private Set<String> whitespace = new HashSet<>();
	private Set<String> digits = new HashSet<>();
	private Set<String> letters = new HashSet<>();
	private Set<String> keywords = new HashSet<>();
	//this also doubles as my symbols table
	private Set<String> operators = new HashSet<>();
	private Set<String> letterDigits = new HashSet<>();
	/*private Set<String> comments = new HashSet<>();*/

	// constructor:
	// - squirrel-away source program
	// - initialize sets
	public Scanner(String program){
		this.program = program;
		pos = 0;
		token = null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLetterDigits(letterDigits);
		initKeywords(keywords);
		initOperators(operators);
	}

	private void initLetterDigits(Set<String> letterDigits){
		letterDigits.addAll(letters);
		letterDigits.addAll(digits);
	}

	private void initDigits(Set<String> set){
		set.add(".");
		fill(set, '0', '9');
	}

	private void initOperators(Set<String> operators){
		operators.add("+");
		operators.add("/");
		operators.add("*");
		operators.add("-");
		operators.add("=");
		operators.add(";");
		operators.add("(");
		operators.add(")");
	}

	private void initKeywords(Set<String> keywords){
		keywords.add("wr");
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
		if(done())
			return false;
		many(whitespace);
		String c = program.charAt(pos) + "";
		if(letters.contains(c))
		{ // letters and keywords
			nextKwID();
		} else if(digits.contains(c))
		{ // digits
			nextNum();
		} else if(operators.contains(c))
		{
			// operators
			nextOperator();
		} else
		{
			System.err.println("illegal character at position " + pos);
			pos++;
			return next();
		}
		return true;
	}

	private void nextNum(){
		String lexeme = "";
		try
		{
			while(Character.isDigit(program.charAt(pos)))
			{
				lexeme += program.charAt(pos);
				pos++;
			}
		} catch(Exception e)
		{
		}
		token = new Token("num", lexeme);
		//		int old = pos;
		//		many(digits);
		//		token = new Token("num", program.substring(old, pos));
	}

	private void nextOperator(){
		int old = pos;
		pos = old + 2;
		// double char operator
		if(! done())
		{
			String lex = program.substring(old, pos);
			if(operators.contains(lex))
			{
				token = new Token(lex);
				return;
			}
		}
		// single char operator
		pos = old + 1;
		String lex = program.substring(old, pos);
		token = new Token(lex);
	}

	private void nextKwID(){
		int old = pos;
		many(letters);
		many(letterDigits);
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
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(args[0]);
			while (scanner.next())
				System.out.println(scanner.curr());
		} catch (SyntaxException e) {
			System.err.println(e);
		}
	}
}
