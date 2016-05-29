package declaration_algorithm;
import perform_algorithm.BasicOperations;
import perform_algorithm.core;

public class SortBubble extends BasicOperations {
	private static SortBubble instance;
	public SortBubble() {
		long startTime = System.nanoTime();
		ShowArray(bubbleSort(core.NumberArray));
		long endTime = System.nanoTime();
		double duration = (endTime - startTime)/1000;  
		System.out.println("Time: "+ duration+" micro seconds");
	}
	
	public static double [] bubbleSort(double[] Array){
		
		double NumberArray[]=new double[Array.length];
		BufforArray(NumberArray, Array);
		double temp;
		
		for(int i=0; i<NumberArray.length; i++){
			for(int j=0; j<NumberArray.length-1; j++){
				if(NumberArray[j]>NumberArray[j+1]){
					temp=NumberArray[j+1];
					NumberArray[j+1]=NumberArray[j];
					NumberArray[j]=temp;
				}
			}
		}
		return NumberArray;
	}
	public static void BufforArray(double NumberArray[], double[] Array){
		for(int i=0; i<NumberArray.length; i++)
		NumberArray[i]=Array[i];
	}
	
	
	
	
	
	public static SortBubble getInstance(){
		instance= new SortBubble();
		return instance;
}
}