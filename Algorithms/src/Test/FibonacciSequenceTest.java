package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import perform_algorithm.*;
import declaration_algorithm.*;
public class FibonacciSequenceTest {

	@Test
	public void test() {
		int SequenceArray[]= new int[20];
		SequenceArray=FibonacciSequence.Fibonaccisequence(20);
		for(int i=0; i<20; i++)
			assertEquals(SequenceArray[i], IterativeFibonacci(i));
	
	}
	public int IterativeFibonacci(int k){
		int n_1=1;
		int n_2=1;
		int result=1;
		if(k>1){
			for(int i=1; i<k; i++)
			{
				result=n_1+n_2;
				n_1=n_2;
				n_2=result;
			}
		}
		
			return result;
	}
	
	
}
