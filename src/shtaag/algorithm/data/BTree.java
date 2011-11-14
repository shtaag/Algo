/**
 * 
 */
package shtaag.algorithm.data;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author takei_s
 * @Date 2011/11/12
 * 
 */
public class BTree<K extends Comparable<K>, V> implements Iterable<V> {
	
	final int minDegree;
	private Node<K, V> nodeInfo;
	private Node<K, V> root;
	
	private static class Node<K, V> {
		
		boolean isLeaf;
		boolean isRoot;
		//TODO why this cant be Element<K,V>[]??
		Object[] elementArray;
		Object[] childArray;
		int elementSize;
		int childSize;
		Node(int minDegree) {
			elementArray = new Object[minDegree*2-1];
			childArray = new Object[minDegree*2-1];
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
	
	public BTree(int minDegree) {
		this.minDegree = minDegree;
		nodeInfo = new Node<K, V>(minDegree);
		nodeInfo.isLeaf = true;
		root = nodeInfo;
		nodeInfo.isRoot = true;
		nodeInfo.elementSize = 0;
		nodeInfo.childSize = 0;
	}
	
	public V search(K key) {
		return btreeSearch(root, key);
	}
	private V btreeSearch(Node<K, V> node, K key) {
		int i = 0;
		while (i <= node.elementSize && key.compareTo(((Element<K, V>)node.elementArray[i]).key) > 0) {
			i++;
		}
		if (i <= node.elementSize && key.equals(((Element<K, V>)node.elementArray[i]).key) ) {
			return ((Element<K, V>)node.elementArray[i]).value;
		} else if (node.isLeaf) {
			throw new NoSuchElementException();
		} else {
			return btreeSearch((Node<K, V>) node.childArray[i], key);
		}
	}

	@Override
	public Iterator<V> iterator() {
		return new NodeIterator(root);
	}
	private class NodeIterator implements Iterator<V> {

		private Stack<Node<K, V>> nodeStack = new Stack<Node<K, V>>();
		private Stack<Element<K, V>> elemStack = new Stack<Element<K, V>>();
		
		NodeIterator(Node<K, V> root) {
			for (int i = 0;  i < root.elementSize; i++) {
				elemStack.push((Element<K, V>) root.elementArray[i]);
			}
			for (int i = 0;  i < root.childSize; i++) {
				nodeStack.push((Node<K, V>) root.childArray[i]);
			}
		}
		@Override
		public boolean hasNext() {
			return ! (elemStack.empty() && nodeStack.empty());
		}

		@Override
		public V next() {
			if (! elemStack.empty()) {
				Element<K, V> targetElem = elemStack.pop();
				return targetElem.value;
			}
			if (nodeStack.empty()) {
				throw new NoSuchElementException();
			}
			Node<K, V> targetNode = nodeStack.pop();
			for (int i = 0;  i < targetNode.elementSize; i++) {
				elemStack.push((Element<K, V>) targetNode.elementArray[i]);
			}
			for (int i = 0;  i < targetNode.childSize; i++) {
				nodeStack.push((Node<K, V>) targetNode.childArray[i]);
			}
			if (! elemStack.empty()) {
				Element<K, V> targetElem = elemStack.pop();
				return targetElem.value;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
	}

	public void insert(final BTree<K, V> tree, K key, V value) {
		Node<K, V> exRoot = tree.root;
		if (tree.root.elementSize == minDegree * 2 -1) {
			Node<K, V> newNode = new Node<K, V>(minDegree);
			tree.root = newNode;
			newNode.isLeaf = false;
			newNode.elementSize = 0;
			newNode.childArray[0] = exRoot;
			newNode.childSize = 1;
			btreeSplitChild(newNode, 0);
			btreeInsertNonFull(newNode, key, value);
		} else {
			btreeInsertNonFull(root, key, value);
		}
	}
	
	private void btreeInsertNonFull(Node<K, V> node, final K key, final V value) {
		int index = node.elementSize - 1;
		if (node.isLeaf) {
			if (index != -1) {
				while (index >= 0 && key.compareTo(((Element<K,V>)node.elementArray[index]).key) < 0) {
					node.elementArray[index + 1] = node.elementArray[index];
					index--;
				}
			}
			node.elementArray[index+1] = new Element<K, V>(key, value);
			node.elementSize++;
		} else {
			while (index >= 0 && key.compareTo(((Element<K,V>) node.elementArray[index]).key) < 0) {
				index--;
			}
			index++;
			if (((Node<K, V>)node.childArray[index]).elementSize == minDegree * 2 - 1) {
				btreeSplitChild(node, index);
				if (key.compareTo(((Element<K, V>)node.elementArray[index]).key) > 0) {
					index++;
				}
			}
			btreeInsertNonFull((Node<K, V>) node.childArray[index], key, value);
		}
	}
	
	private void btreeSplitChild(Node<K, V> node, final int index) {
		Node<K, V> newNode = new Node<K, V>(minDegree);
		Node<K, V> exNode = (Node<K, V>) node.childArray[index];
		
		newNode.isLeaf = exNode.isLeaf;
		
		for (int j = 0; j < minDegree - 1; j++) {
			newNode.elementArray[j] = exNode.elementArray[j + minDegree];
			exNode.elementArray[j + minDegree] = null;
		}
		newNode.elementSize = minDegree - 1;
		exNode.elementSize = minDegree - 1;
		
		if ( ! exNode.isLeaf) {
			for (int j = 0; j < minDegree; j++) {
				newNode.childArray[j] = exNode.childArray[j + minDegree];
				exNode.childArray[j + minDegree] = null;
				exNode.childSize--;
				
			}
			newNode.childSize = minDegree;
		}

		for (int j = node.childSize-1; j >= index; j--) {
			node.childArray[j + 1] = node.childArray[j];
		}
		node.childArray[index + 1] = newNode;
		node.childSize++;
		if (node.elementSize != 0) {
			for (int j = node.elementSize; j >= index; j--) {
				node.elementArray[j - 1] = node.elementArray[j - 2];
			}
		}
		node.elementArray[index] = exNode.elementArray[minDegree-1];
		exNode.elementArray[minDegree - 1] = null;
		node.elementSize++;
	}


}
