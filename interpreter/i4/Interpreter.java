/*
* This is the main class/method for the interpreter.
* Each command-line argument is a complete program,
* which is scanned, parsed, and evaluated.
* All evaluations share the same environment,
* so they can share variables.
*/
public class Interpreter{

	public static void main(String[] args) throws SyntaxException, EvalException{
		Parser parser = new Parser();
		Environment env = new Environment();
		/*for(String prog : args)
		{
//			try
//			{
				Node node = parser.parse(prog);
				Double evaluation;
				System.out.print(node == null ? "" : (evaluation = node.eval(env)) == null ? "" : evaluation + "\n");
//			} catch(EvalException | SyntaxException e)
			//			{
			//				System.err.println(e);
			//			}
		}*/

		for(String stmt : args)
		{
			Node n = parser.parse(stmt);
			Double result;
			if(n != null)
			{
				result = n.eval(env);
				if(result != null)
				{
					System.out.println(result);
				}
			}
		}
	}
}
