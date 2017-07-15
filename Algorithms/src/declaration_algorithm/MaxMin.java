package declaration_algorithm;
import perform_algorithm.*;
public class MaxMin extends BasicOperations{
	
	private static MaxMin instance;
	public static double[] MinMax(double[] NumberArray){
		double max, min;
		double[] MaxMin= new double[2];
		max=NumberArray[0];
		min=NumberArray[0];
		for(int i=1; i<NumberArray.length; i++){
			if(NumberArray[i]>max)
				max=NumberArray[i];
			if(NumberArray[i]<min)
				min=NumberArray[i];
		}
		MaxMin[0]=max;
		MaxMin[1]=min;
			System.out.println("Max  Min");
			return MaxMin;
	}
	public MaxMin() {
		ShowArray(MinMax(core.NumberArray));
	}
	public static MaxMin getInstance(){
		instance=new MaxMin();
		return instance;
	}

}
