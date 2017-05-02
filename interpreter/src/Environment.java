// (C) 2013 Jim Buffenbarger
// All rights reserved.

import java.util.HashMap;
import java.util.Map;

public class Environment{
	private Map<String, Double> map;
	private Map<String, Function> functionMap;

	public Environment(){
		map = new HashMap<>();
		functionMap = new HashMap<>();
	}

	public double put(String var, double val){
		map.put(var, val);
		return val;
	}

	public double get(int pos, String var) throws EvalException{
		if(map.containsKey(var))
			return map.get(var);
		throw new EvalException(pos, "undefined variable: " + var);
	}

	public Function get(double pos, String funcName) throws EvalException{
		if(functionMap.containsKey(funcName))
			return functionMap.get(funcName);
		throw new EvalException((int)pos, "undefined variable: " + funcName);
	}

	public void put(String funcName, Function func){
		functionMap.put(funcName, func);
	}

	public Environment copy(){
		Environment copy = new Environment();
		for(String s : map.keySet())
		{
			copy.put(s, map.get(s));
		}

		for(String name : functionMap.keySet())
		{
			copy.put(name, functionMap.get(name));
		}
		return copy;
	}

	public String toString(){
		return "ENV-MAP: \n:" + map.toString() + "\nFUNC-MAP:" + functionMap.toString();
	}
}
