/**
 * 
 */
package shtaag.algorithm.data.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author takei_s
 * @date 2011/12/05
 */
public class DijkstraTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link shtaag.algorithm.data.graph.Dijkstra#search(shtaag.algorithm.data.graph.DirectedGraph, java.lang.Object)}.
	 */
	@Test
	public void testSearch() {
		DirectedGraph<String> graph = new DirectedGraph<String>();
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addNode("D");
		
		graph.setEdge("A", "B", 1);
		graph.setEdge("A", "C", 1);
		graph.setEdge("A", "D", 1);
		
		graph.setEdge("B", "C", 3);
		graph.setEdge("B", "D", 5);
		
		graph.setEdge("C", "D", 6);
		
		new Dijkstra<String>().search(graph, "A");
		System.out.println("Print out Graph path:");
		graph.printPath("A", "D");
	}

}
