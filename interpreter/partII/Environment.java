import java.util.HashMap;

public class Environment {
    private HashMap<String, Integer> environment = new HashMap<>();

    /**
     * Put the current token in the current environment
     * @param id the id of the token
     * @param val the value of the token
     * @return the previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.)
     */
    public int put(String id, int val) {
    	environment.put(id, val);
        return val;
    }

    /**
     * Get the specified token from within the environment
      * @param pos the position of the token
     * @param var the id of the token
     * @return the requested token
     * @throws EvalException Accessing an undefined variable
     */
    public int get(int pos, String var) throws EvalException {
        if (!environment.containsKey(var))
            throw new EvalException(pos, "The specified value is " +
                    "not stored in this environment");
        return environment.get(var);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        for(String key: environment.keySet()){
            builder.append(key + "="+ environment.get(key) + "\n");
        }
        return builder.toString().substring(0, builder.length()-1);
    }
}