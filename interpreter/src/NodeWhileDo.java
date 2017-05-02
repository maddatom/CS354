// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeWhileDo extends Node {

    private NodeBoolExpr boolexpr;
    private NodeStmt stmt;

    public NodeWhileDo(NodeBoolExpr boolexpr, NodeStmt stmt) {
	this.boolexpr=boolexpr;
	this.stmt=stmt;
    }

    public double eval(Environment env) throws EvalException {
	double r=0;
	while (boolexpr.eval(env)==1)
	    r=stmt.eval(env);
	return r;
    }


}
