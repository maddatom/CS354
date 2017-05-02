// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeStmtWhileDo extends NodeStmt {

    private NodeWhileDo whiledo;

    public NodeStmtWhileDo(NodeWhileDo whiledo) {
	this.whiledo=whiledo;
    }

    public double eval(Environment env) throws EvalException {
	return whiledo.eval(env);
    }

}
