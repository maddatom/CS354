/**
 * Created by kanna on 3/15/2017.
 */
public class NodeStatementAssn extends NodeStatement{
	private NodeAssn assn;

	public NodeStatementAssn(NodeAssn a){
		assn = a;
	}

	public Double eval(Environment environment) throws EvalException{
		assn.eval(environment);
		return null;
	}
}
