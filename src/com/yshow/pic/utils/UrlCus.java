package com.yshow.pic.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UrlCus {
	
	
	public static Map<String, ArrayList<String>> getQuery(String url)
	{
		if(url.startsWith("http")&&url.contains("?"))
		{
			String str = url.substring(url.indexOf('?')+1, url.length());
			if(str.length()==0)
				return Collections.emptyMap();
			String[] queryStr = str.split("&");
			Map<String, ArrayList<String>> map = new HashMap<>();
			for(int i = 0; i < queryStr.length; i++)
			{
				String[] buf = queryStr[i].split("=");
				if(buf.length == 0)
					continue;
				ArrayList<String> arrayList = map.get(buf[0]);
				if(arrayList == null)
				{
					arrayList = new ArrayList<>();
					arrayList.add(buf.length!=2?"":buf[1]);
					map.put(buf[0], arrayList);
				}
				else
					arrayList.add(buf.length!=2?"":buf[1]);
			}
			return map;
		}
		return Collections.emptyMap();
	}
	

}
