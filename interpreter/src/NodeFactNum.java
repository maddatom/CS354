// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class NodeFactNum extends NodeFact{

	private String num;

	public NodeFactNum(String num){
		this.num = num;
	}

	public double eval(Environment env) throws EvalException{
		return Double.parseDouble(num);
	}

}
