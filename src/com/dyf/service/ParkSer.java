package com.dyf.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.dyf.model.ResultParklotInfo;

/**
 * 手机调用的接口服务类
 * 
 * @author diy
 */
@WebService(targetNamespace = "http://service.dyf.com", serviceName = "ParkService")
public interface ParkSer {

	/**
	 * 根据手机端发过来的优先条件，计算适合的停车场，将相应的停车场信息返回给手机端
	 * 
	 * @param condition
	 *            手机端发来的选择停车场的优先条件，可以是时间、距离、剩余停车率、综合
	 * @param selfLng
	 *            selfLat 个人的经纬度信息
	 * @return List<ResultParklotInfo> 返回排序之后的符合条件的停车场信息
	 */
	@WebResult(name = "resultBestParklot", targetNamespace = "http://service.dyf.com")
	@WebMethod(action = "http://service.dyf.com/getBestParklot")
	public List<ResultParklotInfo> getBestParklot(
			@WebParam(name = "condition", targetNamespace = "http://service.dyf.com") String condi,
			@WebParam(name = "selfLng", targetNamespace = "http://service.dyf.com") String selfLng,
			@WebParam(name = "selfLat", targetNamespace = "http://service.dyf.com") String selfLat);

	/**
	 * 获取所有的停车场信息，返回给手机端
	 * @param selfLng 个人的经度信息
	 * @param selfLat 个人的纬度信息
	 * @return List<ResultParklotInfo> 返回所有的停车场信息
	 */
	@WebResult(name = "resultAllParklot", targetNamespace = "http://service.dyf.com")
	@WebMethod(action = "http://service.dyf.com/getAllParklot")
	public List<ResultParklotInfo> getAllParklot(
			@WebParam(name = "selfLng", targetNamespace = "http://service.dyf.com") String selfLng,
			@WebParam(name = "selfLat", targetNamespace = "http://service.dyf.com") String selfLat);
	
	
	
	
}
