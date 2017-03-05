/**
 * Created by kanna on 3/5/2017.
 */
public class NodeNum extends Node{
	private String digit;

	public NodeNum(String digit){
		this.digit = digit;
	}

	public int eval(Environment env) throws EvalException{
		return Integer.parseInt(digit);
	}
}
