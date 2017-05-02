// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeIfElse extends Node {

    private NodeBoolExpr boolexpr;
    private NodeStmt thenstmt;
    private NodeStmt elsestmt;

    public NodeIfElse(NodeBoolExpr boolexpr,
		      NodeStmt thenstmt, NodeStmt elsestmt) {
	this.boolexpr=boolexpr;
	this.thenstmt=thenstmt;
	this.elsestmt=elsestmt;
    }

    public double eval(Environment env) throws EvalException {
	return boolexpr.eval(env)==1
	    ? thenstmt.eval(env)
	    : (elsestmt==null ? 0 : elsestmt.eval(env));
    }

}
