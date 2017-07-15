package declaration_algorithm;
import perform_algorithm.*;
public class EratosthenesSieve extends BasicOperations{
	private static EratosthenesSieve instance;
	
	static boolean[] SieveArray;
	static int SieveArraySize;
	public EratosthenesSieve() {
		
	SieveArraySize=UserInput("Size of array for sieve");
	SieveArray=sieve(SieveArraySize);
	ShowSieveArray(SieveArray, SieveArraySize);
	}
	
	public static boolean[] sieve(int Size){
		boolean[] Array=new boolean[Size+1];
		for (int i=2; i*i<=Size; i++) 
	    {       
	        if(! Array[i])        
	    for (int j = i*i ; j<= Size; j+=i) 
	    	 Array[j] = true;      
	    }
		return Array;
	}
	public static void ShowSieveArray(boolean[] Array, int Size){
		for(int i=2;i<Size;i++)
		{ if(!Array[i])
			System.out.print(i+" ");
		}   	
			System.out.println("");
		    
	}
	
	
	public static EratosthenesSieve getInstance(){
		instance= new EratosthenesSieve();
		return instance;
}

}
