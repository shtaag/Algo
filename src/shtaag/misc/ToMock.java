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

	private String privateField;
	public Integer test = Integer.valueOf(1);
	
	public ToMock(String finalField, String privateField) {
		this.finalField = finalField;
		this.privateField = privateField;
	}
	
	public String getFinalField() {
		return finalField;
	}
	
	public String getPrivateField() {
		return privateField;
	}
	

}
