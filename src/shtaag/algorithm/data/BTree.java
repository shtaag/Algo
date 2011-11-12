/**
 * 
 */
package shtaag.algorithm.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author takei_s
 * @Date 2011/11/12
 * 
 */
public class BTree<K extends Comparable<K>, V> implements Iterable<BTree<K, V>> {
	
	private int minDegree;
	private Node<K, V> nodeInfo;
	
	private static class Node<K, V> {
		
		boolean leaf;
		private boolean root;
		Object[] elementArray;
		Object[] childArray;
		int elementSize;
		Node() {
			elementArray = new Object[]{};
			childArray = new Object[]{};
		}
		
	}
	
	private class EmptyNode extends BTree<K, V>{

		public EmptyNode(int minDegree) {
			super(minDegree);
		}
		
	}
	
	private static class Element<K, V> {
		K key;
		V value;
		Element(K key, V value) { 
			this.key = key;
			this.value = value;
		}
	}
	
//	private static class Element<K extends Comparable<K>, V> implements Comparable<K> {
//		
//		private K key;
//		private V value;
//		private Element(K key, V value) {
//			this.key = key;
//			this.value = value;
//		}
//		private int compareTo(Element<K, V> elem) {
//			int val = key.compareTo(elem.key);
//			return val == 0 ? 
//		}
//		
//	}
	
	public BTree(int minDegree) {
		this.minDegree = minDegree;
		nodeInfo = new Node<K, V>();
		nodeInfo.root = true;
	}
	
	public V search(K key) {
		return btreeSearch(root, key);
	}
	private V btreeSearch(Node<K, V> node, K key) {
		int i = 1;
		while ((i <= node.getKeySize()) && (key.compareTo(node.getElement(i).key) > 0)) {
			i++;
			if (i <= node.getKeySize() && (key.equals(node.getElement(i)))) {
				return node.getElement(i).value;
			} else if (node.leaf) {
				return null;
			} else {
				return btreeSearch(node.childArray[i], key);
			}
		}
		return null;
	}
	
	// static‚É‚Å‚«‚È‚¢EE
	private class NodeIterator implements Iterator<BTree<K, V>> {

		private Stack<BTree<K, V>> stack = new Stack<BTree<K,V>>();
		BTree<K, V> current;
		
		NodeIterator(BTree<K, V> root) {
			current = root;
			stack.push(root);
		}
		@Override
		public boolean hasNext() {
			return ! stack.empty();
		}

		@Override
		public BTree<K, V> next() {
			if (stack.empty()) {
				return new EmptyNode(minDegree);
			}
			BTree<K, V> target = stack.pop();
			int i = 0;
			while (i < target.nodeInfo.elementSize - 1) {
				stack.push((BTree<K, V>) target.nodeInfo.childArray[i]);
			}
			
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	private btreeSplitChild(Node<K, V> node, final int index) {
		Node<K, V> newNode = new Node<K, V>();
		Node<K, V> exNode = node.childArray[index];
		
		newNode.leaf = exNode.leaf;
		// z.n = maxarrsize -1;
		// 
		for (int j = 0; j < minDegree - 1; j++) {
			newNode.elementArray[j] = exNode.elementArray[j + minDegree];
		}
		if ( ! exNode.leaf) {
			for (int j = 0; j < minDegree; j++) {
				newNode.childArray[j] = exNode.childArray[j + minDegree];
			}
		}
		for (int j = node.getKeySize(); j > index; j--) {
			node.childArray[j + 1] = node.childArray[j];
		}
		
		node.childArray[index + 1] = newNode;
		
		for (int j = node.getKeySize(); j >= index; j--) {
			node.elementArray[j + 1] = node.elementArray[j];
		}
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<BTree<K, V>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
