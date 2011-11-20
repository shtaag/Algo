/**
 * 
 */
package shtaag.algorithm.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author takei_s
 * @Date 2011/11/12
 * 
 */
@SuppressWarnings("unchecked")
public class BTree<K extends Comparable<K>, V> implements Iterable<V> {
	
	final int minDegree;
	private Node<K, V> nodeInfo;
	private Node<K, V> root;
	
	private static class Node<K, V> {
		
		final boolean isLeaf;
		boolean isRoot;
		//TODO why this cant be Element<K,V>[]??
		Object[] elementArray;
		Object[] childArray;
		int elementSize;
		int childSize;
		Node(boolean isLeaf, int minDegree) { // staticなのでminDegreeを渡さなければならない
			this.isLeaf = isLeaf;
			elementArray = new Object[minDegree*2-1];
			childArray = new Object[minDegree*2];
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
		nodeInfo = new Node<K, V>(true, minDegree);
		root = nodeInfo;
		nodeInfo.isRoot = true;
		nodeInfo.elementSize = 0;
		nodeInfo.childSize = 0;
	}
	
	public V search(K key) {
		return searchRecursive(root, key);
	}
	private V searchRecursive(Node<K, V> node, K key) {
		int i = 0;
		while (i <= node.elementSize - 1 && key.compareTo(((Element<K, V>)node.elementArray[i]).key) > 0) {
			i++;
		}
		if (i <= node.elementSize - 1 && key.equals(((Element<K, V>)node.elementArray[i]).key) ) {
			return ((Element<K, V>)node.elementArray[i]).value;
		} else if (node.isLeaf) {
			throw new NoSuchElementException();
		} else {
			return searchRecursive((Node<K, V>) node.childArray[i], key); // child exists between key and index
		}
	}
	private int searchCurrentNode(Node<K, V> node, K key) {
		int i = 0;
		while (i <= node.elementSize - 1 && key.compareTo(((Element<K, V>)node.elementArray[i]).key) > 0) {
			i++;
		}
		if (i <= node.elementSize - 1 && key.equals(((Element<K, V>)node.elementArray[i]).key) ) {
			return i;
		} else {
			throw new NoSuchElementException();
		} 
	}
	private static class ResultTaple<K, V> {
		final Node<K, V> parent;
		final int keyIndex;
		final int childIndex;
		ResultTaple(Node<K,V> parent, int keyIndex, int childIndex) {
			this.parent = parent;
			this.keyIndex = keyIndex;
			this.childIndex = childIndex;
		}
	}
	private ResultTaple<K, V> searchChildToDelete(Node<K, V> parent, int childIndex, K key) {
		int i = 0;
		Node<K, V> node = (Node<K, V>)parent.childArray[childIndex];
		if (node == null) {
			return null;
		}
		while (i <= node.elementSize - 1 && key.compareTo(((Element<K, V>)node.elementArray[i]).key) > 0) {
			i++;
		}
		if (i <= node.elementSize - 1 && key.equals(((Element<K, V>)node.elementArray[i]).key) ) {
			return new ResultTaple<K, V>(parent, i, childIndex);
		} else if (node.isLeaf) {
			return null;
		} else {
			return searchChildToDelete(node, i, key); // child exists between key and index
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
			Node<K, V> newNode = new Node<K, V>(false, minDegree);
			tree.root = newNode;
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
				while (index >= 0 && key.compareTo(((Element<K,V>)node.elementArray[index]).key) <= 0) {
					if (key.equals(((Element<K,V>)node.elementArray[index]).key)) {
						throw new IllegalArgumentException("There already exists same key.");
					}
					node.elementArray[index + 1] = node.elementArray[index];
					index--;
				}
			}
			node.elementArray[index + 1] = new Element<K, V>(key, value);
			node.elementSize++;
		} else {
			while (index >= 0 && key.compareTo(((Element<K,V>) node.elementArray[index]).key) <= 0) {
				if (key.equals(((Element<K,V>)node.elementArray[index]).key)) {
					throw new IllegalArgumentException("There already exists same key.");
				}
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
		Node<K, V> exNode = (Node<K, V>) node.childArray[index];
		Node<K, V> newNode = new Node<K, V>(exNode.isLeaf, minDegree);
		
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

		for (int j = node.childSize - 1; j >= index; j--) {
			node.childArray[j + 1] = node.childArray[j];
		}
		node.childArray[index + 1] = newNode;
		node.childSize++;
		if (index < node.elementSize) { // certify the index is at middle of elementArray
			for (int j = node.elementSize - 1; j >= index; j--) {
				// index is the position in childArray. 
				node.elementArray[j + 1] = node.elementArray[j];
			}
		}
		node.elementArray[index] = exNode.elementArray[minDegree-1]; // push up to parent node
		exNode.elementArray[minDegree - 1] = null;
		node.elementSize++;
	}
	
	public void show() {
		System.out.println("+++ Here's the current condition of your B-Tree!! +++");
		List<Node<K,V>> list = new ArrayList<BTree.Node<K,V>>();
		list.add(root);
		showElements(list, 0);
	}

	private void showElements(List<Node<K, V>> nodeList, int height) {
		StringBuilder sb = new StringBuilder();
		List<Node<K,V>> childList = new ArrayList<Node<K,V>>();
		sb.append("Height: " + height + " >>> ");
		sb.append("Elems: ");
		for (Node<K, V> node: nodeList) {
			for (int i = 0; i < node.elementSize; i++) {
				sb.append(((Element<K, V>)node.elementArray[i]).key + " ");
			}
			sb.append("|| ");
			if (! node.isLeaf) {
				for (int i = 0; i < node.childSize; i++) {
					childList.add((Node<K,V>)node.childArray[i]);
				}
			}
		}
		System.out.println(sb.toString());
		if (childList.isEmpty()) {
			// if it is leaf, stop recurtion.
			return;
		}
		height++;
		showElements(childList, height);
	}
	
	public void delete(K key) throws NoSuchElementException {
		// in case of no target, NoSuchElementException is thrown.
		try {
			// search root
			int index = searchCurrentNode(root, key);
			//delete from root
			if (root.isLeaf) {
				deleteAt(root, index); //parentがないので、deleteHelpのDeleteInLeafは使えない。
			} else {
				deleteAtInternalNode(root, index);
			}
		} catch (NoSuchElementException e) { // 例外をロジックに使うべきではないが、、index == -1と比べるとまだ分り易くないか？
			// search into child
			ResultTaple<K, V> target = searchChildToDelete(root, 0, key);
			deleteHelp(target, key);
		}
	}
	
	private void deleteHelp(ResultTaple<K, V> target, K key) {
		Node<K, V> targetNode = (Node<K,V>)target.parent.childArray[target.childIndex];
		Node<K, V> parent = target.parent;
		int keyIndex = target.keyIndex;
		int childIndexAtParent = target.childIndex;
		if (! targetNode.isLeaf) {
			deleteAtInternalNode(targetNode, keyIndex);
		} else {
			deleteInLeaf(targetNode, parent, keyIndex, childIndexAtParent);
		}
	}
	
	private void deleteAt(Node<K, V> node, int index) {
		shrinkArray(node.elementArray, node.elementSize, index);
		node.elementSize--;
	}
	private <E> void shrinkArray(Object[] array, int size, int index) {
		for (int i = index; i <= size - 2; i++) {
			array[i] = array[i + 1];
		}
		array[size - 1] = null;
	}
	
	private void deleteAtInternalNode(
			Node<K,V> targetNode, int keyIndex) {
		Node<K, V> preChild = (Node<K, V>)targetNode.childArray[keyIndex];
		Node<K, V> posChild = (Node<K, V>)targetNode.childArray[keyIndex + 1];
		if (preChild.elementSize >= minDegree) { // Algorithm Introduction 2a
			Element<K, V> predecessor = (Element<K, V>)preChild.elementArray[preChild.elementSize - 1];
			targetNode.elementArray[keyIndex] = predecessor;
			preChild.elementArray[preChild.elementSize - 1] = null;
			preChild.elementSize--;
		} else {
			if (posChild.elementSize >= minDegree) { // Algorithm Introduction 2b
				Element<K, V> successor = (Element<K, V>)posChild.elementArray[0];
				targetNode.elementArray[keyIndex] = successor; // substitution
				// shrink posChild elementarray
				deleteAt(posChild, 0);
			} else { // Algo 2c
				// merge pre and pos
				for (int i = 0; i < posChild.elementSize; i++) {
					preChild.elementArray[i + preChild.elementSize] = posChild.elementArray[i];
					posChild.elementArray[i] = null;
				}
				preChild.elementSize += posChild.elementSize;
				
				// delete key and pointer to poschild
				shrinkArray(targetNode.elementArray, targetNode.elementSize, keyIndex);
				shrinkArray(targetNode.childArray, targetNode.childSize, keyIndex + 1);
				
				targetNode.elementSize--;
				targetNode.childSize--;
			}
		}
	}
	
	private void deleteInLeaf(
			Node<K, V> targetNode, Node<K, V> parent, int keyIndex, int childIndexAtParent) {
		if (targetNode.elementSize <= minDegree - 1) {
			// swap the target with successor
			Element<K, V> successor = (Element<K, V>)parent.elementArray[childIndexAtParent];
			targetNode.elementArray[keyIndex] = successor;
			// swap the successor with successor's soccessor in next sibling
			Node<K, V> nextSibling = (Node<K, V>)parent.childArray[childIndexAtParent + 1];
			Element<K, V> nextSuccessor = (Element<K, V>)nextSibling.elementArray[0];
			parent.elementArray[childIndexAtParent] = nextSuccessor;
			// shrink next sibling
			deleteAt(nextSibling, 0);
		} else {
			// delete element at keyIndex of existingNode
			deleteAt(targetNode, keyIndex);
		}
	}
	
}
