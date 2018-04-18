package com.dyf.main;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.dyf.dao.ParkSerImpl;
import com.dyf.service.ParkSer;
import com.dyf.utils.Inet;
import com.dyf.utils.SysoUtils;

public class Server {

	public static void main(String[] args) {
		
		System.out.println("开启webservice。。。");
		ParkSer parkSer = new ParkSerImpl();
		String IP = "192.168.191.1";
		//String IP = Inet.getIP() ;
		String http = "http://"+IP+":8080/";
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

}
