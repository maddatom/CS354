import java.util.EmptyStackException;
import java.util.Stack;

@SuppressWarnings("serial")
public class RPNStack extends Stack<String> {

	public void addition() throws EmptyStackException {
		int numOne = Integer.parseInt(pop(), 2);
		int numTwo = Integer.parseInt(pop(), 2);
		int result = numOne + numTwo;
		System.out.println("BINARY: " + Integer.toBinaryString(result));
		push(Integer.toBinaryString(result));
	}

	public void subtration() throws EmptyStackException {
		int numOne = Integer.parseInt(pop(), 2);
		int numTwo = Integer.parseInt(pop(), 2);
		int result = numOne - numTwo;
		push(Integer.toBinaryString(result));
	}

	public void multiplication() throws EmptyStackException {
		int numOne = Integer.parseInt(pop(), 2);
		int numTwo = Integer.parseInt(pop(), 2);
		int result = numOne * numTwo;
		push(Integer.toBinaryString(result));
	}

	public void division() throws EmptyStackException {
		int numOne = Integer.parseInt(pop(), 2);
		int numTwo = Integer.parseInt(pop(), 2);
		int result = numOne / numTwo;
		push(Integer.toBinaryString(result));
	}
}
