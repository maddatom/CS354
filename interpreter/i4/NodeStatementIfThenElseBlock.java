/**
 * Created by kanna on 3/15/2017.
 */
public class NodeStatementIfThenElseBlock extends NodeStatement{
	private BoolExpression boolExpression;
	private NodeStatement ifThenBlock, elseBlock;

	public NodeStatementIfThenElseBlock(BoolExpression boolExpression, NodeStatement ifThenexpression, NodeStatement
			elseStatment){
		this.boolExpression = boolExpression;
		ifThenBlock = ifThenexpression;
		elseBlock = elseStatment;
	}

	public Double eval(Environment e) throws EvalException{
		if(boolExpression.eval(e))
		{
			return ifThenBlock.eval(e);
		} else
			return elseBlock.eval(e);
	}
}
