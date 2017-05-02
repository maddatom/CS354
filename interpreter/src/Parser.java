// (C) 2013 Jim Buffenbarger
// All rights reserved.

// prog     : block
// block    : stmt ';' block
//          | stmt
// stmt     : assn
//          | 'rd' id
//          | 'wr' expr
//          | 'if' boolexpr 'then' stmt
//          | 'if' boolexpr 'then' stmt 'else' stmt
//          | 'while' boolexpr 'do' stmt
//          | 'begin' block 'end'
// assn     : id '=' expr
// expr     : term addop expr
//          | term
// term     : fact mulop term
//          | fact
// fact     : id
//          | num
//          | '(' expr ')'
//          | '-' fact
// boolexpr : expr relop expr
// addop    : '+'
//          | '-'
// mulop    : '*'
//          | '/'
// relop    : '<'
//          | '<='
//          | '>'
//          | '>='
//          | '<>'
//          | '=='

public class Parser{

	private Scanner scanner;

	private void match(String s) throws SyntaxException{
		scanner.match(new Token(s));
	}

	private Token curr() throws SyntaxException{
		return scanner.curr();
	}

	private int pos(){
		return scanner.pos();
	}

	private NodeMulop parseMulop() throws SyntaxException{
		if(curr().equals(new Token("*")))
		{
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if(curr().equals(new Token("/")))
		{
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}

	private NodeRelop parseRelop() throws SyntaxException{
		if(curr().equals(new Token("<")))
		{
			match("<");
			return new NodeRelop(pos(), "<");
		}
		if(curr().equals(new Token("<=")))
		{
			match("<=");
			return new NodeRelop(pos(), "<=");
		}
		if(curr().equals(new Token(">")))
		{
			match(">");
			return new NodeRelop(pos(), ">");
		}
		if(curr().equals(new Token(">=")))
		{
			match(">=");
			return new NodeRelop(pos(), ">=");
		}
		if(curr().equals(new Token("<>")))
		{
			match("<>");
			return new NodeRelop(pos(), "<>");
		}
		if(curr().equals(new Token("==")))
		{
			match("==");
			return new NodeRelop(pos(), "==");
		}
		return null;
	}

	private NodeAddop parseAddop() throws SyntaxException{
		if(curr().equals(new Token("+")))
		{
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if(curr().equals(new Token("-")))
		{
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}

	private NodeBoolExpr parseBoolExpr() throws SyntaxException{
		NodeExpr exprl = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr exprr = parseExpr();
		NodeBoolExpr boolexpr = new NodeBoolExpr(exprl, relop, exprr);
		return boolexpr;
	}

	private NodeFact parseFact() throws SyntaxException{
		if(curr().equals(new Token("(")))
		{
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if(curr().equals(new Token("-")))
		{
			match("-");
			NodeFact fact = parseFact();
			return new NodeFactFact(fact);
		}
		if(curr().equals(new Token("id")))
		{
			Token id = curr();
			match("id");

			if(curr().equals(new Token("(")))
			{
				// id '(' expr ')'
				NodeExpr expr = parseExpr();
				return new NodeFuncCall(pos(), id.lex(), expr);
			} else
				return new NodeFactId(pos(), id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	private NodeTerm parseTerm() throws SyntaxException{
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if(mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	private NodeExpr parseExpr() throws SyntaxException{
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if(addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	private NodeAssn parseAssn() throws SyntaxException{
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
	}

	private NodeRd parseRd() throws SyntaxException{
		match("rd");
		Token id = curr();
		match("id");
		NodeRd rd = new NodeRd(id.lex());
		return rd;
	}

	private NodeWr parseWr() throws SyntaxException{
		match("wr");
		NodeExpr expr = parseExpr();
		NodeWr wr = new NodeWr(expr);
		return wr;
	}

	private NodeIfElse parseIfElse() throws SyntaxException{
		match("if");
		NodeBoolExpr boolexpr = parseBoolExpr();
		match("then");
		NodeStmt thenstmt = parseStmt();
		NodeStmt elsestmt = null;
		if(curr().equals(new Token("else")))
		{
			match("else");
			elsestmt = parseStmt();
		}
		NodeIfElse ifelse = new NodeIfElse(boolexpr, thenstmt, elsestmt);
		return ifelse;
	}

	private NodeWhileDo parseWhileDo() throws SyntaxException{
		match("while");
		NodeBoolExpr boolexpr = parseBoolExpr();
		match("do");
		NodeStmt stmt = parseStmt();
		NodeWhileDo whiledo = new NodeWhileDo(boolexpr, stmt);
		return whiledo;
	}

	private NodeBegin parseBegin() throws SyntaxException{
		match("begin");
		NodeBlock block = parseBlock();
		match("end");
		NodeBegin begin = new NodeBegin(block);
		return begin;
	}

	private NodeStmt parseStmt() throws SyntaxException{

		if(curr().equals(new Token("id")))
		{
			NodeAssn assn = parseAssn();
			return new NodeStmtAssn(assn);
		}
		if(curr().equals(new Token("rd")))
		{
			NodeRd rd = parseRd();
			return new NodeStmtRd(rd);
		}
		if(curr().equals(new Token("wr")))
		{
			NodeWr wr = parseWr();
			return new NodeStmtWr(wr);
		}
		if(curr().equals(new Token("if")))
		{
			NodeIfElse ifelse = parseIfElse();
			return new NodeStmtIfElse(ifelse);
		}
		if(curr().equals(new Token("while")))
		{
			NodeWhileDo whiledo = parseWhileDo();
			return new NodeStmtWhileDo(whiledo);
		}

		if(curr().equals(new Token("def")))
		{
			return parseFunctionDecl();
		}
		NodeBegin begin = parseBegin();
		return new NodeStmtBegin(begin);
	}

	private NodeFuncDecl parseFunctionDecl() throws SyntaxException{
		// 'def' id '(' id ')' = expr
		match("def");
		String name = curr().lex();
		match("id");
		match("(");
		String parameter = curr().lex();
		match("id");
		match(")");
		match("=");
		NodeExpr expr = parseExpr();
		return new NodeFuncDecl(name, expr, parameter);
	}

	private NodeBlock parseBlock() throws SyntaxException{
		NodeStmt stmt = parseStmt();
		NodeBlock rest = null;
		if(curr().equals(new Token(";")))
		{
			match(";");
			rest = parseBlock();
		}
		NodeBlock block = new NodeBlock(stmt, rest);
		return block;
	}

	private NodeProg parseProg() throws SyntaxException{
		NodeBlock block = parseBlock();
		if(!scanner.done())
			throw new SyntaxException(pos(), new Token("EOF"), curr());
		NodeProg prog = new NodeProg(block);
		return prog;
	}

	public Node parse(String program) throws SyntaxException{
		scanner = new Scanner(program);
		scanner.next();
		return parseProg();
	}

}
