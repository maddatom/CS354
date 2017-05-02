// (C) 2013 Jim Buffenbarger
// All rights reserved.

public abstract class Node{

	protected int pos = 0;

	public double eval(Environment env) throws EvalException{
		throw new EvalException(pos, "cannot eval() node!");
	}

}
