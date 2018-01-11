package com.dyf.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.cxf.jaxrs.json.basic.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 使用HttpClient：测试工具，可以在Java代码中模拟http请求
 * 
 * @author diy
 *
 */
public class RouteMatrix {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String ak = "C9ESt6VMkS0ItNTWkpVMpklB6CGXrGHr";
		String url = "http://api.map.baidu.com/routematrix/v2/driving?origins=40.54,116.35&destinations=40.35,116.46&tactics=12&ak="+ak;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(get);
			
			System.out.println("response:"+response.toString());
			System.out.println("getParams:"+response.getParams());
			System.out.println("getStatusLine:"+response.getStatusLine().getStatusCode());
			System.out.println();
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			String string = ConvertStreamToString(stream);
			System.out.println("string:"+string);
			
			System.out.println("entity:"+entity.toString());
			EntityUtils.consume(entity);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			get.releaseConnection();
		}
		
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
			System.out.println("Error=" + e.toString());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				System.out.println("Error=" + e.toString());
			}
		}
		return sb.toString();
	}

}
