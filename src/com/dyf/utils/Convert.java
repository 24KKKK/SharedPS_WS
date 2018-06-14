package com.dyf.utils;

import java.text.NumberFormat;

public class Convert {

	/**
	 * 根据性别转成1或2
	 */
	public static int genderToInt(String gender) {
		if (gender.equals("男")) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * 将double转换为string
	 * @param dou
	 * @return string
	 */
	public static String doubleToString(double dou) {
		Double dou_obj = new Double(dou);
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		String dou_str = nf.format(dou_obj);
		return dou_str;
	}

	/**
	 * 将5:20,2018-4-5时间改为05:20,2018-04-05
	 * @param dateTime字符串类型数组
	 *            starttime，endtime，startdate，enddate数组
	 * @return dateTime string类型数组
	 */
	public static String[] dateTimeToNormal(String[] dateTime) {
		// 修改时间格式
		for (int j = 0; j < 2; j++) {
			String second = "";
			String[] ss = dateTime[j].split(":");
			for (int i = 0; i < ss.length; i++) {
				if (ss[i].length() == 1) {
					ss[i] = "0" + ss[i];
				}
				second = second + ss[i];
			}
			StringBuffer sb = new StringBuffer(second);
			sb.insert(2, ":");
			dateTime[j] = sb.toString();
		}

		// 修改日期格式
		for (int j = 2; j < 4; j++) {
			String second = "";
			String[] ss = dateTime[j].split("-");
			for (int i = 0; i < ss.length; i++) {
				if (ss[i].length() == 1) {
					ss[i] = "0" + ss[i];
				}
				second = second + ss[i];
			}
			StringBuffer sb1 = new StringBuffer(second);
			sb1.insert(4, "-");
			StringBuffer sb2 = new StringBuffer(sb1.toString());
			sb2.insert(7, "-");
			dateTime[j] = sb2.toString();
		}
		return dateTime;
	}

	public static void main(String[] args) {
		String[] dateTime = { "2018-4-20", "2018-5-2" };
		for (int j = 0; j < 2; j++) {
			String second = "";
			String[] ss = dateTime[j].split("-");
			for (int i = 0; i < ss.length; i++) {
				if (ss[i].length() == 1) {
					ss[i] = "0" + ss[i];
				}
				second = second + ss[i];
			}
			StringBuffer sb1 = new StringBuffer(second);
			sb1.insert(4, "-");
			StringBuffer sb2 = new StringBuffer(sb1.toString());
			sb2.insert(7, "-");
			dateTime[j] = sb2.toString();
		}
		for (int k = 0; k < 2; k++)
			SysoUtils.print(dateTime[k]);
	}
}
