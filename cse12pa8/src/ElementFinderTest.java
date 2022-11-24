import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

/**
 * HeapTest class implements tester that will test the methods from heap file
 */
public class ElementFinderTest {

	@Test
	public void test4Largest() {
		int output = ElementFinder.Kth_finder("cse12pa8/src/nums.txt", 4, "largest");
		assertEquals(13, output);
	}

	@Test
	public void test4Smallest() {
		int output = ElementFinder.Kth_finder("cse12pa8/src/nums.txt", 4, "smallest");
		assertEquals(3, output);
	}
}