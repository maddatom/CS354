/**
 * Created by kanna on 3/14/2017.
 */
public class NodeUnary extends Node{
	private String negative;

	public NodeUnary(String s){
		negative = s;
	}

	public Double eval(Environment e) throws EvalException{
		return e.put(negative, pos);
	}
}
