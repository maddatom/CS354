public class NodeAssn extends Node{
	private String id;
	private NodeExpr expression;

	public NodeAssn(String id, NodeExpr e){
		this.id = id;
		expression = e;
	}
	public String getId(){return id;}
	public int eval(Environment env) throws EvalException{
		return env.put(id, expression.eval(env));
	}
}
