/**
 * 
 */
package shtaag.algorithm.madeofjava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author takei_s
 * @date 2011/10/15
 */
public class MyStack_ArrayList<T> {
	
	private List<T> innerList;
	
	public MyStack_ArrayList() {
		innerList = new ArrayList<T>();
	}
	
	public void push(T elem) {
		innerList.add(elem);
	}
	
	public T pop() {
		T temp = innerList.get(innerList.size()-1);
		innerList.remove(innerList.size()-1);
		return temp;
	}

}
