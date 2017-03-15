/**
 * Created by kanna on 3/5/2017.
 */
public class NodeAddop extends Node{
	private String operation;

	public NodeAddop(int pos, String s){
		this.pos = pos;
		operation = s;
	}

	public double op(double numOne, double numTwo) throws EvalException{
		if(operation.equals("+"))
		{
			return (numOne + numTwo);
		}
		if(operation.equals("-"))
		{
			return (numOne - numTwo);
		}
		throw new EvalException(pos, "Invalid operation: " + operation);
	}
}
