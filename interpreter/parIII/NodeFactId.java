/**
 * Created by kanna on 2/23/2017.
 */
public class NodeFactId extends NodeFact{
	private String id;
	private NodeFactUnary unary;

	public NodeFactId(int pos, String lex){
		id = lex;
		this.pos = pos;
	}

	public NodeFactId(int pos, String id, NodeFactUnary u){
		this.pos = pos;
		this.id = id;
		this.unary = u;
	}

	public String getId(){
		return id;
	}

	public int eval(Environment e) throws EvalException{
		return unary == null ? e.get(pos, id) : - 1 * e.get(pos, id);
	}
}
