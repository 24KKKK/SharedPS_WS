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
import com.dyf.utils.Convert;
import com.dyf.utils.CreateDate;
import com.dyf.utils.LocationUtil;
import com.dyf.utils.RouteMatrix;
import com.dyf.utils.SortList;
import com.dyf.utils.SysoUtils;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;

public class ParkSerImpl implements ParkSer {

	public static void main(String[] args) {
		// 自己的位置信息
		// String lng = Constant.selfLng;
		// String lat = Constant.selfLat;

		// 手机在毕设室的经纬度信息38.089,114.514
		String lng = "114.514";
		String lat = "38.089";

		ParkSerImpl parkSerImpl = new ParkSerImpl();

		// 获取全部的停车场信息
		List<ResultParklotInfo> allParklots = parkSerImpl.getAllParklot(lng, lat);
		for (int i = 0; i < allParklots.size(); i++) {
			SysoUtils.print(allParklots.get(i).getParklotName() + "的未停车数为：" + allParklots.get(i).getNoParkNum());
		}

		/*
		 * SysoUtils.print("按照时间排序的停车场信息----------------------------------");
		 * List<ResultParklotInfo> bestParklots =
		 * parkSerImpl.getBestParklot("time", lng, lat); for (int i = 0; i <
		 * bestParklots.size(); i++) {
		 * SysoUtils.print("排序后的时间："+bestParklots.get(i).getTime()); }
		 */

		/*
		 * SysoUtils.print("按照距离排序的停车场信息----------------------------------");
		 * List<ResultParklotInfo> bestParklots =
		 * parkSerImpl.getBestParklot("distance", lng, lat); for (int i = 0; i <
		 * bestParklots.size(); i++) {
		 * SysoUtils.print("排序后的停车场名称："+bestParklots.get(i).getParklotName()+
		 * "  距离："+bestParklots.get(i).getDistance()); }
		 */

		/*
		 * SysoUtils.print("按照未停车数排序的停车场信息----------------------------------");
		 * List<ResultParklotInfo> bestParklots =
		 * parkSerImpl.getBestParklot("noParkNum", lng, lat); for (int i = 0; i
		 * < bestParklots.size(); i++) {
		 * SysoUtils.print("排序后的未停车数："+bestParklots.get(i).getNoParkNum()); }
		 */

		/*
		 * List<Table_ParklotInfo> sameAreaParklotInfos =
		 * parkSerImpl.getSameAreaParklotInfos(lng, lat); for (int i = 0; i <
		 * sameAreaParklotInfos.size(); i++) { SysoUtils.print("相同地区信息的停车场名称：" +
		 * sameAreaParklotInfos.get(i).getParklotName()); }
		 */

		/*
		 * SysoUtils.print(
		 * "输出getNearParklots结果----------------------------------------------------"
		 * ); List<ResultParklotInfo> nearParklots =
		 * parkSerImpl.getNearParklots(lng, lat);
		 * SysoUtils.print("nearParklots.size:" + nearParklots.size()); for (int
		 * i = 0; i < nearParklots.size(); i++) { SysoUtils.print("距离小于" +
		 * Constant.DISTANCE + "米的停车场名称：" +
		 * nearParklots.get(i).getParklotName()); SysoUtils.print("未停车率：" +
		 * nearParklots.get(i).getNoParkRate());
		 * SysoUtils.print("停车场描述："+nearParklots.get(i).getParklotDescription())
		 * ; SysoUtils.print("停车场经度："+nearParklots.get(i).getParklotLng());
		 * SysoUtils.print("停车场纬度："+nearParklots.get(i).getParklotLat());
		 * SysoUtils.print("停车场距离："+nearParklots.get(i).getDistance());
		 * SysoUtils.print("停车场车位总数："+nearParklots.get(i).getParklotAmount());
		 * SysoUtils.print("停车场时间："+nearParklots.get(i).getTime());
		 * SysoUtils.print("停车场未停车数："+nearParklots.get(i).getNoParkNum());
		 * SysoUtils.print(
		 * "----------------------------------------------------"); }
		 */

		/*
		 * double noParkRate = parkSerImpl.getNoParkRate("测试停车场");
		 * SysoUtils.print("noParkRate:" + noParkRate);
		 */

	}

	@Override
	public List<ResultParklotInfo> getBestParklot(String condi, String selfLng, String selfLat) {

		List<ResultParklotInfo> nearParklots = getNearParklots(selfLng, selfLat);
		SortList<ResultParklotInfo> sortList = new SortList<ResultParklotInfo>();

		switch (condi) {
		case "time":
			SysoUtils.print("根据时间来查找停车场，个人经纬度为：" + selfLng + " " + selfLat);
			sortList.sortByMethod(nearParklots, "getTime", false);
			break;

		case "distance":
			SysoUtils.print("根据距离来查找停车场，个人经纬度为：" + selfLng + " " + selfLat);
			sortList.sortByMethod(nearParklots, "getDistance", false);
			break;

		case "noParkNum":
			SysoUtils.print("根据未停车数来查找停车场，个人经纬度为：" + selfLng + " " + selfLat);
			sortList.sortByMethod(nearParklots, "getNoParkNum", true);
			break;

		case "multiple":
			SysoUtils.print("综合条件来查找停车场，个人经纬度为：" + selfLng + " " + selfLat);
			break;

		default:
			break;
		}
		SysoUtils.print("返回的停车场信息为：");
		for (int i = 0; i < nearParklots.size(); i++) {
			SysoUtils.print("名称：" + nearParklots.get(i).getParklotName() + " 距离：" + nearParklots.get(i).getDistance()
					+ " 时间：" + nearParklots.get(i).getTime() + " 未停车数：" + nearParklots.get(i).getNoParkNum());

		}
		return nearParklots;
	}

	@Override
	public List<ResultParklotInfo> getAllParklot(String selfLng, String selfLat) {
		List<ResultParklotInfo> resultParklotInfos = new ArrayList<ResultParklotInfo>();

		String selectAllSql = "SELECT parklotName, parklotAmount, parklotLng, parklotLat, parklotDescription FROM  table_parklotinfo;";
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectAllSql);
		try {
			while (rSet.next()) {
				String parklotName = rSet.getString("parklotName");
				int parklotAmount = rSet.getInt("parklotAmount");
				String parklotLng = rSet.getString("parklotLng");
				String parklotLat = rSet.getString("parklotLat");
				String description = rSet.getString("parklotDescription");
				int noParkNum = parklotAmount - getParkNum(parklotName);
				double noParkRate = (double) noParkNum / parklotAmount;
				String[] disAndTime = RouteMatrix.getDistanceAndTime(selfLng, selfLat, parklotLng, parklotLat);
				ResultParklotInfo resultParklotInfo = new ResultParklotInfo(parklotName,
						Integer.parseInt(disAndTime[0]), Integer.parseInt(disAndTime[1]), noParkNum, noParkRate,
						parklotAmount, parklotLng, parklotLat, description);
				resultParklotInfos.add(resultParklotInfo);
			}
		} catch (SQLException e) {
			SysoUtils.print("getAllParklot错误：" + e.toString());
			e.printStackTrace();
		}
		return resultParklotInfos;
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
				int noParkNum = amount - getParkNum(name);
				String description = parklotInfos.get(i).getParklotDescription();
				int time = Integer.parseInt(arr[1]);
				double rate = getNoParkRate(name);
				ResultParklotInfo resultParklotInfo = new ResultParklotInfo(name, distance, time, noParkNum, rate,
						amount, destinationLng, destinationLat, description);
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
		num = getParkNum(parklotName);
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

		SysoUtils.print(parklotName + "已停车：" + num);
		dbBean.close();
		return num;
	}

	@Override
	public int insertQQUserInfo(String openid, String nickname, String gender, String province, String city,
			String figureurl) {
		String selectSQL = "select nickname from table_qquserinfo where openid =" + "'" + openid + "'";
		SysoUtils.print("selectSQL:" + selectSQL);
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectSQL);
		try {
			if (rSet.next()) {
				return 1;
			}
			// 如果上面的查询没有返回1，则说明没有记录，进行用户信息插入
			String insertSQL = "insert into table_qquserinfo values ('" + openid + "','" + nickname + "',"
					+ Convert.genderToInt(gender) + ",'" + province + "','" + city + "','" + figureurl + "','"
					+ CreateDate.getDate() + "')";
			String insertSQL2 = "insert into table_qquserbalance values ('" + openid + "'," + 0 + ")";
			SysoUtils.print("insertSQL:" + insertSQL);
			SysoUtils.print("insertSQL2:" + insertSQL2);
			int i = dbBean.executeUpdate(insertSQL);
			int j = dbBean.executeUpdate(insertSQL2);
			if (i == 1 && j == 1)
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rSet.close();
				dbBean.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public double getBalance(String openid) {
		double balance = 0;
		String selectSQL = "select balance from table_qquserbalance where openid = ('" + openid + "')";
		SysoUtils.print("selectSQL:" + selectSQL);
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectSQL);
		try {
			while (rSet.next()) {
				balance = rSet.getDouble("balance");
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
		return balance;
	}

	@Override
	public int insertReChargeOption(String openid, String reChargeNum, String optionName) {

		// 记录用户操作之前，先要将充值操作打进余额中
		String updateUserBal = "update table_qquserbalance set balance = balance + " + Double.parseDouble(reChargeNum)
				+ " where openid = '" + openid + "'";
		SysoUtils.print("updateUserBal：" + updateUserBal);
		// 将用户的操作记录下来
		String insertOptionSQL = "INSERT INTO table_qquserrecord VALUES ('" + openid + "','" + optionName + "',"
				+ Double.parseDouble(reChargeNum) + ",'" + CreateDate.getDate()
				+ "',(SELECT a.balance FROM table_qquserbalance a WHERE a.openid = '" + openid + "') +"
				+ Double.parseDouble(reChargeNum) + ")";
		SysoUtils.print("insertOptionSQL：" + insertOptionSQL);
		DBBean dbBean = new DBBean();
		int i = dbBean.executeUpdate(updateUserBal);
		int j = dbBean.executeUpdate(insertOptionSQL);
		dbBean.close();
		if (i == 1 && j == 1) {
			SysoUtils.print("记录操作成功");
			return 1;
		} else {
			SysoUtils.print("记录操作失败");
			return 0;
		}
	}

}
