/**
 * Created by kanna on 5/1/2017.
 */
public class NodeFuncCall extends NodeFact{
	private String id;
	private NodeExpr nodeExpr;

	public NodeFuncCall(int position, String id, NodeExpr expr){
		this.pos = position;
		this.id = id;
		nodeExpr = expr;
	}

	public double eval(Environment e) throws EvalException{
		Function f = e.get((double) pos, id);
		return f.call(e, nodeExpr.eval(e));
	}
}
