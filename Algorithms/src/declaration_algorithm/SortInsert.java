package declaration_algorithm;
import perform_algorithm.*;
public class SortInsert extends BasicOperations {
	private static SortInsert instance;
	public SortInsert() {
		long startTime = System.nanoTime();
		ShowArray(insertSort(core.NumberArray));
		long endTime = System.nanoTime();
		double duration = (endTime - startTime)/1000;  
		System.out.println("Time: "+ duration+" micro seconds");
	}
	public static double [] insertSort(double[] Array){
		double NumberArray[]=new double[Array.length];
		SortBubble.BufforArray(NumberArray, Array);
			double value;
			int j;
		for(int i=1; i<NumberArray.length; i++){
			j=i;
			value=NumberArray[i];
				while(j>0 && NumberArray[j-1]>value){
						NumberArray[j]=NumberArray[j-1];
						j--;
				}
				NumberArray[j]=value;
		}
		
		return NumberArray;
	}
	public static SortInsert getInstance(){
		instance= new SortInsert();
		return instance;
}
	

}
