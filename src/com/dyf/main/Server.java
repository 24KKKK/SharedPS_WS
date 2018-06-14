package com.dyf.main;

import java.io.IOException;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.dyf.dao.ParkSerImpl;
import com.dyf.service.ParkSer;
import com.dyf.utils.Inet;
import com.dyf.utils.SysoUtils;

public class Server {

	public static void main(String[] args) {
		
		// 开启webservice
		Server.startWS();
		
		// 关闭webservice
		//Server.stopWS();
		
	}
	
	/**
	 * 开启webservice
	 */
	public static void startWS(){
		System.out.println("开启webservice。。。");
		ParkSer parkSer = new ParkSerImpl();
		//String IP = "192.168.23.1";
		//String IP = "192.168.1.3";
		String IP = Inet.getIP() ;
		String http = "http://"+IP+":8081/";
		String serviceName = "parkservice";
		String address = http+serviceName;
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
		factoryBean.setAddress(address); // 设置暴露地址
		factoryBean.setServiceClass(ParkSer.class); //设置接口类
		factoryBean.setServiceBean(parkSer); // 设置实现类
		factoryBean.create();
		System.out.println("开启webservice成功。。。");	
		SysoUtils.print("wsdl地址为："+address+"?wsdl");
	}
	
	/**
	 * 关闭javaw.exe，即关闭webservice，
	 */
	public static void stopWS(){
			String command = "taskkill /f /im javaw.exe";  
			try {
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				SysoUtils.print("错误："+e.toString());
				e.printStackTrace();
			} 
	}

} 
