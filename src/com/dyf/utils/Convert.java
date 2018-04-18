package com.dyf.utils;

import java.text.NumberFormat;

public class Convert {
	
	// 根据性别转成1或2
	public static int genderToInt(String gender) {
		if (gender.equals("男")) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	// 将double转换为string
		public static String doubleToString(double dou) {
			Double dou_obj = new Double(dou);
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			String dou_str = nf.format(dou_obj);
			return dou_str;
		}
}
