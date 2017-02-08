// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

import java.util.HashMap;

public class Environment {
    private HashMap<String, Integer> environment = new HashMap<>();

    public int put(String var, int val) {
        return environment.put(var, val);
    }

    public int get(int pos, String var) throws EvalException {
        if (!environment.containsKey(var))
            throw new EvalException(pos, "The specified value is " +
                    "not stored in this environment");
        return environment.get(var);
    }

    public String toString() {
        return environment.toString();
    }
}