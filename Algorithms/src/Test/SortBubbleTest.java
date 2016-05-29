package Test;

import static org.junit.Assert.*;
import declaration_algorithm.*;
import org.junit.Test;

public class SortBubbleTest {

	@Test
	public void test() {
		double[] testArray=new double[1000];
		double[] expectedArray=new double[1000];
		for(int i=0; i<1000; i++){
			testArray[i]=999-i;
			expectedArray[i]=i;
		}
			assertArrayEquals(SortBubble.bubbleSort(testArray), expectedArray, 0.001);
	}

}
