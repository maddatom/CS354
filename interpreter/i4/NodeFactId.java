/**
 * Created by kanna on 3/5/2017.
 */
public class NodeFactId extends NodeFact{
	private static final double NEGATIVE_ONE = (- 1);
	private String id;
	private NodeUnary unary;

	public NodeFactId(int pos, String lex){
		this.pos = pos;
		id = lex;
	}

	public NodeFactId(int pos, String lex, NodeUnary unary){
		this.pos = pos;
		id = lex;
		this.unary = unary;

	}

	public Double eval(Environment e) throws EvalException{
		if(unary == null)
		{
			return e.get(pos, id);
		}
		return e.get(pos, id) * NEGATIVE_ONE;
	}
}
