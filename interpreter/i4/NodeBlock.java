import java.util.List;

/**
 * Created by kanna on 2/8/2017.
 */
public class NodeBlock extends NodeStatement{
	private List<NodeStatement> statementList;
	private NodeStatement statement;
	private NodeBlock block;

	public NodeBlock(NodeStatement stmnt, NodeBlock block){
		this.statement = stmnt;
		this.block = block;
		statementList = null;
	}

	public NodeBlock(List<NodeStatement> stmnt){
		statementList = stmnt;
	}
	public NodeBlock(NodeStatement stmnt){
		statement = stmnt;
	}

	public Double eval(Environment e) throws EvalException{

		if(statementList == null)
		{
			double stmnt = statement.eval(e);
			if(block == null)
			{
				return stmnt;
			}
			return block.eval(e);
		} else
		{
			for(NodeStatement s : statementList)
			{
				s.eval(e);
			}
			return null;
		}
	}
}
