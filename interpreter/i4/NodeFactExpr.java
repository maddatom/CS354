/**
 * Created by kanna on 3/5/2017.
 */
public class NodeFactExpr extends NodeFact{
	public final int NEGATIVE_ONE = - 1;
	private NodeExpr xpression;
	private NodeUnary negative;

	public NodeFactExpr(NodeExpr xp){
		xpression = xp;
	}

	public NodeFactExpr(NodeExpr xp, NodeUnary unary){
		negative = unary;
		xpression = xp;
	}

	public Double eval(Environment env) throws EvalException{
		if(negative != null)
			return xpression.eval(env) * NEGATIVE_ONE;
		return xpression.eval(env);
	}
}
