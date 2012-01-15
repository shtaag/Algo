/**
 * 
 */
package shtaag.algorithm.data.tree.bstree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * @author takei_s
 * @Date 2012/01/12
 * 
 */
public class MyBinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
	
	private static class Node<T extends Comparable<T>> {
		public T key;
		private MyBinarySearchTree<T> rTree;
		private MyBinarySearchTree<T> lTree;
		
		public Node(T key) {
			this.key = key;
		}
		private MyBinarySearchTree<T> getRTree() {
			return rTree;
		}
		private MyBinarySearchTree<T> getLTree() {
			return lTree;
		}
	}
	
	private Node<T> root;
	public MyBinarySearchTree(T key) {
		root = new Node<T>(key);
	}
	
	private Node<T> getRoot() {
		return root;
	}
	
	public void insert(T key) {
		if (root.key == null) {
			root = new Node<T>(key);
			return;
		}
		
		if (key.compareTo(root.key) < 0) {
			if (root.lTree == null) {
				root.lTree = new MyBinarySearchTree<T>(key);
				return;
			}
			root.lTree.insert(key);
		} else if (key.compareTo(root.key) > 0){
			if (root.rTree == null) {
				root.rTree = new MyBinarySearchTree<T>(key);
				return;
			}
			root.rTree.insert(key);
		} else {
			throw new IllegalArgumentException("That element is already in.");
		}
		
	}
	
	public void delete(T key) {
		
		Node<T> target = searchHelper(key, this);
		if (target == null) throw new NoSuchElementException();
		deleteHelp(key, target);
	}
	
	private void deleteHelp(T key, Node<T> node) {
		
		if (node.lTree == null) {
			if (node.rTree == null) {
				node.key = null;
			} else {
				// transplant from rTree
				node.key = node.rTree.root.key;
				Node<T> rTreeRoot = node.rTree.root;
				node.rTree = rTreeRoot.rTree;
				node.lTree = rTreeRoot.lTree;
			}
		} else if (node.rTree == null) {
			// in case of only lTree exists, transplant it
			node.key = node.lTree.root.key;
			Node<T> lTreeRoot = node.lTree.root;
			node.lTree = lTreeRoot.lTree;
			node.rTree = lTreeRoot.rTree;
		} else {
			// node has two childs
			if (node.rTree.root.lTree == null) {
				// right child of the node is the successor, so transplant it.
				node.key = node.rTree.root.key;
				Node<T> rTreeRoot = node.rTree.root;
				node.lTree = rTreeRoot.lTree;
				node.rTree = rTreeRoot.rTree;
			}
			Node<T> successor = successorInRightTree(node);
			node.key = successor.key;
			// successor has no lTree
			if (successor.rTree == null) {
				successor.key = null;
			} else {
				successor.key = successor.rTree.root.key;
				successor.rTree = successor.rTree.root.rTree;
			}
		}
	}
	
	/**
	 * can use only the node has right tree
	 */
	private Node<T> successorInRightTree(Node<T> node) {
		if (node.rTree == null) throw new IllegalArgumentException();
		return minimum(node.rTree.root);
	}
	private Node<T> minimum(Node<T> node) {
		if (node.lTree != null) {
			return minimum(node.lTree.root);
		}
		return node;
	}
	
	public T search(T key) {
		
		return searchHelper(key, this).key;
	}
	
	private Node<T> searchHelper(T key, MyBinarySearchTree<T> tree) {
		
		if (tree.root.key == null) {
			throw new NoSuchElementException();
		}
		
		if (key.equals(tree.root.key)) {
			return tree.root;
		} else if (key.compareTo(root.key) < 0) {
			if (tree.root.lTree == null) throw new NoSuchElementException();
			
			return searchHelper(key, tree.root.lTree);
		} else if (key.compareTo(root.key) > 0){
			if (tree.root.rTree == null) throw new NoSuchElementException();
			
			return searchHelper(key, tree.root.rTree);
		}
		throw new NoSuchElementException();
	}
	
	public T depthFirstSearch(T key) {
		
		for (Iterator<T> it = dfsIterator(); it.hasNext(); ) {
			T current = it.next(); 
			if (current == key) {
				return current;
			}
		}
		throw new NoSuchElementException();
	}
	
	public Iterator<T> dfsIterator() {
		return new HelpIterator<T>(this, new dfsType<T>());
	}
	
	public T breadthFirstSearch(T key) {
		for (Iterator<T> it = bfsIterator(); it.hasNext(); ) {
			T current = it.next(); 
			if (current == key) {
				return current;
			}
		}
		throw new NoSuchElementException();
		
	}
	
	public Iterator<T> bfsIterator() {
		return new HelpIterator<T>(this, new bfsType<T>());
	}
	
	private static interface SearchType<T extends Comparable<T>> {
		public boolean hasNext();
		public void push(Node<T> node);
		public Node<T> pop();
	}
	
	private static class dfsType<T extends Comparable<T>> implements SearchType<T> {

		private Stack<Node<T>> stack = new Stack<MyBinarySearchTree.Node<T>>(); 
		@Override
		public void push(Node<T> node) {
			stack.push(node);
		}

		@Override
		public Node<T> pop() {
			Node<T> node = stack.pop();
			if (node.rTree != null && node.rTree.root.key != null) {
				stack.push(node.getRTree().getRoot());
			}
			if (node.lTree != null && node.lTree.root.key != null) {
				stack.push(node.getLTree().getRoot());
			}
			return node;
		}

		@Override
		public boolean hasNext() {
			if (stack.empty()) return false;
			if (stack.peek().key == null) return false;
			return true;
		}
	}
	
	private static class bfsType<T extends Comparable<T>> implements SearchType<T> {
		
		private Queue<Node<T>> queue = new ArrayDeque<MyBinarySearchTree.Node<T>>(); 
		@Override
		public void push(Node<T> node) {
			queue.add(node);
		}
		
		@Override
		public Node<T> pop() {
			Node<T> node = queue.poll();
			if (node.rTree != null && node.rTree.root.key != null) {
				queue.add(node.getRTree().getRoot());
			}
			if (node.lTree != null && node.lTree.root.key != null) {
				queue.add(node.getLTree().getRoot());
			}
			return node;
		}
		
		@Override
		public boolean hasNext() {
			if (queue.isEmpty()) return false;
			if (queue.peek().key == null) return false;
			return true;
		}
	}
	
	
	private static class HelpIterator<T extends Comparable<T>> implements Iterator<T> {

		private SearchType<T> type;
		
		/**
		 * @param stack
		 */
		public HelpIterator(MyBinarySearchTree<T> tree, SearchType<T> type) {
			this.type = type;
			type.push(tree.getRoot());
		}

		@Override
		public boolean hasNext() {
			return type.hasNext();
		}

		@Override
		public T next() {
			return (T) type.pop().key;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		
	}
	
	/**
	 * @return
	 */
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public Iterator<T> iterator() {
		return dfsIterator();
	}
	

}
