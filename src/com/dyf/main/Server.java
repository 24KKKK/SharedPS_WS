package com.dyf.main;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.dyf.dao.ParkSerImpl;
import com.dyf.service.ParkSer;

public class Server {

	public static void main(String[] args) {
		
		System.out.println("开启webservice。。。");
		ParkSer parkSer = new ParkSerImpl();
		String IP = "http://192.168.63.1:8080/";
		String serviceName = "parkservice";
		String address = IP+serviceName;
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
		factoryBean.setAddress(address); // 设置暴露地址
		factoryBean.setServiceClass(ParkSer.class); //设置接口类
		factoryBean.setServiceBean(parkSer); // 设置实现类
		factoryBean.create();
		System.out.println("开启webservice成功。。。");		
	}

}
