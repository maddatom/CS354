/*
 * This class is a recursive-descent parser,
 * modeled after the programming language's grammar.
 * It constructs and has-a Scanner for the program
 * being parsed.
*/
public class Parser {
    private Scanner scanner;

    /**
     * CONSTRUCTOR: create a Parser to parse the specified program
     * @param program program to parse
     * @return a node containing the parsed statement
     * @throws SyntaxException
     */
    public Node parse(String program) throws SyntaxException {
        scanner = new Scanner(program);
        scanner.next();
        return parseBlock();
    }

    private NodeBlock parseBlock() throws SyntaxException {
        NodeStatement statement = parseStatement();
        if (scanner.curr().equals(new Token(";"))) {
            match(";");
            NodeBlock blk = parseBlock();
            return new NodeBlock(statement, blk);
        }
        return new NodeBlock(statement);
    }

    private NodeAssn parseAssn() throws SyntaxException {
        Token id = scanner.curr();
        match("id");
        match("=");

        Token num = scanner.curr();

        match("num");

        return new NodeAssn(id.lex(), Integer.parseInt(num.lex()));
    }

    /**
     * Parse a program's statement
     * @return the parsed statement
     * @throws SyntaxException if the Scanner gets an undefined token
     */
    private NodeStatement parseStatement() throws SyntaxException {
        return new NodeStatement(parseAssn());
    }

    private void match(String s) throws SyntaxException {
        scanner.match(new Token(s));
    }
}