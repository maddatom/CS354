/**
 * Created by kanna on 3/5/2017.
 */
public class NodeNum extends Node{
	private String digit;
	private NodeUnary unary;

	public NodeNum(String digit){
		this.digit = digit;
	}

	public Double eval(Environment env) throws EvalException{
		return Double.parseDouble(unary == null ? digit : "-" + digit);
	}

	public void setUnary(NodeUnary unary){
		this.unary = unary;
	}
}
