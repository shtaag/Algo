/**
 * 
 */
package shtaag.algorithm.data.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author takei_s
 * @date 2011/12/04
 */
public class DirectedGraph<K> {
	
	/**
	 * in each list, the element at index 0 is the root for the children.
	 */
	private Map<K, List<Node<K>>> adjListMap = new HashMap<K, List<Node<K>>>(); 
	private EdgeMap<K> edgeMap = new EdgeMap<K>();
	
	public int getEdgeWeight(K parent, K child) {
		return edgeMap.getWeight(parent, child);
	}
	
	public List<Node<K>> getAdjList(K target) {
		return adjListMap.get(target);
	}
	
	public void relax(K parent, K child) {
		if (parent.equals(child)) {
			return;
		}
		if (! adjListMap.containsKey(parent)) {
			throw new NoSuchElementException("The parent is invalid: " + parent);
		}
		if (! adjListMap.containsKey(child)) {
			throw new NoSuchElementException("The end point is invalid: " + child);
		}
		
		Node<K> parentNode = adjListMap.get(parent).get(0);
		Node<K> childNode = adjListMap.get(child).get(0);
		
		int parentDist = parentNode.getDistance();
		int childDist = childNode.getDistance();
		int edgeWeight = getEdgeWeight(parent, child);
		if (childDist > (parentDist + edgeWeight)) {
			childNode.setDistance(parentDist + edgeWeight);
			childNode.setPre(parentNode);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (List<Node<K>> adjList: adjListMap.values()){
			Node<K> node = adjList.get(0);
			sb.append(node.key + ": cost=" + node.getDistance() + " / ");
		}
		return sb.toString();
	}
	
	public void initialize(K start) {
		for (Entry<K, List<Node<K>>> entry : adjListMap.entrySet()) {
			Node<K> node = entry.getValue().get(0);
			node.setDistance(Node.MAX);
			node.setPre(null);
			if (node.key.equals(start)) {
				node.setDistance(0);
			}
		}
		
	}
	
	public Set<Node<K>> nodes() {
		Set<Node<K>> set = new HashSet<Node<K>>();
		for (Entry<K, List<Node<K>>> entry : adjListMap.entrySet()) {
			Node<K> node = entry.getValue().get(0);
			set.add(node);
		}
		return set;
	}
	
	public void printPath(K start, K destination) {
		if (! adjListMap.containsKey(start)) {
			throw new NoSuchElementException("The start point is invalid: " + start);
		}
		if (! adjListMap.containsKey(destination)) {
			throw new NoSuchElementException("The end point is invalid: " + destination);
		}
		
		Node<K> startNode = adjListMap.get(start).get(0);
		Node<K> destNode = adjListMap.get(destination).get(0);
		if (destNode.equals(startNode)) {
			System.out.println(start + " >> ");
		} else if (destNode.getPre() == null) {
			System.out.println("No path from " + start + " to " + destination + " exists.");
		} else {
			printPath(start, destNode.getPre().key);
			System.out.println(destination + " cost:" + destNode.getDistance() + " >> ");
		}
		
	}
		
	private static class EdgeMap<K> {
		Map<K, Map<K, Integer>> parentMap = new HashMap<K, Map<K,Integer>>(); 
		
		private void insert(K parent, K child, int weight) {
			if (! parentMap.containsKey(parent)) {
				Map<K, Integer> newMap = new HashMap<K, Integer>();
				newMap.put(child, weight);
				parentMap.put(parent, newMap);
				return;
			}
			if (weight < 0) {
				throw new IllegalArgumentException("The weight must not be < 0");
			}
			Map<K, Integer> childMap = parentMap.get(parent);
			childMap.put(child, weight);
		}
		private int getWeight(K parent, K child) {
			Map<K, Integer> childMap = parentMap.get(parent);
			return childMap.get(child);
		}
	}

	public void setEdge(K parentKey, K childKey, int weight) {
		if (! adjListMap.containsKey(parentKey)) {
			throw new IllegalArgumentException("This key does not exists. ParentKey:" + parentKey);
		}
		if (! adjListMap.containsKey(childKey)) {
			throw new IllegalArgumentException("This key does not exists. ChildKey:" + childKey);
		}
		
		changeAdjacent(parentKey, childKey);
		edgeMap.insert(parentKey, childKey, weight);
	}
	
	/**
	 * @param parentKey
	 * @param childKey
	 */
	private void changeAdjacent(K parentKey, K childKey) {
		List<Node<K>> adjList = adjListMap.get(parentKey);
		Node<K> child = adjListMap.get(childKey).get(0);
		adjList.add(child);
		
	}

	public void addNode(K key) {
		
		if (adjListMap.containsKey(key)) {
			throw new IllegalArgumentException("This key already exists. Key:" + key);
		}
		
		Node<K> newOne = new Node<K>(key);
		
		List<Node<K>> adjList = new LinkedList<Node<K>>();
		adjList.add(newOne);
		adjListMap.put(key, adjList);
		
	}

}
