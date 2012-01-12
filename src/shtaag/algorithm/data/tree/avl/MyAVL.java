/**
 * 
 */
package shtaag.algorithm.data.tree.avl;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * @author takei_s
 * @Date 2012/01/12
 * 
 */
public class MyAVL<T extends Comparable<T>> implements Iterable<T> {
	
	private static class Node<T extends Comparable<T>> {
		public final T key;
		private MyAVL<T> rTree;
		private MyAVL<T> lTree;
		
		public Node(T key) {
			this.key = key;
		}
		private MyAVL<T> getRTree() {
			return rTree;
		}
		private MyAVL<T> getLTree() {
			return lTree;
		}
	}
	
	private Node<T> root;
	
	private Node<T> getRoot() {
		return root;
	}
	
	public void insert(T key) {
		if (root == null) {
			root = new Node<T>(key);
		}
		
		if (key > root.key) {
			root.rTree.insert(key);
		} else if (key < root.key){
			root.lTree.insert(key);
		} else {
			System.out.println("That element is already in.");
			return;
		}
		
	}
	
	public void delete(T key) {
		
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

		private Stack<Node<T>> stack = new Stack<MyAVL.Node<T>>(); 
		@Override
		public void push(Node<T> node) {
			stack.push(node);
		}

		@Override
		public Node<T> pop() {
			Node<T> node = stack.pop();
			if (! node.getRTree().isEmpty()) {
				stack.push(node.getRTree().getRoot());
			}
			if (! node.getLTree().isEmpty()) {
				stack.push(node.getLTree().getRoot());
			}
			return node;
		}

		@Override
		public boolean hasNext() {
			return ! stack.empty();
		}
	}
	
	private static class bfsType<T extends Comparable<T>> implements SearchType<T> {
		
		private Queue<Node<T>> queue = new ArrayDeque<MyAVL.Node<T>>(); 
		@Override
		public void push(Node<T> node) {
			queue.add(node);
		}
		
		@Override
		public Node<T> pop() {
			Node<T> node = queue.poll();
			if (! node.getRTree().isEmpty()) {
				queue.add(node.getRTree().getRoot());
			}
			if (! node.getLTree().isEmpty()) {
				queue.add(node.getLTree().getRoot());
			}
			return node;
		}
		
		@Override
		public boolean hasNext() {
			return ! queue.isEmpty();
		}
	}
	
	
	private static class HelpIterator<T extends Comparable<T>> implements Iterator<T> {

		private SearchType<T> type;
		
		/**
		 * @param stack
		 */
		public HelpIterator(MyAVL<T> tree, SearchType<T> type) {
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
