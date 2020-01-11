package com.yshow.pic.utils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class StaticTools {
	private static Gson gson = new Gson();
	private static Pattern posiInt = Pattern.compile("-?[0-9]+");
	private static Pattern posiFloat = Pattern.compile("(-?[0-9]+.?[0-9]+)");
	//Positive integer
	public static Gson getGson()
	{
		return gson;
	}
	
	//是正整数返回true
	public static boolean isPosiInt(String num)
	{
		if(num == null)
			return false;
		return posiInt.matcher(num).matches();
	}
	//判断是否是数字
	public static boolean isNumber(String num)
	{
		if(num == null)
			return false;
		boolean res = posiInt.matcher(num).matches();
		if(!res)
		{
			res =  posiFloat.matcher(num).matches();
		}
		return res;
	}
	//获取客户端ip
	public static String getIp2(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringManager.isBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringManager.isBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
