package com.yshow.pic.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.yshow.pic.contant.RequestUnencpParams;

public class URLDecryptCus {
	private static final String encDataStr = RequestUnencpParams.ENCDATA;
	private static final String encVerStr = RequestUnencpParams.EncodeVer;

	// all:true不在加密字段的中查询参数也接受，false只接受在加密字段的中参数
	public static Map<String, String> decrParam(HttpServletRequest request, boolean all) {
		Map<String, String> resultMap = new HashMap<>();
		Map<String, String[]> requsetMap = request.getParameterMap();
		String enver, data;
		enver = data = null;
		if (all) {
			Set<Map.Entry<String, String[]>> set = requsetMap.entrySet();
			for (Map.Entry<String, String[]> se : set) {
				resultMap.put(se.getKey(), se.getValue()[0]);
			}
			data = resultMap.remove(encDataStr);
			enver = resultMap.remove(encVerStr);
		} else {
			if ((requsetMap.get(encVerStr) == null) || (requsetMap.get(encDataStr) == null)) {
				return resultMap;
			}
			data = requsetMap.get(encDataStr)[0];
			enver = requsetMap.get(encVerStr)[0];
		}
		Map<String, String> map = coreDecrypt(data, enver);
		if (map != null)
			resultMap.putAll(map);
		return resultMap;
	}

	// 解密版本1
	private static Map<String, String> decrypt1(String data) {
		
		byte[] bt = Base64.decode(data);
		if(bt == null)
			return Collections.emptyMap();
		for(int index = 0; index < bt.length; index++)
		{
			bt[index] = (byte)(~bt[index]^0xf4);
		}
		String result = new String(bt);
		Map<String, ArrayList<String>> map = UrlCus.getQuery("http?"+result);
		if(map.isEmpty())
			return Collections.emptyMap();
		Map<String, String> resultMap = new HashMap<>();
		Set<Map.Entry<String, ArrayList<String>>> set = map.entrySet();
		for(Map.Entry<String, ArrayList<String>> me : set)
		{
			resultMap.put(me.getKey(), me.getValue().get(0));
		}
		return resultMap;
	}

	// 解密版本2
	private static Map<String, String> decrypt2(String data) {

		return Collections.emptyMap();
	}

	private static Map<String, String> coreDecrypt(String data, String enver) {
		if (StaticTools.isPosiInt(enver)) {
			switch (Integer.parseInt(enver)) {
			case 1:
				return decrypt1(data);
			case 2:
				return decrypt2(data);
			default:
				return Collections.emptyMap();
			}
		}
		return Collections.emptyMap();
	}
}
