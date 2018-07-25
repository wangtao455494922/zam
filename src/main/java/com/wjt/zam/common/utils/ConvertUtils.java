package com.wjt.zam.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class ConvertUtils {
	/**
	 * 字符串转集合
	 */
	public static List<String> strToList(String str) {
		List<String> list = new ArrayList<>();
		int index = str.indexOf(",");
		if (index == -1) {
			list.add(str);
		} else {
			for (String string : str.split(",")) {
				list.add(string);
			}
		}
		return list;
	}
	
	/**
	 * 将act对象转换成zam系统对应的对象
	 */
	public static <T, D> void actObjectToZamObject(Class<T> ajcDeclareAnnotation,List<T> list, List<D> actTasks) throws InstantiationException, IllegalAccessException {
		if (CheckUtils.listIsNotNull(actTasks)) {
			for (D actTask : actTasks) {
				T zamTask = ajcDeclareAnnotation.newInstance();
				BeanUtils.copyProperties(actTask, zamTask);
				list.add(zamTask);
			}
		}
	}	
}
