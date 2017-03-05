/**
 * Created by kanna on 2/8/2017.
 */
public class NodeBlock extends Node{
	private NodeStatement statement;
	private NodeBlock block;

	public NodeBlock(NodeStatement stmnt, NodeBlock block){
		this.statement = stmnt;
		this.block = block;
	}

	public NodeBlock(NodeStatement stmnt){
		statement = stmnt;
	}

	public int eval(Environment e) throws EvalException{
		int stmnt = statement.eval(e);
		if(block == null)
		{
			return stmnt;
		}
		return block.eval(e);
	}
}
