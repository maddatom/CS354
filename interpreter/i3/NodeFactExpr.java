/**
 * Created by kanna on 3/5/2017.
 */
public class NodeFactExpr extends NodeFact{
	private NodeExpr xpression;

	public NodeFactExpr(NodeExpr xp){
		xpression = xp;
	}

	public int eval(Environment env) throws EvalException{
		return xpression.eval(env);
	}
}
