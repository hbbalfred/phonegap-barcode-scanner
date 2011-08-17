package com.urenwu.phonegap.plugin.barcodescanner;

public class BridgeR {

	private static String pkg;
	
	/**
	 * get the value of R by expression
	 * @param exp R.color.blue
	 * @return
	 */
	public static int get(String exp){
		String[] arr = exp.split("[.]");
		String clsName = pkg + "." + arr[0] + "$" + arr[1];
		Class<?> cls;
		int result = 0;
		try{
			cls = Class.forName(clsName);
			result = cls.getField(arr[2]).getInt(null);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * init bridge something likes the class package of R
	 * @param pkg
	 */
	public static void init(String pkg){
		BridgeR.pkg = pkg;
	}
	
}
