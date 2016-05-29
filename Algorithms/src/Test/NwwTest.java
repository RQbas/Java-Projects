package Test;
import declaration_algorithm.*;
import static org.junit.Assert.*;

import org.junit.Test;

import perform_algorithm.*;

public class NwwTest {

	@Test
	public void test() {
		int randomNumber;
		for(int i=10;i<10; i++){
			randomNumber=  (int) (Math.random()*1000);
			
			assertEquals(Nww.NWW(i, randomNumber), ((randomNumber*i)/NWDtest(randomNumber, (i % randomNumber)))) ;
		
	}

}
	public static int NWDtest(int FirstComponent, int SecondComponent){ //dopisz UnitTest
		while(FirstComponent!=SecondComponent)
		{
			if(FirstComponent>SecondComponent)
				FirstComponent-=SecondComponent;
			else
				SecondComponent-=FirstComponent;
			
		}
		return FirstComponent;		
}
}