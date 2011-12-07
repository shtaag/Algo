/**
 * 
 */
package shtaag.misc;

import java.lang.reflect.Field;

/**
 * @author takei_s
 * @Date 2011/12/06
 * 
 */
public class MockFinalField {
	
	public static void main(String[] args) {
		
		MockFinalField instance = new MockFinalField();
		try {
			instance.testSetAccessible();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			instance.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testSetAccessible() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		System.out.println(">> Fieldに対してsetAccessible(true)をセットする場合。");
		
		ToMock mock = new ToMock("fin-immutable", 10, "pri-immutable");
		
		System.out.println(mock.getFinalField()); // >> fin-immutableが表示される。
		System.out.println(mock.getFinalPrimitive()); // >> 10
		System.out.println(mock.getPrivateField()); // >> pri-immutableが表示される。
		
		Class<? extends ToMock> clazz = mock.getClass();
		Field fin = clazz.getDeclaredField("finalField");
		fin.setAccessible(true);
		fin.set(mock, "fin-mutable");
		
		Field finPrim = clazz.getDeclaredField("finalPrimitive");
		finPrim.setAccessible(true);
		finPrim.set(mock, 1000);

		Field pri = clazz.getDeclaredField("privateField");
		pri.setAccessible(true);
		pri.set(mock, "pri-mutable");
		
		System.out.println(mock.getFinalField()); // >> fin-mutableが表示される。
		System.out.println(mock.getFinalPrimitive()); // >> 1000
		System.out.println(mock.getPrivateField()); // >> pri-mutableが表示される。
		
	}
	
	public void test() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		System.out.println(">> Fieldに対してsetAccessible(true)をセットしない場合。");

		ToMock mock = new ToMock("fin-immutable", 10, "pri-immutable");
		
		Class<? extends ToMock> clazz = mock.getClass();
		Field fin = clazz.getDeclaredField("finalField");
		try {
			fin.set(mock, "fin-mutable");
		} catch (IllegalAccessException e) {
			System.out.println("setAccessible(true)が必要。");
		}
		
		Field finPrim = clazz.getDeclaredField("finalPrimitive");
		try {
			finPrim.set(mock, 1000);
		} catch (IllegalAccessException e) {
			System.out.println("setAccessible(true)が必要。");
		}

		Field pri = clazz.getDeclaredField("privateField");
		try {
			pri.set(mock, "pri-mutable");
		} catch (IllegalAccessException e) {
			System.out.println("setAccessible(true)が必要。");
		}
		
		System.out.println(mock.getFinalField()); // >> fin-immutableが表示される。
		System.out.println(mock.getFinalPrimitive()); // >> 10が表示される。
		System.out.println(mock.getPrivateField()); // >> pri-immutableが表示される。
		
	}
	
	

}
