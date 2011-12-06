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
public class ReflectionTest {
	
	public static void main(String[] args) {
		
		ReflectionTest instance = new ReflectionTest();
		try {
			instance.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void test() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		String finalField = "final-immutable";
		String privateField = "private-immutable";
		ToMock mock = new ToMock(finalField, Integer.valueOf(3));
		
		Class<? extends ToMock> clazz = mock.getClass();
		Field[] fields = clazz.getFields();
		System.out.println(fields.length);
		for (Field each : fields) {
			System.out.println(each.toString());
		}
		Field fin = clazz.getField("test");
		fin.setAccessible(true);
		fin.set(fin, finalField);
		
		System.out.println(mock.getFinalField());
		
	}

}
