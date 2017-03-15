/**
 * Created by kanna on 3/5/2017.
 */
public class NodeStatementWr extends NodeStatement{
	private NodeExpr xpr;

	public NodeStatementWr(NodeExpr xp){
		xpr = xp;
	}

	public Double eval(Environment e) throws EvalException{
		System.out.println(xpr.eval(e));
		return null;
	}
}
