/**
 * 
 */
package shtaag.algorithm.data.tree.bstree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Binary Search Tree with bfs, dfs.<br>
 * Made Node in shape of Key + tree, tree, such as functional programming's recursive datatype.<br>
 * This form is beneficial to write insert compactly.<br>
 * When a new Node is created, it has null rTree and lTree. This is not so good.<br>
 * In Introduction to Algorithms, they used pointer to parent node, but here I don't use it, cause it is no use. 
 * @author takei_s
 * @Date 2012/01/12
 * 
 */
public class MyBinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
	
	static class Node<T extends Comparable<T>> {
		private T key;
		private MyBinarySearchTree<T> rTree;
		private MyBinarySearchTree<T> lTree;
		
		public Node(T key) {
			this.key = key;
		}
		public T getKey() {
			return key;
		}
		public MyBinarySearchTree<T> getRTree() {
			return rTree;
		}
		public MyBinarySearchTree<T> getLTree() {
			return lTree;
		}
	}
	
	private Node<T> root;
	private SearchType<T> searchType;
	public MyBinarySearchTree(T key, SearchType<T> searchType) {
		this.root = new Node<T>(key);
		this.searchType = searchType;
	}
	
	Node<T> getRoot() {
		return root;
	}
	
	public void insert(T key) {
		if (root.key == null) {
			root = new Node<T>(key);
			return;
		}
		
		if (key.compareTo(root.key) < 0) {
			if (root.lTree == null) {
				root.lTree = new MyBinarySearchTree<T>(key, searchType);
				return;
			}
			root.lTree.insert(key);
		} else if (key.compareTo(root.key) > 0){
			if (root.rTree == null) {
				root.rTree = new MyBinarySearchTree<T>(key, searchType);
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
		
		for (Iterator<T> it = new HelpIterator<T>(this, new DfsType<T>()); it.hasNext(); ) {
			T current = it.next(); 
			if (current == key) {
				return current;
			}
		}
		throw new NoSuchElementException();
	}
	
	public T breadthFirstSearch(T key) {
		for (Iterator<T> it = new HelpIterator<T>(this, new BfsType<T>()); it.hasNext(); ) {
			T current = it.next(); 
			if (current == key) {
				return current;
			}
		}
		throw new NoSuchElementException();
		
	}
	
	private static class HelpIterator<T extends Comparable<T>> implements Iterator<T> {

		private SearchType<T> type;
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
	
	@Override
	public Iterator<T> iterator() {
		return new HelpIterator<T>(this, searchType);
	}
	

}
