/*
 * This class is a recursive-descent parser,
 * modeled after the programming language's grammar.
 * It constructs and has-a Scanner for the program
 * being parsed.
*/
public class Parser {
    private Scanner scanner;

    /*
    * Creates a new scanner for the string to be parsed.
    * Returns the parsed statement
    * Throws SyntaxException if the scanner class throws one.
    * @param program
    */
    public Node parse(String program) throws SyntaxException {
        scanner = new Scanner(program);
        scanner.next();
        return parseBlock();
    }

    private NodeBlock parseBlock() throws SyntaxException {
        // return some kind of NodeBlock
        Token token = scanner.curr();

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
     *
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
