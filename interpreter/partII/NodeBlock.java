/**
 * Created by kanna on 2/8/2017.
 */
public class NodeBlock extends Node {
    private NodeStatement statement;
    private NodeBlock block;

    public NodeBlock(NodeStatement stmnt, NodeBlock blk) {
        this.statement = stmnt;
        block = blk;
    }

    public NodeBlock(NodeStatement stmnt) {
        statement = stmnt;
    }

    public int eval(Environment e) throws EvalException {
        if (block == null) {
            return statement.eval(e);
        }
        statement.eval(e);
        return block.eval(e);
    }
}