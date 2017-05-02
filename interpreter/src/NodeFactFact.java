// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeFactFact extends NodeFact {

    private NodeFact fact;

    public NodeFactFact(NodeFact fact) {
	this.fact=fact;
    }

    public double eval(Environment env) throws EvalException {
	return -fact.eval(env);
    }

}
