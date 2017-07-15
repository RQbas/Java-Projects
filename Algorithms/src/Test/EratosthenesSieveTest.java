package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import declaration_algorithm.EratosthenesSieve;

public class EratosthenesSieveTest {

	@Test
	public void test() {
		
		boolean[] testArray=new boolean[1000];
		testArray=EratosthenesSieve.sieve(1000);
		EratosthenesSieve.ShowSieveArray(testArray, 1000);
		
		
		for(int i=2;i<1000;i++)
		{ if(!testArray[i])
			assertEquals(check_prime(i, 1000), true);
		}   	
		
	}
	
	
	
	boolean check_prime(int x, int size){
		boolean result=false;
		int counter=0;
		for(int i=size; i>=1; i--){
			if(x % i==0){
				counter++;
			}
				
		}
			if(counter==2)
				result=true;
		
		return result;
	}
}
