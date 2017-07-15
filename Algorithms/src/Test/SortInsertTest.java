package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import declaration_algorithm.SortBubble;
import declaration_algorithm.SortInsert;

public class SortInsertTest {

	@Test
	public void test() {
		double[] testArray=new double[1000];
		double[] expectedArray=new double[1000];
		for(int i=0; i<1000; i++){
			testArray[i]=999-i;
			expectedArray[i]=i;
		}
			assertArrayEquals(SortInsert.insertSort(testArray), expectedArray, 0.001);
	}
}