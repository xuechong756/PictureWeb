package com.yshow.pic.utils;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.yshow.pic.contant.RequestEncpParams;

public class UrlDecOut {
	
	private UrlDecOut(){}
	
	//此方法解密并将接受到的全部参数输出， log4j
	//all:true不在加密字段的中查询参数也接受，false只接受在加密字段的中参数
	public static Map<String, String> decrParam(HttpServletRequest request, boolean all, Logger log)
	{
		Map<String, String[]> requstMap = request.getParameterMap();
		LogShow.out(log, requstMap);//log4j 输出接受到的参数
		Map<String, String> requsMap = URLDecryptCus.decrParam(request, all);
		if(StaticTools.isPosiInt(requsMap.get(RequestEncpParams.HTTPVER)))
			return requsMap;
		return Collections.emptyMap();
	}
}
