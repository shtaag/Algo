/**
 * 
 */
package shtaag.algorithm.data;

/**
 * @author takei_s
 * @Date 2011/10/28
 * 
 */
public class BinaryTree<E extends Comparable<E>> {
	
	private static class Node<E> {
		
		private E value;
		private Node parent;
		private Node left;
		private Node right;
		
		Node(E value, Node parent, Node left, Node right) {
			this.value = value;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public E getValue() {
			return value;
		}
		public Node getParent() {
			return parent;
		}
		public Node getLeft() {
			return left;
		}
		public Node getRight() {
			return right;
		}
		
	}
	
	public void insert(E value) {
		
	}
	
	private breadthFirstSearch(E value) {
		
	}

}
