/**
 * 
 */
package shtaag.algorithm.data.tree.bstree;

import shtaag.algorithm.data.tree.bstree.MyBinarySearchTree.Node;

interface SearchType<T extends Comparable<T>> {
	public boolean hasNext();
	public void push(MyBinarySearchTree.Node<T> node);
	public MyBinarySearchTree.Node<T> pop();
}