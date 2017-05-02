// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeBoolExpr extends Node{

	private NodeExpr exprl;
	private NodeRelop relop;
	private NodeExpr exprr;

	public NodeBoolExpr(NodeExpr exprl, NodeRelop relop, NodeExpr exprr){
		this.exprl = exprl;
		this.relop = relop;
		this.exprr = exprr;
	}

	public double eval(Environment env) throws EvalException{
		return relop.op(exprl.eval(env), exprr.eval(env));
	}

}

