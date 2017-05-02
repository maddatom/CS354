/**
 * Created by kanna on 5/1/2017.
 */
public class Function{
	private NodeExpr expression;
	private String funcArg;

	public Function(String funcArg, NodeExpr expression){
		this.funcArg = funcArg;
		this.expression = expression;
	}

	public double call(Environment e, double val) throws EvalException{
		Environment copy = e.copy();
		copy.put(funcArg, val);
		return this.expression.eval(copy);
	}
}
