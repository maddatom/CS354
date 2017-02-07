public class NodeAssn extends Node 
{
    private String id;
    private int num;
	
    public NodeAssn(String id, int num) 
    {
    	this.id=id;
    	this.num = num;
    }
	
    public int eval(Environment env) throws EvalException 
    {
    	return env.put(id,num);
    }
}