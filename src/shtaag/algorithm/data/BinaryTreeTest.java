/**
 * 
 */
package shtaag.algorithm.data;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author takei_s
 * @date 2011/10/30
 */
public class BinaryTreeTest {

	BinaryTree<Integer> bTree = new BinaryTree<Integer>();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bTree.insert(1);
		bTree.insert(2);
		bTree.insert(3);
		bTree.insert(4);
		bTree.insert(5);
		bTree.insert(6);
		bTree.insert(7);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link shtaag.algorithm.data.BinaryTree#iterator()}.
	 */
	@Test
	public void testIterator() {
		int counter = 0;
		for (Integer i : bTree) {
			System.out.println(i);
			counter++;
		}
		assertThat(counter, is(7));
	}

	/**
	 * Test method for {@link shtaag.algorithm.data.BinaryTree#insert(java.lang.Comparable)}.
	 */
	@Test
	public void testInsert() {
		bTree.insert(8);
		int counter = 0;
		for (Integer i : bTree) {
			System.out.println(i);
			counter++;
		}
		assertThat(counter, is(8));
	}

	/**
	 * Test method for {@link shtaag.algorithm.data.BinaryTree#remove(java.lang.Comparable)}.
	 */
	@Test
	public void testRemoveE() {
		bTree.remove(4);
		bTree.remove(7);
		int counter = 0;
		for (Integer i : bTree) {
			System.out.println(i);
			counter++;
		}
		assertThat(counter, is(5));

	}

	/**
	 * Test method for {@link shtaag.algorithm.data.BinaryTree#breadthFirstSearch(java.lang.Comparable)}.
	 */
	@Test
	public void testBreadthFirstSearch() {
		bTree.insert(100);
		assertThat(bTree.breadthFirstSearch(100), is(true));
	}

	/**
	 * Test method for {@link shtaag.algorithm.data.BinaryTree#depthFirstSearch(java.lang.Comparable)}.
	 */
	@Test
	public void testDepthFirstSearch() {
		bTree.insert(100);
		assertThat(bTree.depthFirstSearch(7), is(true));
	}

}
