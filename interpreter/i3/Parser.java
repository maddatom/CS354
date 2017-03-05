/*
 * This class is a recursive-descent parser,
 * modeled after the programming language's grammar.
 * It constructs and has-a Scanner for the program
 * being parsed.
*/
public class Parser{
	private Scanner scanner;

	/**
	 * CONSTRUCTOR: create a Parser to parse the specified program
	 * @param program program to parse
	 * @return a node containing the parsed statement
	 * @throws SyntaxException
	 */
	public Node parse(String program) throws SyntaxException{
		scanner = new Scanner(program);
		scanner.next();
		return parseBlock();
	}

	private NodeBlock parseBlock() throws SyntaxException{
		NodeStatement statement = parseStatement();
		//		List<NodeStatement> statementList = new LinkedList<>();
		//		while(true){
		//			statement = parseStatement();
		//			statementList.add(statement);
		//
		//			if(scanner.curr().equals(new Token(";"))){
		//				match(";");
		//			}
		//			else
		//				break;
		//		}
		//		return new NodeBlock(statementList);
		if(scanner.curr().equals(new Token(";")))
		{
			match(";");
			NodeBlock blk = parseBlock();
			return new NodeBlock(statement, blk);
		}
		return new NodeBlock(statement);
	}

	private NodeAssn parseAssn() throws SyntaxException{
		Token id = scanner.curr();
		match("id");
		match("=");
		NodeExpr express = parseExpression();
		NodeAssn ass = new NodeAssn(id.lex(), express);
		return ass;
	}

	private NodeExpr parseExpression() throws SyntaxException{
		NodeTerm t = parseTerm();
		NodeAddop add = parseAdditionSubtraction();
		if(add == null)
		{
			return new NodeExpr(t, null, null);
		}
		NodeExpr express = parseExpression();
		express.append(new NodeExpr(t, add, null));
		return express;
	}

	private NodeTerm parseTerm() throws SyntaxException{
		NodeFact f = parseFact();
		NodeMulop mulop = parseMultiplicationDivision();
		if(mulop == null)
		{
			return new NodeTerm(f, null, null);
		}
		NodeTerm t = parseTerm();
		t.append(new NodeTerm(f, mulop, null));
		return t;
	}

	private NodeFact parseFact() throws SyntaxException{
		if(scanner.curr().equals(new Token("(")))
		{
			match("(");
			NodeExpr xp = parseExpression();
			match(")");
			return new NodeFactExpr(xp);
		}
		if(scanner.curr().equals(new Token("id")))
		{
			Token id = scanner.curr();
			match("id");
			return new NodeFactId(scanner.pos(), id.lex());
		}
		NodeNum n = parseDigits();
		match("num");
		return new NodeFactNum(n);
	}

	private NodeStatementWr NodeStatementWr() throws SyntaxException{
		match("wr");
		NodeExpr xp = parseExpression();
		return new NodeStatementWr(xp);
	}

	private NodeNum parseDigits() throws SyntaxException{
		String lex = scanner.curr().lex();
		return new NodeNum(lex);
	}

	private NodeMulop parseMultiplicationDivision() throws SyntaxException{
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

	private NodeAddop parseAdditionSubtraction() throws SyntaxException{
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

	/**
	 * Parse a program's statement
	 * @return the parsed statement
	 * @throws SyntaxException if the Scanner gets an undefined token
	 */
	private NodeStatement parseStatement() throws SyntaxException{
		if(scanner.curr().lex().equals("wr"))
		{
			match("wr");
			NodeExpr xp = parseExpression();
			return new NodeStatementWr(xp);
		}
		return new NodeStatement(parseAssn());
	}

	private void match(String s) throws SyntaxException{
		scanner.match(new Token(s));
	}
}
