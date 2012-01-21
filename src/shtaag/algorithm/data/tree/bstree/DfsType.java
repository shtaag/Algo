/**
 * 
 */
package shtaag.algorithm.data.tree.bstree;

import java.util.Stack;

class DfsType<T extends Comparable<T>> implements SearchType<T> {

	private Stack<MyBinarySearchTree.Node<T>> stack = new Stack<MyBinarySearchTree.Node<T>>(); 
	@Override
	public void push(MyBinarySearchTree.Node<T> node) {
		stack.push(node);
	}

	@Override
	public MyBinarySearchTree.Node<T> pop() {
		MyBinarySearchTree.Node<T> node = stack.pop();
		if (node.getRTree() != null && node.getRTree().getRoot().getKey() != null) {
			stack.push(node.getRTree().getRoot());
		}
		if (node.getLTree() != null && node.getLTree().getRoot().getKey() != null) {
			stack.push(node.getLTree().getRoot());
		}
		return node;
	}

	@Override
	public boolean hasNext() {
		if (stack.empty()) return false;
		if (stack.peek().getKey() == null) return false;
		return true;
	}
}