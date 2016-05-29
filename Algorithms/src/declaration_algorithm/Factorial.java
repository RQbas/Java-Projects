package declaration_algorithm;

import perform_algorithm.BasicOperations;

public class Factorial extends BasicOperations {
	public static Factorial instance;
	public Factorial(){
		
		ReturnFunctionValue(factorial(UserInput("Factorial expansion up to ")));
	}
	
	
	public static int factorial(int n){
		int result;
		if(n==1){
			return 1;
		}
		 result=n*factorial(n-1);
		return result;
	}
	public static Factorial getInstance(){
		instance=new Factorial();
		return instance;
	}
}
