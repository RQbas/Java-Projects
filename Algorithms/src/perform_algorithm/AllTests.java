package perform_algorithm;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import Test.*;
import declaration_algorithm.*;
@RunWith(Suite.class)
@SuiteClasses({ FactorialTest.class,
				FibonacciElementTest.class,
				FibonacciSequenceTest.class,
				MinMaxTest.class,
				NwdTest.class,
				NwwTest.class,
				SortBubbleTest.class,
				SortInsertTest.class,
				SortSelectionTest.class,
				EratosthenesSieveTest.class
				})
public class AllTests {

}
