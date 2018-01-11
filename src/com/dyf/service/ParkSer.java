package com.dyf.service;

import java.util.List;

import com.dyf.model.ResultParklotInfo;

/**
 * 手机调用的接口服务类
 * @author diy
 *
 */
public interface ParkSer {
	
	/**
	 * 根据手机端发过来的优先条件，计算适合的停车场，将相应的停车场信息返回给手机端
	 * @param condition 手机端发来的选择停车场的优先条件，可以是时间、距离、剩余停车率、综合
	 * @param selfLng  selfLat 个人的经纬度信息
	 * @return
	 */
	public List<ResultParklotInfo> getBestParklot(String condi,String selfLng,String selfLat);
	
}
