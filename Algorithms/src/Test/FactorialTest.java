package Test;


import static org.junit.Assert.*;
import org.junit.Test;
import declaration_algorithm.*;


public class FactorialTest {

	@Test
	public void test() {
		
		for(int i=1; i<=10; i++){
			assertEquals(Factorial.factorial(i), IterativeFactorial(i));
		}
		
		
	}
	public int IterativeFactorial(int k){
		int result=1;
		for(int i=1; i<=k; i++)
			result*=i;
		return result;
	}

}
