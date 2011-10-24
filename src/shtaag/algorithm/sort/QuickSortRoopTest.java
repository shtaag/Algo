/**
 * 
 */
package shtaag.algorithm.sort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author takei_s
 * @date 2011/10/24
 */
public class QuickSortRoopTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link shtaag.algorithm.sort.QuickSortRoop#sort(java.util.List)}.
	 */
	@Test
	public void testSortListOfInteger() {

		List<Integer> array = new ArrayList<Integer>();
		array.add(10);
		array.add(13);
		array.add(11);
		array.add(5);
		array.add(1);
		
		QuickSortRoop instance = new QuickSortRoop();
		instance.sort(array);
		System.out.println("***** Finale *****");
		for (Integer i : array) {
			System.out.println(i);
		}
	}

}
