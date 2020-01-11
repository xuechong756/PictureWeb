package com.yshow.pic.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringManager {
	
	private static final Logger LOG = LoggerFactory.getLogger(StringManager.class);
	private StringManager(){}
	
	// 提取字符串中的数字
	public static String[] getNumberFromString(String origine) {

		if(origine == null)
			return new String[0];
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(origine);
		String result = m.replaceAll("_");//不是正则限定内的字符用 _ 代替
		return result.split("_");
	}

  
  //若为null或""返回true。否则返回false
  public static boolean isBlank(String str)
  {
	  if((str == null) || ("".equals(str)))
		  return true;
	  return false;
  }
  
  //将string从编码code1转成code1编码
  public static String changeToCoding(String string, String code1, String code2)
  {
	  try {
		return new String(string.getBytes(code1), code2);
	} catch (UnsupportedEncodingException e) {
		LOG.error("", e);
	}
	  return null;
  }

	//去掉指定重复字符串
	public static String removeSameString(String orgine, String same)
	{
		StringBuilder stringBuilder = new StringBuilder(orgine);
		int start = stringBuilder.indexOf(same);
		for(int find = stringBuilder.indexOf(same,start + 1); find != -1; find = stringBuilder.indexOf(same,start + 1))
		{
			stringBuilder.replace(find, find + same.length(), "");
		}
		return stringBuilder.toString();
	}	
	
	
	

}
