/**
 * Created by kanna on 3/5/2017.
 */
public class NodeFactId extends NodeFact{
	private String id;

	public NodeFactId(int pos, String lex){
		this.pos = pos;
		id = lex;
	}

	public int eval(Environment e) throws EvalException{
		return e.get(pos, id);
	}
}
