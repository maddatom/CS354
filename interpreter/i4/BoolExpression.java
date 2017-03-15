/**
 * Created by kanna on 3/14/2017.
 */
public class BoolExpression{
	private String relationalOperator;
	private NodeExpr left, right;

	public BoolExpression(NodeExpr leftSide, String relationalOperator, NodeExpr rightSide){
		this.relationalOperator = relationalOperator;
		left = leftSide;
		right = rightSide;
	}

	public boolean eval(Environment e) throws EvalException{

		double leftValue = left.eval(e);
		double rightValue = right.eval(e);

		if(relationalOperator.equals(">"))
		{
			return leftValue > rightValue;
		} else if(relationalOperator.equals("<"))
		{
			return leftValue < rightValue;
		} else if(relationalOperator.equals("=="))
		{
			return leftValue == rightValue;
		} else if(relationalOperator.equals("<="))
		{
			return leftValue <= rightValue;
		} else if(relationalOperator.equals(">="))
		{
			return leftValue >= rightValue;
		} else if(relationalOperator.equals("<>"))
		{
			return leftValue != rightValue;
		} else
			throw new EvalException(0, "Unknown relational operator");
	}
}
