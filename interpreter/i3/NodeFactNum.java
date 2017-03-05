/**
 * Created by kanna on 3/5/2017.
 */
public class NodeFactNum extends NodeFact{
	private NodeNum num;

	public NodeFactNum(NodeNum num){
		this.num = num;
	}

	public int eval(Environment env) throws EvalException{
		return num.eval(env);
	}
}
