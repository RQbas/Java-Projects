package Test;

import declaration_algorithm.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import perform_algorithm.*;
public class MinMaxTest {

	@Test
	public void test() {
		double[] testArray=new double[1000];
		for(int i=0; i<testArray.length; i++){
			testArray[i]=Math.pow(-1, i)*i;
		}
		assertArrayEquals(new double[]{998, -999}, MaxMin.MinMax(testArray), 0.01);

	}

}
