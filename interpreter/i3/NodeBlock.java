import java.util.LinkedList;
import java.util.List;

/**
 * Created by kanna on 2/8/2017.
 */
public class NodeBlock extends NodeStatement{
	private List<NodeStatement> statements = new LinkedList<>();

	public NodeBlock(List<NodeStatement> statements){
		this.statements = statements;
	}

	public int eval(Environment e) throws EvalException{
		for(NodeStatement s :statements)
		{
			return s.eval(e);
		}
		//TODO: might lead to problems
		return 0;
	}
}
