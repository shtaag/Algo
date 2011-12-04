/**
 * 
 */
package shtaag.algorithm.data.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


/**
 * @author takei_s
 * @date 2011/12/04
 */
public class Dijkstra<K> {
	
	public void search(DirectedGraph<K> graph, K start) { 
		initializeSingleSource(graph, start);
		Set<Node<K>> set = new HashSet<Node<K>>();
		Queue<Node<K>> queue = new PriorityQueue<Node<K>>();
		for (Node<K> node : graph.nodes()) {
			queue.add(node);
		}
		while (! queue.isEmpty()) {
			Node<K> current = queue.poll();
			set.add(current);
			for (Node<K> child : graph.getAdjList(current.key)) {
				relax(graph, current.key, child.key);
			}
		}
		
	}
	
	private void initializeSingleSource(DirectedGraph<K> graph, K start) { 
		graph.initialize(start);
	}
	
	private void relax(DirectedGraph<K> graph, K parent, K child) {
		graph.relax(parent, child);
	}

}
