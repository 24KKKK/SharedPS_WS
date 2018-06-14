package com.dyf.utils;

import java.io.IOException;

/**
 * 进程类，负责进程操作
 * @author diy
 *
 */
public class Process {
	
	/**
	 * 关闭javaw.exe进程
	 */
	public static void killJavaw(){
		String command = "taskkill /f /im javaw.exe";  
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			SysoUtils.print("错误："+e.toString());
			e.printStackTrace();
		} 
	}

}
