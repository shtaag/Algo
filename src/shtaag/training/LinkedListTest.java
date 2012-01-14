/**
 * 
 */
package shtaag.training;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shtaag.algorithm.sort.QuickSortRoop;

/**
 * @author takei_s
 * @date 2011/10/30
 */
public class LinkedListTest {
	@Test
	public void testqueue() {

		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		queue.add(1);
		queue.remove();
		queue.add(2);
		queue.add(3);
		queue.add(4);
		
		System.out.println(queue.remove());
		
	}

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

}
