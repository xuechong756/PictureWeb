package com.yshow.pic.utils;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.yshow.pic.contant.RequestEncpParams;

public class UrlDecOut {
	
	private UrlDecOut(){}
	
	//�˷������ܲ������ܵ���ȫ����������� log4j
	//all:true���ڼ����ֶε��в�ѯ����Ҳ���ܣ�falseֻ�����ڼ����ֶε��в���
	public static Map<String, String> decrParam(HttpServletRequest request, boolean all, Logger log)
	{
		Map<String, String[]> requstMap = request.getParameterMap();
		LogShow.out(log, requstMap);//log4j ������ܵ��Ĳ���
		Map<String, String> requsMap = URLDecryptCus.decrParam(request, all);
		if(StaticTools.isPosiInt(requsMap.get(RequestEncpParams.HTTPVER)))
			return requsMap;
		return Collections.emptyMap();
	}
}
