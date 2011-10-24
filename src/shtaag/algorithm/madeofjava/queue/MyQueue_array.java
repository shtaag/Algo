/**
 * 
 */
package shtaag.algorithm.madeofjava.queue;

/**
 * @author takei_s
 * @date 2011/10/15
 */
public class MyQueue_array<T> {
	
	private Object[] innerArray;
	private int max;
	private int firstIndex;
	private int lastIndex;
	
	public MyQueue_array(int max) {
		this.max = max;
		innerArray = new Object[max];
	}
	
	public void queue(T elem) {
		if (lastin)
		innerArray[lastIndex++] = elem;
	}
	
	
 
}
