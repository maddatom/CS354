/**
 * Created by kanna on 2/23/2017.
 */
public class NodeFactNum extends NodeFact{
	private NodeNum n;

	public NodeFactNum(NodeNum num){
		n = num;
	}

	public int eval(Environment e) throws EvalException{
		return Integer.parseInt(num);
	}
}
