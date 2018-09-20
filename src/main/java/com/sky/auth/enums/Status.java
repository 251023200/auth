package com.sky.auth.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共状态枚举. 只有 (冻结) 与(激活) 两种状态
 * 
 * @author yangfan
 */
public enum Status {

	ACTIVE(1,"激活"),

	UNACTIVE(0,"冻结");

	/** 编码 */
	private int code;
	
	/** 描述 */
	private String desc;

	private Status(int code,String desc) {
		this.code =code;
		this.desc = desc;
	}

	public int getCode(){
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public static Map<String, Map<String, Object>> toMap() {
		Status[] ary = Status.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = ary[num].name();
			map.put("value", ary[num].name());
			map.put("desc", ary[num].getDesc());
			map.put("code", ary[num].getCode());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		Status[] ary = Status.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("desc", ary[i].getDesc());
			map.put("name", ary[i].name());
			map.put("code",ary[i].getCode());
			list.add(map);
		}
		return list;
	}

	public static Status getEnum(String name) {
		Status[] arry = Status.values();
		for (int i = 0; i < arry.length; i++) {
			if (arry[i].name().equalsIgnoreCase(name)) {
				return arry[i];
			}
		}
		return null;
	}

	/**
	 * 取枚举的json字符串
	 * 
	 * @return
	 */
	public static String getJsonStr() {
		Status[] enums = Status.values();
		StringBuffer jsonStr = new StringBuffer("[");
		for (Status senum : enums) {
			if (!"[".equals(jsonStr.toString())) {
				jsonStr.append(",");
			}
			jsonStr.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}

}
