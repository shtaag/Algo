/**
 * 
 */
package shtaag.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author takei_s
 * @date 2011/10/24
 */
public class HeapSortTest {

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
	 * Test method for {@link shtaag.algorithm.sort.HeapSort#sort(java.util.List)}.
	 */
	@Test
	public void testSort() {
		List<Integer> array = new ArrayList<Integer>();
		array.add(10);
		array.add(13);
		array.add(11);
		array.add(1);
		array.add(5);
		
		HeapSort heapsort = new HeapSort();
		List<Integer> sorted = heapsort.sort(array);
		
		System.out.println("***** Finale *****");
		for (Integer i : sorted) {
			System.out.println(i);
		}
	}

}
