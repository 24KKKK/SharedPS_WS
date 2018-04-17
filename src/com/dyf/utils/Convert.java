package com.dyf.utils;

public class Convert {
	
	public static int genderToInt(String gender) {
		if (gender.equals("男")) {
			return 1;
		}
		else {
			return 2;
		}
	}
}
