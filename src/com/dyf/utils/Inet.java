package com.dyf.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Inet {
	
	public static String getIP(){
		String IP = "0.0.0.0";
		try {
			IP = Inet4Address.getLocalHost().getHostAddress().toString();
			System.out.println("本机IP地址为："+IP);
		} catch (UnknownHostException e) {
			SysoUtils.print("获取IP失败："+e.toString());
			e.printStackTrace();
		}
		return IP;
	}
	
	/** 
     * 获取机器所有网卡的IP（ipv4） 
     * @return 
     */  
    public static List<String> getLocalIP() {  
        List<String> ipList = new ArrayList<String>();  
        InetAddress ip = null;  
        try {  
            Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();  
            while (netInterfaces.hasMoreElements()) {  
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();  
                // 遍历所有ip  
                Enumeration<InetAddress> ips = ni.getInetAddresses();  
                while (ips.hasMoreElements()) {  
                    ip = (InetAddress) ips.nextElement();  
                    if (null == ip || "".equals(ip)) {  
                        continue;  
                    }  
                    String sIP = ip.getHostAddress();  
                    if(sIP == null || sIP.indexOf(":") > -1) {  
                        continue;  
                    }  
                    ipList.add(sIP);  
                    System.out.println(sIP);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ipList;  
    }  
}
