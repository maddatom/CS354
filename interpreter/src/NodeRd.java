// (C) 2013 Jim Buffenbarger
// All rights reserved.

import java.util.Scanner;

public class NodeRd extends Node {

    private String id;

    private static Scanner scanner=null;

    public NodeRd(String id) {
	this.id=id;
    }

    public double eval(Environment env) throws EvalException {
	if (scanner==null)
	    scanner=new Scanner(System.in);
	return env.put(id,scanner.nextDouble());

    }

}
