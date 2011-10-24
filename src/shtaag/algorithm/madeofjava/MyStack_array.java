/**
 * 
 */
package shtaag.algorithm.madeofjava;

import java.util.NoSuchElementException;

/**
 * @author takei_s
 * @date 2011/10/15
 */
public class MyStack_array<T> {
	
	private Object[] innerArray;
	private int top;
	private int max;
	
	public MyStack_array(int max) {
		innerArray = new Object[max];
	}
	
	public void push(T elem) {
		if (top == max-1) {
			throw new IndexOutOfBoundsException();
		}
		innerArray[++top] = elem;
	}
	
	/**
	 * 配列に対して破壊的にpushするため、明示的にremoveする必要はない
	 * @return
	 */
	public T pop() {
		if (top == 0) {
			throw new NoSuchElementException();
		}
		return (T) innerArray[--top];
	}

}
