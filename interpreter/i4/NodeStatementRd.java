/**
 * Created by kanna on 3/14/2017.
 */

import java.util.Scanner;

public class NodeStatementRd extends NodeStatement{
	Double value;
	private String lexeme;

	public NodeStatementRd(String lex){
		lexeme = lex;
	}

	public Double eval(Environment e) throws EvalException{
		while(value == null)
		{
			setValue();
		}
		return e.put(lexeme, value);
	}

	private void setValue(){
		Scanner scan = new Scanner(System.in);
		try
		{
			System.out.print("Enter value for fdd" + lexeme + ": ");
			value = Double.parseDouble(scan.nextLine());
		} catch(NumberFormatException e)
		{
			System.err.print("The value needs to be a double\n");

		}
	}
}
