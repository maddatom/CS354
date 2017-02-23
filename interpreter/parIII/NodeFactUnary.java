/**
 * Created by kanna on 2/23/2017.
 */
public class NodeFactUnary extends NodeFact{
	private String unaryMinus

	public NodeFactUnary(NodeFact f){
		fact = f;
	}

	public int eval(Environment e) throws EvalException{
		return (fact.eval(e)) * - 1;
	}
}
