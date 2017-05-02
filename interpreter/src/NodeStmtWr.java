// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeStmtWr extends NodeStmt {

    private NodeWr wr;

    public NodeStmtWr(NodeWr wr) {
	this.wr=wr;
    }

    public double eval(Environment env) throws EvalException {
	return wr.eval(env);
    }

}
