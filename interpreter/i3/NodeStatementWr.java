/**
 * Created by kanna on 2/23/2017.
 */
public class NodeStatementWr extends NodeStatement{
	private NodeExpr expression;

	public NodeStatementWr (NodeExpr e){
		expression = e;
	}

	public int eval(Environment e) throws EvalException{
		return expression.eval(e);
	}
}
