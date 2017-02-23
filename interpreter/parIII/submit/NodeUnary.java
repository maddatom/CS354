
public class NodeUnary extends Node
{
	private String unaryMinus;

	public NodeUnary(String unaryMinus)
	{
		this.unaryMinus = unaryMinus;
	}

	/**
	 * Simply for the abstract implementation's sake
	 * @param env
	 * @return
	 * @throws EvalException 
	 */
	@Override
	public Double eval(Environment env) throws EvalException
	{
		return env.put(unaryMinus, pos);
	}
}
