/**
 * Created by kanna on 2/23/2017.
 */
public class NodeAddop extends Node{
	private String op;

	public NodeAddop(int position, String op){
		this.op = op;
		pos = position;
	}

	public int op(int numOne, int numTwo) throws EvalException{
		if(op.equals("+"))
		{
			return (numOne + numTwo);
		}
		if(op.equals("-"))
		{
			return (numOne - numTwo);
		}
		throw new EvalException(pos, "invalid addop: " + op);
	}
}
