/**
 * Created by kanna on 3/15/2017.
 */
public class NodeStatementIfThenBlock extends NodeStatement{
	private BoolExpression boolExpression;
	private NodeStatement ifThenBlock;

	public NodeStatementIfThenBlock(BoolExpression boolExpression, NodeStatement ifThenexpression){
		this.boolExpression = boolExpression;
		ifThenBlock = ifThenexpression;
	}

	public Double eval(Environment e) throws EvalException{
		if(boolExpression.eval(e))
		{
			return ifThenBlock.eval(e);
		} else
			return null;
	}
}
