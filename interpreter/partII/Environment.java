// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

public class Environment {

    public int put(String var, int val) { return val; }
    public int get(int pos, String var) throws EvalException { return 0; }
    public String toString() { return null; }

}