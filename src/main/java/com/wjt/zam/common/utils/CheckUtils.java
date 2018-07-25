package com.wjt.zam.common.utils;

import java.util.List;

public class CheckUtils {
	
	/**
	  * 判断list集合是否为空
	  */
	public static boolean listIsNotNull(List<?> list) {
		if (list != null && !list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
