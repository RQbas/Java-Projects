package Test;
import declaration_algorithm.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciElementTest {

	@Test
	public void test() {
		for(int i=1; i<=20; i++){
			assertEquals(Fibonacci.fibonacci(i), IterativeFibonacci(i));
		}
		
		
	}
	public int IterativeFibonacci(int k){
		int n_1=1;
		int n_2=1;
		int result=1;
		for(int i=1; i<k; i++)
		{
			result=n_1+n_2;
			n_1=n_2;
			n_2=result;
		}
			return result;
	}

}
