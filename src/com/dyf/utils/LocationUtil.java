package com.dyf.utils;

import java.net.URL;
import java.text.NumberFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LocationUtil {
	/**
	 * 传入一个地点的经纬度信息，返回地点所在的省市区数组，arr[].0 1 2分别是省市区
	 * @param longtitude 经度值
	 * @param latitude 纬度值
	 * @return
	 */
	public static String[] getLocation(String longtitude, String latitude) {
		// lat 31.2990170 纬度
		// log 121.3466440 经度
		String add = getAdd(longtitude, latitude);
		JSONObject jsonObject = JSONObject.fromObject(add);
		JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
		JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
		String allAdd = j_2.getString("admName");
		String arr[] = allAdd.split(",");
		SysoUtils.print("省:" + arr[0] + "\n市:" + arr[1] + "\n区:" + arr[2]);
		return arr;

	}

	public static String getAdd(String log, String lat) {
		// lat 小 log 大
		// 参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
		String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
		SysoUtils.print("请求省市区的URL："+urlString);
		String res = "";
		try {
			URL url = new URL(urlString);
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			java.io.BufferedReader in = new java.io.BufferedReader(
					new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line + "\n";
			}
			in.close();
		} catch (Exception e) {
			System.out.println("error in wapaction,and e is " + e.getMessage());
		}
		SysoUtils.print("该经纬度的地点信息："+res);
		return res;
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