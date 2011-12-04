/**
 * 
 */
package shtaag.algorithm.data.graph;


/**
 * @author takei_s
 * @date 2011/12/04
 */
public class Node<K> implements Comparable<Node<K>> {
	
	public static int MAX = 9999999;
	private int distance;
	private Node<K> pre;
	public final K key;
	public Node(K key) {
		this.key = key;
	}
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	/**
	 * @return the pre
	 */
	public Node<K> getPre() {
		return pre;
	}
	/**
	 * @param pre the pre to set
	 */
	public void setPre(Node<K> pre) {
		this.pre = pre;
	}
	@Override
	public int compareTo(Node<K> o) {
		return distance - o.distance;
	}
	
}
