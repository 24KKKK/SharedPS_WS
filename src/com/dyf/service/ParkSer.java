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
	
	/**
	 * 将QQ登录的用户信息保存进数据库
	 * @param openid 用户唯一识别码
	 * @param nickname 用户昵称
	 * @param gender 性别
	 * @param province 省份
	 * @param city 城市
	 * @param figureurl 用户头像URL
	 * @return int 是否保存成功
	 */
	@WebResult(name = "insertResult", targetNamespace = "http://service.dyf.com")
	@WebMethod(action = "http://service.dyf.com/insertQQUserInfo")
	public int insertQQUserInfo(
			@WebParam(name = "openid", targetNamespace = "http://service.dyf.com") String openid,
			@WebParam(name = "nickname", targetNamespace = "http://service.dyf.com") String nickname,
			@WebParam(name = "gender", targetNamespace = "http://service.dyf.com") String gender,
			@WebParam(name = "province", targetNamespace = "http://service.dyf.com") String province,
			@WebParam(name = "city", targetNamespace = "http://service.dyf.com") String city,
			@WebParam(name = "figureurl", targetNamespace = "http://service.dyf.com") String figureurl);
	
	/**
	 * 根据用户的openid获得余额，进行返回
	 * @param openid 用户的id
	 * @return double 用户余额
	 */
	@WebResult(name = "resultBalance", targetNamespace = "http://service.dyf.com")
	@WebMethod(action = "http://service.dyf.com/getBalance")
	public double getBalance(
			@WebParam(name = "openid", targetNamespace = "http://service.dyf.com") String openid);
	
	/**
	 * 将用户重要操作保存进数据库（比如充值）
	 * @param openid 用户唯一识别码
	 * @param reChargeNum 充值金额
	 * @param optionName 操作名称
	 * @return int 是否保存成功
	 */
	@WebResult(name = "insertResult", targetNamespace = "http://service.dyf.com")
	@WebMethod(action = "http://service.dyf.com/insertReChargeOption")
	public int insertReChargeOption(
			@WebParam(name = "openid", targetNamespace = "http://service.dyf.com") String openid,
			@WebParam(name = "reChargeNum", targetNamespace = "http://service.dyf.com") String reChargeNum,
			@WebParam(name = "optionName", targetNamespace = "http://service.dyf.com") String optionName);
	
	
}
