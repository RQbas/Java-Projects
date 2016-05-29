package perform_algorithm;
import java.util.InputMismatchException;
import java.util.Scanner;
import declaration_algorithm.*;
public class BasicOperations {
	public static BasicOperations instance;
	
	public static void ShowArray(double[] NumberArray){
		for(int i=0; i<NumberArray.length; i++)
			System.out.print(NumberArray[i]+"  ");
			System.out.println("");
	 }
	void FirstShowArray(double[] NumberArray){
			System.out.println("Examplary array");
		for(int i=0; i<NumberArray.length; i++)
			System.out.print(NumberArray[i]+"  ");
			System.out.println("");
	 }
	void RandomNumberFill(double[] NumberArray){
		for(int i=0; i<NumberArray.length; i++)
			NumberArray[i]=Math.random();
	}
	void RandomNumberCorrection(double[] NumberArray){
			for(int i=0; i<NumberArray.length; i++){
					NumberArray[i]=Math.round(NumberArray[i]*1000);
					NumberArray[i]/=10.0;
			}
	}
	 public void ReturnFunctionValue(int value){
		System.out.println(value);
	}
	
	 protected double[] CastIntToDouble(int[] NumberArray){
		 double CastedArray[]= new double[NumberArray.length];
		 for(int i=0; i<NumberArray.length; i++){
			 CastedArray[i]=(double) NumberArray[i];
		 }
			 return CastedArray;
	 }
	 
	protected static int UserInput(String s){
				System.out.println(s);
				int ArraySize=0;
					Scanner sc = new Scanner(System.in);
					try{
						
						ArraySize= sc.nextInt();
						if(ArraySize<=0)
						        throw new IllegalArgumentException("Positive number is required");
						}
					catch(InputMismatchException exception){
							System.out.println("Integer number is required");
							
						}
					return ArraySize;
			}
	
	
	
	
				

	

}
