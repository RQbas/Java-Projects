package declaration_algorithm;
import perform_algorithm.*;

public class Fibonacci extends BasicOperations{
	public static Fibonacci instance;
	public Fibonacci() {
		ReturnFunctionValue(fibonacci(UserInput("Value of fibonacci element in given position")-1));
	}
	
	public static int fibonacci(int n){
		int result;
		if(n<2)
			return 1;
		result=fibonacci(n-2)+fibonacci(n-1);
		return result;
	}
	public static Fibonacci getInstance(){
		instance=new Fibonacci();
		return instance;
	}

}
