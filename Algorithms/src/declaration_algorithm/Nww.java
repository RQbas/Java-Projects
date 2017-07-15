package declaration_algorithm;
import perform_algorithm.*;
public class Nww extends BasicOperations{
	private static Nww instance;
	public Nww() {
		ReturnFunctionValue(NWW(UserInput("Enter first number"), UserInput("Enter second number")));
	}
	public static int NWW(int FirstComponent, int SecondComponent){
		int nwwValue;
			nwwValue=(FirstComponent*SecondComponent)/(Nwd.NWD(FirstComponent, SecondComponent));
		return nwwValue;
	}
	public static Nww getInstance(){
		instance=new Nww();
		return instance;
	}
}
