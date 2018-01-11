package com.dyf.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 * 这是一个自定义排序的类，专门针对列表（List）中的数据进行排序；可按指定方法进行。
 * @param <E>
 */
public class SortList<E> {

	/**
	 * 对列表中的数据按指定字段进行排序。要求类必须有相关的方法返回字符串、整型等值以进行比较。
	 * @param list 进行排序的list对象
	 * @param method 按照什么条件排序，比如按照时间排序，则method值为 "getTime",也就是model中要有time的get方法
	 * @param reverseFlag false为从小到大排序
	 */
	public void sortByMethod(List<E> list, final String method, final boolean reverseFlag) {
		Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			public int compare(Object arg1, Object arg2) {
				int result = 0;
				try {
					Method m1 = ((E) arg1).getClass().getMethod(method, null);
					Method m2 = ((E) arg2).getClass().getMethod(method, null);
					Object obj1 = m1.invoke(((E) arg1), null);
					Object obj2 = m2.invoke(((E) arg2), null);
					if (obj1 instanceof String) {
						// 字符串
						result = obj1.toString().compareTo(obj2.toString());
					} else if (obj1 instanceof Integer) {
						// 整型（Method的返回参数可以是int的，因为JDK1.5之后，Integer与int可以自动转换了）
						result = (Integer) obj1 - (Integer) obj2;
					}
					else {
						// 目前尚不支持的对象，直接转换为String，然后比较，后果未知
						result = obj1.toString().compareTo(obj2.toString());
						System.err.println("SortList.sortByMethod方法接受到不可识别的对象类型，转换为字符串后比较返回...");
					}

					if (reverseFlag) {
						// 倒序
						result = -result;
					}
				} catch (NoSuchMethodException nsme) {
					nsme.printStackTrace();
				} catch (IllegalAccessException iae) {
					iae.printStackTrace();
				} catch (InvocationTargetException ite) {
					ite.printStackTrace();
				}

				return result;
			}
		});
	}
}