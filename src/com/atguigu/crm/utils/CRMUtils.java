package com.atguigu.crm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.lf5.util.DateFormatManager;

public class CRMUtils {

	public static String encodeParamsToQueryString(Map<String, Object> params,
			String prefix){
		StringBuffer result=new StringBuffer();
		
		Set<Entry<String,Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			result.append("&").append(prefix).append(key).append("=").append(value);
		}
		
		return result.toString();
	}

	public static Map<String, Object> paramsToMybatisParams(
			Map<String, Object> params) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> result=new HashMap<>();
		Set<Entry<String,Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			
			Object value = entry.getValue();
			
			if(value==null||value.toString().trim().equals("")){
				continue;
			}
			String key = entry.getKey();
			String name=key;
			String[] split = key.split("_");
			if(split.length>1){
				name=split[1];
				if(split[0].equals("LIKE")){
					value="%"+value+"%";
				}else if(split[0].equals("D")){
					value = simpleDateFormat.parseObject(value.toString());
				}
			}
			result.put(name, value);
		}
		return result;
	}
}
