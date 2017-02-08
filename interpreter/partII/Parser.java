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
        //return parseStmt();
        return null;
    }

}