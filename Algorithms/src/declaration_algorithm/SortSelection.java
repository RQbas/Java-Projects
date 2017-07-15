package declaration_algorithm;
import perform_algorithm.*;
public class SortSelection extends BasicOperations{
	private static SortSelection instance;
	double[] SortedArray;
	
	public SortSelection() {
		SortedArray=new double[core.ArraySize];
		long startTime = System.nanoTime();
		SortedArray=selectionSort(core.NumberArray);
		ShowArray(SortedArray);
		long endTime = System.nanoTime();
		double duration = (endTime - startTime)/1000;  
		System.out.println("Time: "+ duration+" micro seconds");
	}
	
	public static double[] selectionSort(double [] Array) {
		double NumberArray[]=new double[Array.length];
		SortBubble.BufforArray(NumberArray, Array);
		
	      int minIndex;
	      double tmp;
	      int n = NumberArray.length;
	      for (int i = 0; i < n - 1; i++) {
	            minIndex = i;
	            for (int j = i + 1; j < n; j++)
	                  if (NumberArray[j] < NumberArray[minIndex])
	                        minIndex = j;
	            if (minIndex != i) {
	                  tmp = NumberArray[i];
	                  NumberArray[i] = NumberArray[minIndex];
	                  NumberArray[minIndex] = tmp;
	            }
	      }
	      	return NumberArray;
	}
	
	
	
	public static SortSelection getInstance(){
		instance=new SortSelection();
		return instance;
	}
}
