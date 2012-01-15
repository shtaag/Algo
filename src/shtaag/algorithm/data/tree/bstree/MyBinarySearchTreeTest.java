/**
 * 
 */
package shtaag.algorithm.data.tree.bstree;


import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

/**
 * @author takei_s
 * @date 2012/01/14
 */
public class MyBinarySearchTreeTest {

	
	@Test
	public void testInsert() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(1);
		bst.insert(2);
		bst.insert(3);
		bst.insert(4);
		bst.insert(5);
		bst.insert(6);
		bst.insert(7);
		bst.insert(8);

		StringBuilder sb = new StringBuilder();
		for (Integer elem : bst) {
			sb.append(elem + ":");
		}
		System.out.println(sb.toString());
		bst.search(8);
		
	}
	@Test
	public void testInsertDuplicate() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		
		try {
			bst.insert(1);
			bst.insert(1);
		} catch (IllegalArgumentException e) {		
		}
		
	}
	
	@Test
	public void testDeleteRoot() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(1);
		bst.insert(2);
		
		bst.delete(1);
		
		StringBuilder sb = new StringBuilder();
		for (Integer elem : bst) {
			sb.append(elem + ":");
		}
		assertThat(sb.toString(), is("2:"));
	}
	
	@Test
	public void testDeleteLeaf() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(1);
		bst.insert(2);
		
		bst.delete(2);
		
		StringBuilder sb = new StringBuilder();
		for (Integer elem : bst) {
			sb.append(elem + ":");
		}
		assertThat(sb.toString(),is("1:"));
	}
	
	@Test
	public void testDeleteNoRTree() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(4);
		bst.insert(1);
		bst.insert(6);
		bst.insert(5);
		
		bst.delete(6);
		
		StringBuilder sb = new StringBuilder();
		for (Integer elem : bst) {
			sb.append(elem + ":");
		}
		assertThat(sb.toString(),is("4:1:5:"));
	}
	@Test
	public void testDeleteNoLTree() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(4);
		bst.insert(1);
		bst.insert(5);
		bst.insert(6);
		
		bst.delete(5);
		
		StringBuilder sb = new StringBuilder();
		for (Integer elem : bst) {
			sb.append(elem + ":");
		}
		assertThat(sb.toString(),is("4:1:6:"));
	}
	
	@Test
	public void testDeleteNoLTreeTransplantLargetree() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(4);
		bst.insert(1);
		bst.insert(5);
		bst.insert(6);
		bst.insert(20);
		bst.insert(15);
		bst.insert(10);
		bst.insert(17);
		bst.insert(25);
		
		bst.delete(6);
		
		StringBuilder sb = new StringBuilder();
		for (Integer elem : bst) {
			sb.append(elem + ":");
		}
		assertThat(sb.toString(),is("4:1:5:20:15:10:17:25:"));
	}

	@Test
	public void testDeleteUseMinimumSearch() {
		
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(4);
		bst.insert(1);
		bst.insert(5);
		bst.insert(7);
		bst.insert(6);
		bst.insert(20);
		bst.insert(15);
		bst.insert(10);
		bst.insert(17);
		bst.insert(25);
		
		bst.delete(7);
		
		StringBuilder sb = new StringBuilder();
		for (Integer elem : bst) {
			sb.append(elem + ":");
		}
		assertThat(sb.toString(),is("4:1:5:10:6:20:15:17:25:"));
	}
	
	@Test
	public void testSearch() {
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(1);
		bst.insert(10);
		bst.insert(2);
		bst.insert(4);
		bst.insert(12);
		bst.insert(62);
		bst.insert(73);
		bst.insert(80);
		
		bst.search(12);
		
	}

	@Test
	public void testBfsIterator() {
		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>(null);
		bst.insert(1);
		bst.insert(10);
		bst.insert(2);
		bst.insert(4);
		bst.insert(12);
		bst.insert(62);
		bst.insert(73);
		bst.insert(80);
		
		StringBuilder sb = new StringBuilder();
		for (Iterator<Integer> it = bst.bfsIterator(); it.hasNext();) {
			sb.append(it.next() + ":");
		}
		System.out.println(sb.toString());
		bst.search(12);
		
	}

}
