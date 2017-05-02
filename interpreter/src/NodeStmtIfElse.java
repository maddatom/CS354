// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeStmtIfElse extends NodeStmt {

    private NodeIfElse ifelse;

    public NodeStmtIfElse(NodeIfElse ifelse) {
	this.ifelse=ifelse;
    }

    public double eval(Environment env) throws EvalException {
	return ifelse.eval(env);
    }

}
