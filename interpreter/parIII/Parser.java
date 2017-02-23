/*
 * This class is a recursive-descent parser,
 * modeled after the programming language's grammar.
 * It constructs and has-a Scanner for the program
 * being parsed.
*/
public class Parser{
	private Scanner scanner;

	public static void main(String[] args){
		try
		{
			//			Scanner scanner = new Scanner(args[0]);
			Parser p = new Parser();
			System.out.println(args[0]);
			for(String s :
					args)
			{

				System.out.println(p.parse(s));
			}
			//			while(scanner.next())

		} catch(SyntaxException e)
		{
			System.err.println(e);
		}
	}

	/**
	 * CONSTRUCTOR: create a Parser to parse the specified program
	 * @param program program to parse
	 * @return a node containing the parsed statement
	 * @throws SyntaxException
	 */
	public Node parse(String program) throws SyntaxException{
		scanner = new Scanner(program);
		scanner.next();
		return parseStatement();
		//		return parseBlock();
	}

	private NodeBlock parseBlock() throws SyntaxException{
		NodeStatement statement = parseStatement();
		if(scanner.curr().equals(new Token(";")))
		{
			match(";");
			NodeBlock blk = parseBlock();
			return new NodeBlock(statement, blk);
		}
		return new NodeBlock(statement);
	}

	private NodeStatementWr parseWr() throws SyntaxException{
		match("wr");
		NodeExpr expr = parseExpression();
		return new NodeStatementWr(expr);
	}

	private NodeAssn parseAssn() throws SyntaxException{
		Token id = scanner.curr();
		match("id");
		match("=");

		NodeExpr e = parseExpression();
		NodeAssn ass = new NodeAssn(id.lex(), e);
		return new NodeAssn(id.lex(), e);
	}

	private NodeMulop parseMultiplication() throws SyntaxException{
		if(scanner.curr().equals(new Token("*")))
		{
			match("*");
			return new NodeMulop(scanner.pos(), "*");
		}
		if(scanner.curr().equals(new Token("/")))
		{
			match("/");
			return new NodeMulop(scanner.pos(), "/");
		}
		return null;
	}

	private NodeAddop parseAddition() throws SyntaxException{
		if(scanner.curr().equals(new Token("+")))
		{
			match("+");
			return new NodeAddop(scanner.pos(), "+");
		}
		if(scanner.curr().equals(new Token("-")))
		{
			match("-");
			return new NodeAddop(scanner.pos(), "-");
		}
		return null;
	}

	private NodeExpr parseExpression() throws SyntaxException{
		NodeTerm term = parseTerm();
		NodeAddop addOP = parseAddition();
		if(addOP == null)
		{
			return new NodeExpr(term, null, null);
		}
		NodeExpr expression = parseExpression();
		expression.append(new NodeExpr(term, addOP, null));
		return expression;
	}

	private NodeTerm parseTerm() throws SyntaxException{
		NodeFact nodeFact = parseFact();
		NodeMulop mulOP = parseMultiplication();
		if(mulOP == null)
		{
			return new NodeTerm(nodeFact, null, null);
		}
		NodeTerm nodeTerm = parseTerm();
		nodeTerm.append(new NodeTerm(nodeFact, mulOP, null));
		return nodeTerm;
	}

	private NodeFact parseFact() throws SyntaxException{
		if(scanner.curr().equals(new Token("(")))
		{
			match("(");
			NodeExpr expression = parseExpression();
			match(")");
			return new NodeFactExpr(expression);
		}
		if(scanner.curr().equals(new Token("id")))
		{
			Token id = scanner.curr();
			match("id");
			return new NodeFactId(scanner.pos(), id.lex());
		}
		if(scanner.curr().equals(new Token("-")))
		{
			match("-");
			NodeFact f = parseFact();
			return new NodeFactUnary(f);
		}
		Token num = scanner.curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	/**
	 * Parse a program's statement
	 * @return the parsed statement
	 * @throws SyntaxException if the Scanner gets an undefined token
	 */
	private NodeStatement parseStatement() throws SyntaxException{
		if(scanner.curr().equals(new Token("wr")))
		{
			return parseWr();
		} else
		{
			return parseAssn();
		}
		//		return new NodeStatement(parseAssn());
	}

	private void match(String s) throws SyntaxException{
		scanner.match(new Token(s));
	}
}
