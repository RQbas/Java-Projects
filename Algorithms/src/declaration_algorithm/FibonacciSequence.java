package declaration_algorithm;
import perform_algorithm.*;
public class FibonacciSequence extends BasicOperations{
	private static FibonacciSequence instance;
	public FibonacciSequence() {
		ShowArray(CastIntToDouble(Fibonaccisequence(UserInput("Length of fibonacci sequence"))));
	}
	public static int[] Fibonaccisequence(int n){
		int FibonacciArray []= new int[n];
		FibonacciArray[0]=1;
			if(n>1)
			{
				FibonacciArray[1]=1;
				for(int i=2; i<n; i++)
					FibonacciArray[i]=FibonacciArray[i-1]+FibonacciArray[i-2];
			}
		return FibonacciArray;
	}
	public static FibonacciSequence getInstance(){
		instance=new FibonacciSequence();
		return instance;
	}
}
