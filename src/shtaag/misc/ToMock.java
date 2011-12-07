/**
 * 
 */
package shtaag.misc;

/**
 * @author takei_s
 * @Date 2011/12/06
 * 
 */
public class ToMock {
	
	private final String finalField;
	private final int finalPrimitive;

	private String privateField;
	public Integer test = Integer.valueOf(1);
	
	public ToMock(String finalField, int finalPrimitive, String privateField) {
		this.finalField = finalField;
		this.finalPrimitive = finalPrimitive;
		this.privateField = privateField;
	}
	
	public String getFinalField() {
		return finalField;
	}
	public int getFinalPrimitive() {
		return finalPrimitive;
	}
	
	public String getPrivateField() {
		return privateField;
	}
	

}
