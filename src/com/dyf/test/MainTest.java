package com.dyf.test;

import java.io.IOException;
import java.util.Date;

import com.dyf.dao.ParkSerImpl;
import com.dyf.utils.CreateDate;
import com.dyf.utils.SysoUtils;

public class MainTest {
	
	public static void main(String[] args) {
		//ParkSerImpl parkSerImpl = new ParkSerImpl();
		//SysoUtils.print(parkSerImpl.insertQQUserInfo("2", "kkkk", "女", "山西", "太原", "https://ljlk.com"));
		//System.out.println(CreateDate.getDate());
		
		//parkSerImpl.insertReserveOption("4B994EDEB23EEA3BE6BE94CBE855572E", "胸科医院停车场", "12:08", "15:23", "2018-4-19", "2018-4-19");
		String command = "taskkill /f /im javaw.exe";  
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			SysoUtils.print("错误："+e.toString());
			e.printStackTrace();
		}
	}
}
