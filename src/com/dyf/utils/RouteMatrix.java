package com.dyf.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.cxf.jaxrs.json.basic.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 使用HttpClient：测试工具，可以在Java代码中模拟http请求
 * 百度地图web api的算路，可以得到两个坐标点的路线距离和行车时间
 * @author diy
 */
public class RouteMatrix {

	public static void main(String[] args) {
		getDistanceAndTime("114.345678", "38.088783", "114.345614", "38.088713");
	}
	
	/**
	 * 根据两个坐标点得到实际的路线距离 ，单位 米 和行车时间 单位秒
	 * @param Lng1 
	 * @param Lat1
	 * @param Lng2
	 * @param Lat2
	 * @return 返回String[] 包含实际路线距离和行车时间
	 */
	@SuppressWarnings("deprecation")
	public static String[] getDistanceAndTime(String Lng1,String Lat1,String Lng2,String Lat2) {
		String[] disandtime={"",""};
		String ak = "C9ESt6VMkS0ItNTWkpVMpklB6CGXrGHr";
		String url = "http://api.map.baidu.com/routematrix/v2/driving?origins="+Lat1+","+Lng1+"&destinations="+Lat2+","+Lng2+"&tactics=12&ak="+ak;
		SysoUtils.print("请求距离和时间的URL："+url);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(get);
			SysoUtils.print("response:"+response);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			String string = ConvertStreamToString(stream);
			SysoUtils.print("string1:"+string);
			string = string.substring(0,string.length()-1);
			SysoUtils.print("string2:"+string);
			JSONObject jsonObject = JSONObject.fromObject(string);

			//先判断请求是否成功
			Map map1 =  (Map) jsonObject;
			int status = (Integer) map1.get("status");
			SysoUtils.print("请求是否成功(0代表成功):"+status);
			if (status==0) {
				JSONObject jsonObject2 = JSONObject.fromObject(map1.get("result").toString().substring(1, map1.get("result").toString().length()-1));
				Map map2 = (Map) jsonObject2;
				JSONObject jsonObject3 = JSONObject.fromObject(map2.get("distance").toString());
				JSONObject jsonObject4 = JSONObject.fromObject(map2.get("duration").toString());
				disandtime[0] = jsonObject3.get("value").toString();
				disandtime[1] = jsonObject4.get("value").toString();
				SysoUtils.print("disandtime:"+disandtime[0]+" "+disandtime[1]);
			}
			else {
				disandtime[0] = "0";
				disandtime[1] = "0";
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			get.releaseConnection();
		}
		return disandtime;
		
	}

	// Convert stream to string
	public static String ConvertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			System.out.println("读取Error=" + e.toString());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				System.out.println("关闭Error=" + e.toString());
			}
		}
		return sb.toString();
	}

}
