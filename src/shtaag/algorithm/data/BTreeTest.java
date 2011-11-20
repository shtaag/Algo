/**
 * 
 */
package shtaag.algorithm.data;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

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
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(3);
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
		tree.insert(tree, 11, 110);
		tree.insert(tree, 12, 120);
		tree.insert(tree, 13, 130);
		tree.insert(tree, 14, 140);
		tree.insert(tree, 15, 150);
		tree.insert(tree, 16, 160);
		tree.insert(tree, 17, 170);
		tree.insert(tree, 18, 180);
		tree.insert(tree, 19, 190);
		tree.insert(tree, 20, 200);
		tree.insert(tree, 21, 210);
		tree.insert(tree, 22, 220);
		tree.insert(tree, 23, 230);
		tree.insert(tree, 24, 240);
		tree.insert(tree, 25, 250);
		tree.insert(tree, 26, 260);
		tree.insert(tree, 27, 270);
		tree.insert(tree, 28, 280);
		tree.insert(tree, 29, 290);
		tree.insert(tree, 30, 300);
		tree.insert(tree, 31, 310);
		tree.insert(tree, 32, 320);
		tree.insert(tree, 33, 330);
		
		tree.show();
		
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
		
		tree.show();
		
		assertThat(tree.search(3), is(30));
	}

	@Test
	public void testInsertSameKey() {
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(4);
		try {
			tree.insert(tree, 2, 20);
			tree.insert(tree, 2, 20);
		
		} catch (IllegalArgumentException e) { 
			assertTrue(true);
			return;
		}
		fail();
	}
	/**
	 * Test method for {@link shtaag.algorithm.data.BTree#iterator()}.
	 */
	@Test
	public void testIterator() {
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(3);
		tree.insert(tree, 1, 10);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 3, 30);
		tree.insert(tree, 4, 40);
		tree.insert(tree, 5, 50);
		tree.insert(tree, 6, 60);
		tree.insert(tree, 7, 70);
		tree.insert(tree, 8, 80);
		tree.insert(tree, 9, 90);
		tree.insert(tree, 10, 100);
		tree.insert(tree, 11, 110);
		tree.insert(tree, 12, 120);
		tree.insert(tree, 13, 130);
		tree.insert(tree, 14, 140);
		tree.insert(tree, 15, 150);
		tree.insert(tree, 16, 160);
		tree.insert(tree, 17, 170);
		tree.insert(tree, 18, 180);
		tree.insert(tree, 19, 190);
		tree.insert(tree, 20, 200);
		tree.insert(tree, 21, 210);
		tree.insert(tree, 22, 220);
		tree.insert(tree, 23, 230);
		tree.insert(tree, 24, 240);
		tree.insert(tree, 25, 250);
		tree.insert(tree, 26, 260);
		tree.insert(tree, 27, 270);
		tree.insert(tree, 28, 280);
		tree.insert(tree, 29, 290);
		tree.insert(tree, 30, 300);
		tree.insert(tree, 31, 310);
		tree.insert(tree, 32, 320);
		tree.insert(tree, 33, 330);
		
		tree.show();
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (Integer value: tree) {
			set.add(value);
		}
		for (int i = 1; i <= 33; i++) {
			assertThat(set.contains(Integer.valueOf(i*10)), is(true));
		}
	}
	
	@Test
	public void testDeleteAtRootWithNoChild() {
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(3);
		tree.insert(tree, 5, 50);
		tree.insert(tree, 3, 30);
		
		tree.show();
		tree.delete(3);
		
		tree.show();
		
		assertThat(tree.search(5), is(50));
		
	}

	@Test
	public void testDeleteAtRootWithChild() {
		BTree<Integer, Integer> tree = new BTree<Integer, Integer>(3);
		tree.insert(tree, 5, 50);
		tree.insert(tree, 3, 30);
		tree.insert(tree, 2, 20);
		tree.insert(tree, 1, 10);
		tree.insert(tree, 6, 60);
		tree.insert(tree, 7, 70);
		tree.insert(tree, 8, 80);
		
		tree.show();
		tree.delete(3);
		
		tree.show();
		
		assertThat(tree.search(5), is(50));
		
	}
	

}
