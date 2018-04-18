package com.dyf.test;

import java.util.Date;

import com.dyf.dao.ParkSerImpl;
import com.dyf.utils.CreateDate;
import com.dyf.utils.SysoUtils;

public class MainTest {
	
	public static void main(String[] args) {
		ParkSerImpl parkSerImpl = new ParkSerImpl();
		SysoUtils.print(parkSerImpl.insertQQUserInfo("2", "kkkk", "女", "山西", "太原", "https://ljlk.com"));
		System.out.println(CreateDate.getDate());
	}

}
