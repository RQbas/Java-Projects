package Test;
import declaration_algorithm.*;
import perform_algorithm.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class NwdTest {

	@Test
	public void test() {
		int randomNumber;
		for(int i=10;i<10; i++){
			randomNumber=  (int) (Math.random()*1000);
			
			assertEquals(Nwd.NWD(i, randomNumber), (NWDtest(randomNumber, (i % randomNumber)))) ;
		
	}

}
	public static int NWDtest(int FirstComponent, int SecondComponent){ 
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
