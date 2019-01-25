package com.insigma.common.util;

import java.util.HashMap;
import java.util.Map;

public class GetFiledCommentsUtil {
   
	/**
	 * �����ַ���
	 * @param types
	 * @return
	 */
	public static Map<String,String> analysis(String types){
		if(types==null  || "".equalsIgnoreCase(types)) return null;
		Map<String,String> map = new HashMap<String,String>();
		String[] typeArray = types.split(";");
		for (String string : typeArray) {
			String[] type = string.split(":");
			map.put(type[0], type[1]);
		}
		return map;
	}
	
	/**
	 * ���ر�����ֶ���������
	 * @param types
	 * @param key
	 * @return
	 */
	public static String getChangeFieldName(String types,String key){
		Map<String,String> map  = analysis(types);
		if(map==null  || "".equals(map)) return null;
		return map.get(key);
	}
	
	/**
	 * ���ر�����ֶ���������
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getChangeFieldName(Map<String,String> map,String key){
		if(map==null  || "".equals(map)) return null;
		return map.get(key);
	}
	
}
