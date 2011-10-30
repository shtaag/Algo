/**
 * 
 */
package shtaag.algorithm.data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * In this binary tree, the tree is complete binary tree.<br>
 * So, elements are filled from top, and left towards right.<br>
 * -- caution--<br>
 * This tree does not extends itself, when the capacity is over.<br>
 * You should indicates appropriate max size of tree at creating instances.
 * 
 * @author takei_s
 * @Date 2011/10/28
 */
public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {
	
	private LinkedList<Node<E>> array = new LinkedList<Node<E>>();

	private Deque<Node<E>> insertPoint = new ArrayDeque<Node<E>>(); 
	
	public BinaryTree() {
		insertPoint.add(new Node<E>(0, null, null, null, null));
	}
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Iterator<Node<E>> innerIterator = array.iterator();
			@Override
			public boolean hasNext() {
				return innerIterator.hasNext();
			}

			@Override
			public E next() {
				return innerIterator.next().value;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public void insert(E value) {
		Node<E> insertNode = insertPoint.removeFirst();
		insertNode.value = value; // change value of the insertPoint
		registNewInsertPoint(insertNode); 
		insertNode.index = array.size();
		array.add(insertNode);
	}
	
	private void registNewInsertPoint(Node<E> node) {
		Node<E> leftChild = new Node<E>(-1, null, node, null, null);
		node.left = leftChild;
		insertPoint.add(leftChild);
		Node<E> rightChild = new Node<E>(-1, null, node, null, null);
		node.right = rightChild;
		insertPoint.add(rightChild);
	}

	/**
	 * remove the found first element
	 * @param value
	 */
	public void remove(E value) {
		Node<E> target = (Node<E>)bfs(value);
		Node<E> last = (Node<E>) array.getLast();
		swapNode(target, last);
		// now, target comes last
		target.value = null;
		target.left = null;
		target.right = null;
		insertPoint.addFirst(target);
		// maintain array
		array.removeLast();
	}
//	public void remove(int index) {
//		throw new UnsupportedOperationException();
//	}

	/**
	 * search with breadth first search
	 * @param value
	 * @return 
	 */
	public boolean breadthFirstSearch(E value) {
		Node<E> target = bfs(value);
		return (target == null) ? false : true;
	}

	private Node<E> bfs(E value) {
		System.out.println("*** start bfs;");
		LinkedList<Node<E>> queue = new LinkedList<BinaryTree.Node<E>>();
		queue.add(array.getFirst());
		while(true) {
			if (queue.isEmpty()) {
				return null;
			}
			Node<E> target = queue.remove();
			System.out.println("bfs: target index:" + target.index);
			if (target.value == null) {
				continue;
			}
			System.out.println("bfs: target value:" + target.value);
			if (target.value.equals(value)) {
				return target;
			} else {
				// regist left first, in order to search from left to right.
				queue.add(target.left);
				queue.add(target.right);
			}
		}
	}
	
	/**
	 * search with depth first search
	 * @param value
	 * @return 
	 */
	public boolean depthFirstSearch(E value) {
		System.out.println("*** start dfs;");
		Node<E> target = dfs(array.getFirst(), value);
		return (target == null) ? false : true;		
	}
	
	private Node<E> dfs(Node<E> parent, E value) {
		if (parent == null) {
			return null;
		}
		if (parent.value == null) {
			return null;
		}
		System.out.println("dfs: target index:" + parent.index);
		System.out.println("dfs: target value:" + parent.value);
		if (parent.value.equals(value)) {
			return parent;
		}
		Node<E> target = null;
		target = dfs(parent.left, value);
		if (target != null) {
			return target;
		}
		target = dfs(parent.right, value);
		if (target != null) {
			return target;
		}
		return null;
	}
	
	private static class Node<E> {
		private int index;
		private E value;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;

		Node(int index, E value, Node<E> parent, Node<E> left, Node<E> right) {
			this.index = index;
			this.value = value;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
	
	private void swapNode(Node<E> from, Node<E> to) { 
		int fromIndex = from.index;
		Node<E> fromParent = from.parent;
		Node<E> fromLeft = from.left;
		Node<E> fromRight = from.right;
		int toIndex = to.index;
		Node<E> toParent = to.parent;
		Node<E> toLeft = to.left;
		Node<E> toRight = to.right;
		
		from.index = toIndex;
		from.parent = toParent;
		from.left = toLeft;
		from.right = toRight;
		
		to.index = fromIndex;
		to.parent = fromParent;
		to.left = fromLeft;
		to.right = fromRight;
		
		swapArray(fromIndex, toIndex);
	}

	private void swapArray(int from, int to) {
		Node<E> fromObj = array.get(from);
		Node<E> toObj = array.get(to);
		array.set(from, toObj);
		array.set(to, fromObj);
	}

}
