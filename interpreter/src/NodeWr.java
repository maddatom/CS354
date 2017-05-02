// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeWr extends Node {

    private NodeExpr expr;

    public NodeWr(NodeExpr expr) {
	this.expr=expr;
    }

    public double eval(Environment env) throws EvalException {
	double val=expr.eval(env);
	System.out.println(val);
	return val;
    }

}

