public class NodeAssn extends Node{
	private String id;
	private NodeExpr expression;

	public NodeAssn(String id, NodeExpr exp){
		this.id = id;
		expression = exp;
	}

	public int eval(Environment env) throws EvalException{
		return env.put(id, expression.eval(env));
	}
}
