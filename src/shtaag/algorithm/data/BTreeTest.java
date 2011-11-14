/**
 * 
 */
package shtaag.algorithm.data;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author takei_s
 * @date 2011/11/14
 */
public class BTreeTest {

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
	 * Test method for {@link shtaag.algorithm.data.BTree#search(java.lang.Comparable)}.
	 */
	@Test
	public void testSearch() {
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(4);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 5, 50);
		tree.insert(tree, 3, 30);
		tree.insert(tree, 6, 60);
		tree.insert(tree, 1, 10);
		tree.insert(tree, 7, 70);
		tree.insert(tree, 8, 80);
		tree.insert(tree, 4, 40);
		tree.insert(tree, 9, 90);
		tree.insert(tree, 10, 100);
		
		assertThat(tree.search(4), is(40));
	}

	/**
	 * Test method for {@link shtaag.algorithm.data.BTree#insert(shtaag.algorithm.data.BTree, java.lang.Comparable, java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(4);
		tree.insert(tree, -2, 20);
		tree.insert(tree, 5, 50);
		tree.insert(tree, 3, 30);
		tree.insert(tree, -6, 60);
		tree.insert(tree, 1, 10);
		tree.insert(tree, 7, 70);
		tree.insert(tree, 8, 80);
		tree.insert(tree, 4, 40);
		tree.insert(tree, 9, 90);
		tree.insert(tree, 10, 100);
		
		for (Iterator<Integer> itr = tree.iterator(); itr.hasNext();) {
			System.out.println(itr.next());
		}
		
		assertThat(tree.search(3), is(30));
	}

	/**
	 * Test method for {@link shtaag.algorithm.data.BTree#iterator()}.
	 */
	@Test
	public void testIterator() {
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(4);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 2, 20);
		
		
		
		for (Iterator<Integer> itr = tree.iterator(); itr.hasNext();) {
			System.out.println(itr.next());
		}
		
		assertThat(tree.search(2), is(20));
	}

}
