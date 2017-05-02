/**
 * Created by kanna on 5/1/2017.
 */
public class NodeFuncDecl extends NodeStmt{
	private String funcName, functArgs;
	private NodeExpr expression;

	public NodeFuncDecl(String func, NodeExpr e, String args){
		functArgs = args;
		expression = e;
		this.funcName = func;
	}

	public double eval(Environment e){
		e.put(funcName, new Function(functArgs, expression));
		return 0;
	}

	public String toString(){
		return "Func name: " + funcName + ", Args: "
				+ functArgs + ", Expression: \n\t" + expression.toString();
	}
}
