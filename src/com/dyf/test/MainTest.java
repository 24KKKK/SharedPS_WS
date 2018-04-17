package com.dyf.test;

import com.dyf.dao.ParkSerImpl;
import com.dyf.utils.SysoUtils;

public class MainTest {
	
	public static void main(String[] args) {
		ParkSerImpl parkSerImpl = new ParkSerImpl();
		SysoUtils.print(parkSerImpl.insertQQUserInfo("cxverfg", "kkkk", "女", "山西", "太原", "https://ljlk.com"));
	}

}
