/**
 * Created by kanna on 2/23/2017.
 */
public class NodeFactExpr extends NodeFact{
	private NodeExpr expr;
	private NodeFactUnary unary;

	public NodeFactExpr(NodeExpr expression){
		expr = expression;
	}

	public NodeFactExpr(NodeExpr e, NodeFactUnary u){
		expr = e;
		unary = u;
	}

	public int eval(Environment e) throws EvalException{
		return unary == null ? expr.eval(e) : - 1 * expr.eval(e);
	}
}
