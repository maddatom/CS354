/**
 * Created by kanna on 2/8/2017.
 */
public class NodeStatement extends Node {
    private NodeAssn assn;

    public NodeStatement(NodeAssn assn) {
        this.assn = assn;
    }

	public NodeStatement(){
	}

	public int eval(Environment e) throws EvalException {
        return assn.eval(e);
    }
}
