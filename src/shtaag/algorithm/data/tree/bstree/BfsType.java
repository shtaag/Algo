/**
 * 
 */
package shtaag.algorithm.data.tree.bstree;

import java.util.ArrayDeque;
import java.util.Queue;

class BfsType<T extends Comparable<T>> implements SearchType<T> {
	
	private Queue<MyBinarySearchTree.Node<T>> queue = new ArrayDeque<MyBinarySearchTree.Node<T>>(); 
	@Override
	public void push(MyBinarySearchTree.Node<T> node) {
		queue.add(node);
	}
	
	@Override
	public MyBinarySearchTree.Node<T> pop() {
		MyBinarySearchTree.Node<T> node = queue.poll();
		if (node.getRTree() != null && node.getRTree().getRoot().getKey() != null) {
			queue.add(node.getRTree().getRoot());
		}
		if (node.getLTree() != null && node.getLTree().getRoot().getKey() != null) {
			queue.add(node.getLTree().getRoot());
		}
		return node;
	}
	
	@Override
	public boolean hasNext() {
		if (queue.isEmpty()) return false;
		if (queue.peek().getKey() == null) return false;
		return true;
	}
}