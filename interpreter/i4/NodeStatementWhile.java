/**
 * Created by kanna on 3/15/2017.
 */
public class NodeStatementWhile extends NodeStatement{
	private BoolExpression bol;
	private NodeStatement whileStatement;

	public NodeStatementWhile(BoolExpression boolExpression, NodeStatement whileStatement){
		bol = boolExpression;
		this.whileStatement = whileStatement;
	}

	public Double eval(Environment e) throws EvalException{
		while(bol.eval(e))
		{
			whileStatement.eval(e);
		}
		return null;
	}
}
