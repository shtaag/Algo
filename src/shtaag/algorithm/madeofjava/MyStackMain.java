/**
 * 
 */
package shtaag.algorithm.madeofjava;

/**
 * @author takei_s
 * @date 2011/10/15
 */
public class MyStackMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MyStack_ArrayList<Integer> stack = new MyStack_ArrayList<Integer>(); 
		
		stack.push(Integer.valueOf(1));
		stack.push(Integer.valueOf(2));
		stack.push(Integer.valueOf(3));
		stack.push(Integer.valueOf(4));
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

}
