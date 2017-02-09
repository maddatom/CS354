/**
 * Created by kanna on 2/8/2017.
 */
public class NodeBlock extends Node {
	private NodeStatement statement;
	private NodeBlock block;

	public NodeBlock(NodeStatement stmnt, NodeBlock block) {
		this.statement = stmnt;
		this.block = block;
	}

	public NodeBlock(NodeStatement stmnt) {
		statement = stmnt;
	}

	public int eval(Environment e) throws EvalException {
		int stmnt = statement.eval(e);
		if (block == null) {
			System.out.println("block IS null");
			System.out.println("STATEMENT: " + statement);
			return stmnt;
//			return statement.eval(e);
		}
//		System.out.println("block is NOT null");
//		System.out.println("STATEMENT: " + statement);
//		statement.eval(e);
		return block.eval(e);
	}
}