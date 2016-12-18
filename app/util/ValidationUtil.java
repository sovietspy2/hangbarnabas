package util;

public class ValidationUtil {
	public static boolean isEmpty(String input){
		return (input == null || input.trim().isEmpty());
	}
	
	/**
	 * Tegyük olvasmányossá a kódot!
	 * @param input
	 * @return
	 * Ez az orai uitl 
	 */
	public static boolean isNotEmpty(String input){
		return !isEmpty(input);
	}
	
	public static boolean isInteger(String input){
		boolean isNumber = false;
		if (ValidationUtil.isNotEmpty(input)){
			try {
				Integer.valueOf(input);
				isNumber = true;
			} catch (NumberFormatException nfe){
				//Ha idejut a kód, akkor a string nem szám
			}
		} 
		
		return isNumber;
	}
}
