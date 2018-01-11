package com.dyf.model;

/**
 * 通过计算返回给手机端的停车场信息类
 * @author diy
 *
 */
public class ResultParklotInfo {

	private String parklotName; // 停车场名称
	private int distance; // 车辆距离停车场的距离，单位 米
	private int time;// 车辆行驶到停车场所需的时间
	private int noParkNum; //停车场的未停车数
	private double noParkRate; //停车场的未停车率
	private int parklotAmount; // 停车场车位数量
	private String parklotLng; // 停车场位置精度
	private String parklotLat; // 停车场位置纬度
	private String parklotDescription; // 停车场描述
	
	
	public ResultParklotInfo() {
		super();
	}
	
	/**
	 * @param parklotName
	 * @param distance
	 * @param time
	 * @param noParkNum
	 * @param noParkRate
	 * @param parklotAmount
	 * @param parklotLng
	 * @param parklotLat
	 * @param parklotDescription
	 */
	public ResultParklotInfo(String parklotName, int distance, int time, int noParkNum, double noParkRate,
			int parklotAmount, String parklotLng, String parklotLat, String parklotDescription) {
		super();
		this.parklotName = parklotName;
		this.distance = distance;
		this.time = time;
		this.noParkNum = noParkNum;
		this.noParkRate = noParkRate;
		this.parklotAmount = parklotAmount;
		this.parklotLng = parklotLng;
		this.parklotLat = parklotLat;
		this.parklotDescription = parklotDescription;
	}

	/**
	 * 不包含未停车数
	 * @param parklotName
	 * @param distance
	 * @param time
	 * @param noParkRate 包含未停车率
	 * @param parklotAmount
	 * @param parklotLng
	 * @param parklotLat
	 * @param parklotDescription
	 */
	public ResultParklotInfo(String parklotName, int distance, int time, double noParkRate, int parklotAmount,
			String parklotLng, String parklotLat, String parklotDescription) {
		super();
		this.parklotName = parklotName;
		this.distance = distance;
		this.time = time;
		this.noParkRate = noParkRate;
		this.parklotAmount = parklotAmount;
		this.parklotLng = parklotLng;
		this.parklotLat = parklotLat;
		this.parklotDescription = parklotDescription;
	}

	@Override
	public String toString() {
		return "ResultParklotInfo [parklotName=" + parklotName + ", distance=" + distance + ", time=" + time
				+ ", noParkNum=" + noParkNum + ", noParkRate=" + noParkRate + ", parklotAmount=" + parklotAmount
				+ ", parklotLng=" + parklotLng + ", parklotLat=" + parklotLat + ", parklotDescription="
				+ parklotDescription + "]";
	}
	/**
	 * 不包含未停车率，未停车数
	 * @param parklotName
	 * @param distance
	 * @param time
	 * @param parklotAmount
	 * @param parklotLng
	 * @param parklotLat
	 * @param parklotDescription
	 */
	public ResultParklotInfo(String parklotName, int distance, int time, int parklotAmount, String parklotLng,
			String parklotLat, String parklotDescription) {
		super();
		this.parklotName = parklotName;
		this.distance = distance;
		this.time = time;
		this.parklotAmount = parklotAmount;
		this.parklotLng = parklotLng;
		this.parklotLat = parklotLat;
		this.parklotDescription = parklotDescription;
	}
	public String getParklotName() {
		return parklotName;
	}
	public void setParklotName(String parklotName) {
		this.parklotName = parklotName;
	}
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getParklotAmount() {
		return parklotAmount;
	}
	public void setParklotAmount(int parklotAmount) {
		this.parklotAmount = parklotAmount;
	}
	public String getParklotLng() {
		return parklotLng;
	}
	public void setParklotLng(String parklotLng) {
		this.parklotLng = parklotLng;
	}
	public String getParklotLat() {
		return parklotLat;
	}
	public void setParklotLat(String parklotLat) {
		this.parklotLat = parklotLat;
	}
	public String getParklotDescription() {
		return parklotDescription;
	}
	public void setParklotDescription(String parklotDescription) {
		this.parklotDescription = parklotDescription;
	}
	public double getNoParkRate() {
		return noParkRate;
	}

	public void setNoParkRate(double noParkRate) {
		this.noParkRate = noParkRate;
	}

	public int getNoParkNum() {
		return noParkNum;
	}

	public void setNoParkNum(int noParkNum) {
		this.noParkNum = noParkNum;
	}
}
