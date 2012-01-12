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
		graph.addNode("E");
		graph.addNode("F");
		graph.addNode("G");
		graph.addNode("H");
		
		graph.setEdge("A", "B", 1);
		graph.setEdge("A", "C", 6);
		graph.setEdge("A", "D", 8);
		graph.setEdge("A", "E", 10);
		graph.setEdge("A", "F", 20);
		graph.setEdge("A", "G", 40);
		graph.setEdge("A", "H", 100);
		
		graph.setEdge("B", "A", 2);
		graph.setEdge("B", "C", 2);
		graph.setEdge("B", "D", 5);
		graph.setEdge("B", "E", 5);
		graph.setEdge("B", "F", 5);
		graph.setEdge("B", "G", 5);
		graph.setEdge("B", "H", 5);
		
		graph.setEdge("C", "A", 1);
		graph.setEdge("C", "B", 1);
		graph.setEdge("C", "D", 1);
		graph.setEdge("C", "E", 1);
		graph.setEdge("C", "F", 1);
		graph.setEdge("C", "G", 1);
		graph.setEdge("C", "H", 1);

		graph.setEdge("D", "A", 1);
		graph.setEdge("D", "B", 1);
		graph.setEdge("D", "C", 1);
		graph.setEdge("D", "E", 1);
		graph.setEdge("D", "F", 1);
		graph.setEdge("D", "G", 1);
		graph.setEdge("D", "H", 1);
		
		graph.setEdge("E", "A", 1);
		graph.setEdge("E", "D", 1);
		graph.setEdge("E", "F", 1);
		graph.setEdge("E", "H", 1);

		graph.setEdge("F", "A", 1);
		graph.setEdge("F", "B", 1);
		graph.setEdge("F", "C", 1);
		graph.setEdge("F", "D", 1);
		
		graph.setEdge("G", "A", 1);
		graph.setEdge("G", "D", 1);
		graph.setEdge("G", "E", 1);
		graph.setEdge("G", "F", 1);

		graph.setEdge("H", "A", 1);
		graph.setEdge("H", "B", 1);
		graph.setEdge("H", "D", 1);
		graph.setEdge("H", "F", 1);

		
		new Dijkstra<String>().search(graph, "A");
		System.out.println(graph.toString());
		System.out.println("Print out Graph path:");
		graph.printPath("A", "A");
		System.out.println("Print out Graph path:");
		graph.printPath("A", "C");
		System.out.println("Print out Graph path:");
		graph.printPath("A", "D");
		
		new Dijkstra<String>().search(graph, "B");
		System.out.println(graph.toString());
		System.out.println("Print out Graph path:");
		graph.printPath("B", "A");
		System.out.println("Print out Graph path:");
		graph.printPath("C", "D");
		System.out.println("Print out Graph path:");
		graph.printPath("B", "C");
		System.out.println("Print out Graph path:");
		graph.printPath("B", "D");
	}

}
