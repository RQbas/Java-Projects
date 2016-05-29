package perform_algorithm;
import declaration_algorithm.*;
public class core extends BasicOperations {
	
	public static int ArraySize;
	public static double[] NumberArray;
	private static core instance=null;
	
	//STATES
	public core(int ArraySize){
		NumberArray= new double[ArraySize];
		RandomNumberFill(NumberArray);
		RandomNumberCorrection(NumberArray);
		SimpleInterface();
	
		
		
	}

	
	void SimpleInterface(){
		boolean isWorking=true;
		
		while(isWorking){
			FirstShowArray(NumberArray);
			System.out.println("Choose number of algorithm");
			System.out.println("1. Factorial");
			System.out.println("2. Fibonacci element");
			System.out.println("3. Fibonacci sequence");
			System.out.println("4. Min and Max");
			System.out.println("5. NWD");
			System.out.println("6. NWW");
			System.out.println("7. Bubble sort");
			System.out.println("8. Insert sort");
			System.out.println("9. Selection sort");
			System.out.println("10. Eratosthenes sieve");
			System.out.println("Other number-> Exit");
			switch(UserInput("")){
			case 1: 
			{
				//Factorial
				Factorial.getInstance();
				break;
			}
			case 2:
			{
				//Fibonacci
				Fibonacci.getInstance();
				break;
			}
			case 3:
			{
				//Fibonacci Sequence
				FibonacciSequence.getInstance();
				break;
			}
			case 4:{
				//Max&Min Algorithms
				MaxMin.getInstance();
				
				break;
			}
			case 5:{
				//NWD
				Nwd.getInstance();
				break;
			}
			case 6: {
				//NWW
				Nww.getInstance();
				break;
			}
			case 7:{
				//Bubble sort
				SortBubble.getInstance();
				break;
			}
			case 8:{
				SortInsert.getInstance();
				break;
			}
			case 9:{
			SortSelection.getInstance();
				break;
			}
			case 10:{
				EratosthenesSieve.getInstance();
				break;
			}
			default:
			{
				isWorking=false;
				break;
			}
			}
			
		}
	}
	public static core getInstance(int ArraySize){
		if(instance==null)
			instance= new core(ArraySize);
		return  instance;
		}
	
	}


