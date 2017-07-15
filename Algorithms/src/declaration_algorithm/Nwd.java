package declaration_algorithm;
import perform_algorithm.*;
public class Nwd extends BasicOperations {
	private static Nwd instance;
	public Nwd() {
		ReturnFunctionValue(NWD(UserInput("Enter first number"), UserInput("Enter second number")));
	}
	public static int NWD(int FirstComponent, int SecondComponent){
		while(FirstComponent!=SecondComponent)
		{
			if(FirstComponent>SecondComponent)
				FirstComponent-=SecondComponent;
			else
				SecondComponent-=FirstComponent;
			
		}
		return FirstComponent;		
}
	public static Nwd getInstance(){
		instance=new Nwd();
		return instance;
	}


}
