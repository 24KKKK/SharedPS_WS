package com.dyf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dyf.bean.DBBean;
import com.dyf.model.ResultParklotInfo;
import com.dyf.model.Table_ParklotInfo;
import com.dyf.service.ParkSer;
import com.dyf.utils.Constant;
import com.dyf.utils.LocationUtil;
import com.dyf.utils.RouteMatrix;
import com.dyf.utils.SortList;
import com.dyf.utils.SysoUtils;

public class ParkSerImpl implements ParkSer {

	public static void main(String[] args) {
		// 自己的位置信息
		String lng = Constant.selfLng;
		String lat = Constant.selfLat;

		ParkSerImpl parkSerImpl = new ParkSerImpl();
		
		SysoUtils.print("按照时间排序的停车场信息----------------------------------");
		List<ResultParklotInfo> bestParklots = parkSerImpl.getBestParklot("time", lng, lat);
		for (int i = 0; i < bestParklots.size(); i++) {
			SysoUtils.print("排序后的时间："+bestParklots.get(i).getTime());
		}
		
		/*SysoUtils.print("按照距离排序的停车场信息----------------------------------");
		List<ResultParklotInfo> bestParklots = parkSerImpl.getBestParklot("distance", lng, lat);
		for (int i = 0; i < bestParklots.size(); i++) {
			SysoUtils.print("排序后的距离："+bestParklots.get(i).getDistance());
		}*/
		
		/*SysoUtils.print("按照未停车数排序的停车场信息----------------------------------");
		List<ResultParklotInfo> bestParklots = parkSerImpl.getBestParklot("noParkNum", lng, lat);
		for (int i = 0; i < bestParklots.size(); i++) {
			SysoUtils.print("排序后的未停车数："+bestParklots.get(i).getNoParkNum());
		}*/
		
		/*List<Table_ParklotInfo> sameAreaParklotInfos = parkSerImpl.getSameAreaParklotInfos(lng, lat);
		for (int i = 0; i < sameAreaParklotInfos.size(); i++) {
			SysoUtils.print("相同地区信息的停车场名称：" + sameAreaParklotInfos.get(i).getParklotName());
		}*/

		/*SysoUtils.print("输出getNearParklots结果----------------------------------------------------");
		List<ResultParklotInfo> nearParklots = parkSerImpl.getNearParklots(lng, lat);
		SysoUtils.print("nearParklots.size:" + nearParklots.size());
		for (int i = 0; i < nearParklots.size(); i++) {
			SysoUtils.print("距离小于" + Constant.DISTANCE + "米的停车场名称：" + nearParklots.get(i).getParklotName());
			SysoUtils.print("未停车率：" + nearParklots.get(i).getNoParkRate());
			SysoUtils.print("停车场描述："+nearParklots.get(i).getParklotDescription());
			SysoUtils.print("停车场经度："+nearParklots.get(i).getParklotLng());
			SysoUtils.print("停车场纬度："+nearParklots.get(i).getParklotLat());
			SysoUtils.print("停车场距离："+nearParklots.get(i).getDistance());
			SysoUtils.print("停车场车位总数："+nearParklots.get(i).getParklotAmount());
			SysoUtils.print("停车场时间："+nearParklots.get(i).getTime());
			SysoUtils.print("停车场未停车数："+nearParklots.get(i).getNoParkNum());			
			SysoUtils.print("----------------------------------------------------");
		}*/
		
		/*double noParkRate = parkSerImpl.getNoParkRate("测试停车场");
		SysoUtils.print("noParkRate:" + noParkRate);*/
		 
	}

	@Override
	public List<ResultParklotInfo> getBestParklot(String condi, String selfLng, String selfLat) {

		List<ResultParklotInfo> nearParklots = getNearParklots(selfLng, selfLat);
		SortList<ResultParklotInfo> sortList = new SortList<ResultParklotInfo>();

		switch (condi) {
		case "time":
			sortList.sortByMethod(nearParklots, "getTime", false);
			break;

		case "distance":
			sortList.sortByMethod(nearParklots, "getDistance", false);
			break;

		case "noParkNum":
			sortList.sortByMethod(nearParklots, "getNoParkNum", true);
			break;

		case "multiple":

			break;

		default:
			break;
		}

		return nearParklots;
	}

	/**
	 * 返回和自己同一区域的停车场信息,比如我的经纬度在桥东区，那么返回桥东区的停车场信息
	 * 
	 * @param lng
	 *            自己的经度信息
	 * @param lat
	 *            自己的纬度信息
	 * @return List<Table_ParklotInfo>
	 */
	public List<Table_ParklotInfo> getSameAreaParklotInfos(String lng, String lat) {

		// 得到停车场的省市区信息
		String[] arr = LocationUtil.getLocation(lng, lat);

		List<Table_ParklotInfo> parklotInfos = new ArrayList<Table_ParklotInfo>();

		String selectSql = "SELECT parklotName, parklotAmount, parklotLng, parklotLat, parklotDescription, parklotCreatedTime, parklotProvince, parklotCity, parklotArea FROM table_parklotinfo WHERE parklotProvince = '"
				+ arr[0] + "' AND parklotCity = '" + arr[1] + "' AND parklotArea = '" + arr[2] + "'";
		SysoUtils.print("查看和自己位置相同区域的信息sql：" + selectSql);
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectSql);
		try {
			while (rSet.next()) {
				String parklotName = rSet.getString("parklotName");
				int parklotAmount = rSet.getInt("parklotAmount");
				String parklotLng = rSet.getString("parklotLng");
				String parklotLat = rSet.getString("parklotLat");
				String parklotDescription = rSet.getString("parklotDescription");
				String parklotCreatedTime = rSet.getString("parklotCreatedTime");
				String parklotProvince = rSet.getString("parklotProvince");
				String parklotCity = rSet.getString("parklotCity");
				String parklotArea = rSet.getString("parklotArea");

				Table_ParklotInfo parklotInfo = new Table_ParklotInfo(parklotName, parklotAmount, parklotLng,
						parklotLat, parklotDescription, parklotCreatedTime, parklotProvince, parklotCity, parklotArea);
				parklotInfos.add(parklotInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rSet.close();
				dbBean.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return parklotInfos;
	}

	/**
	 * 返回距离我1200米，开车时间1200秒内的停车场信息，距离和时间通过修改constant类值来修改
	 * 
	 * @param selflng
	 *            自己的经度信息
	 * @param selflat
	 *            自己的纬度信息
	 * @return 返回List<ResultParklotInfo>，带有我和停车场的距离和时间等信息
	 */
	public List<ResultParklotInfo> getNearParklots(String selflng, String selflat) {
		List<ResultParklotInfo> results = new ArrayList<ResultParklotInfo>();

		String[] arr = { "", "" };
		List<Table_ParklotInfo> parklotInfos = getSameAreaParklotInfos(selflng, selflat);
		SysoUtils.print("parklotInfos.size():" + parklotInfos.size());
		for (int i = 0; i < parklotInfos.size(); i++) {
			String destinationLng = parklotInfos.get(i).getParklotLng();
			String destinationLat = parklotInfos.get(i).getParklotLat();
			arr = RouteMatrix.getDistanceAndTime(selflng, selflat, destinationLng, destinationLat);
			if (Double.parseDouble(arr[0]) <= Constant.DISTANCE && Integer.parseInt(arr[1]) <= Constant.TIME) {
				String name = parklotInfos.get(i).getParklotName();
				int distance = Integer.parseInt(arr[0]);
				int amount = parklotInfos.get(i).getParklotAmount();
				int noParkNum = amount-getParkNum(name);
				String description = parklotInfos.get(i).getParklotDescription();
				int time = Integer.parseInt(arr[1]);
				double rate = getNoParkRate(name);
				ResultParklotInfo resultParklotInfo = new ResultParklotInfo(name, distance, time, noParkNum, rate, amount,
						destinationLng, destinationLat, description);
				results.add(resultParklotInfo);
			}
		}
		return results;
	}

	/**
	 * 返回停车场的未停车率，一个double数组
	 * 
	 * @param selfLng
	 *            自己的经度信息
	 * @param selfLat
	 *            自己的纬度信息
	 * @return double[] 未停车率
	 */
	public double[] getNoParkRate(String selfLng, String selfLat) {
		double[] rate = null;

		List<ResultParklotInfo> nearParklots = getNearParklots(selfLng, selfLat);
		DBBean dbBean = new DBBean();
		SysoUtils.print("nearParklots.size():" + nearParklots.size());
		for (int i = 0; i < nearParklots.size(); i++) {
			rate = new double[nearParklots.size()];
			String parklotName = nearParklots.get(i).getParklotName();
			String countSql = "select count(*) num from table_inoutinfo where parkid != 0 and parklotname = '"
					+ parklotName + "';";
			SysoUtils.print("countSql:" + countSql);
			String selectSql = "select parklotAmount from table_parklotinfo where parklotName = '" + parklotName + "';";
			SysoUtils.print("selectSql:" + selectSql);

			ResultSet rSet = dbBean.executeQuery(countSql);
			int num = 0; // 停车场已经停车的数量
			try {
				while (rSet.next()) {
					num = rSet.getInt("num");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			ResultSet rSet2 = dbBean.executeQuery(selectSql);
			int amount = 0;
			try {
				while (rSet2.next()) {
					amount = rSet2.getInt("parklotAmount");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rSet2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			rate[i] = (amount - num) / amount;
		}
		dbBean.close();
		return rate;
	}

	/**
	 * 返回指定停车场的未停车率
	 * 
	 * @param parklotName
	 *            停车场名称
	 * @return double 未停车率
	 */
	public double getNoParkRate(String parklotName) {
		double rate = 0.0;

		int num = 0; // 停车场已经停车的数量
		num=getParkNum(parklotName);
		DBBean dbBean = new DBBean();
		
		String selectSql = "select parklotAmount from table_parklotinfo where parklotName = '" + parklotName + "';";
		SysoUtils.print("selectSql:" + selectSql);

		ResultSet rSet2 = dbBean.executeQuery(selectSql);
		int amount = 0;
		try {
			while (rSet2.next()) {
				amount = rSet2.getInt("parklotAmount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rSet2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rate = (double) (amount - num) / amount;
		SysoUtils.print("已停车：" + num + "  总数量：" + amount + "  未停车率：" + rate);
		dbBean.close();
		return rate;
	}
	
	/**
	 * 返回指定停车场的已经停车数
	 * 
	 * @param parklotName
	 *            停车场名称
	 * @return double 未停车数
	 */
	public int getParkNum(String parklotName) {
		int rate = 0;

		DBBean dbBean = new DBBean();
		String countSql = "select count(*) num from table_inoutinfo where parkid != 0 and parklotname = '" + parklotName
				+ "';";
		SysoUtils.print("countSql:" + countSql);
		
		ResultSet rSet = dbBean.executeQuery(countSql);
		int num = 0; // 停车场已经停车的数量
		try {
			while (rSet.next()) {
				num = rSet.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		rate = num;
		SysoUtils.print("已停车：" + num);
		dbBean.close();
		return rate;
	}
}
