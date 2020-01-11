package com.yshow.pic.server.query.pic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yshow.pic.contant.RequestEncpParams;
import com.yshow.pic.model.db.pic.T_phoalb;
import com.yshow.pic.model.db.pic.T_phoatlas;
import com.yshow.pic.model.db.pic.T_phtype;
import com.yshow.pic.model.local.ParamType;
import com.yshow.pic.server.query.ListByType;
import com.yshow.pic.utils.UrlDecOut;

public class VersionManager {
	private static final Logger LOG = LoggerFactory.getLogger(VersionManager.class);
	
	//获取图片分类标题
	public static Map<String, Object> getNavs(HttpServletRequest request)
	{
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", "-1");
		// 解密
		Map<String, String> requstMap = UrlDecOut.decrParam(request, false, LOG);
		if(requstMap.isEmpty())
			return resultMap;
		switch (Integer.parseInt(requstMap.get(RequestEncpParams.HTTPVER))) {
		case 1:
			List<T_phtype> list = ListByType.morePicTypes();
			if(list.isEmpty())
				break;
			resultMap.put("result", list);
			resultMap.put("code", "0");
			break;
		case 2:
			break;
		}
		return resultMap;
	}
	
	//获取封面列表
	public static Map<String, Object> getTypeOver(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", "-1");
		// 解密
		Map<String, String> requstMap = UrlDecOut.decrParam(request, false, LOG);
		if(requstMap.isEmpty())
			return resultMap;
		switch (Integer.parseInt(requstMap.get(RequestEncpParams.HTTPVER))) {
		case 1:
			{
				try{
					ParamType paramType = new ParamType(requstMap.get("type"),
							requstMap.get("page"), requstMap.get("items"));
					List<T_phoatlas> list = ListByType.obatinOver(paramType);
					if(list.isEmpty())
						break;
					resultMap.put("result", list);
					resultMap.put("code", "0");
				}
				catch(Exception e)
				{
					//不足为奇
				}
			}
			
			break;
		case 2:
			break;
		}
		return resultMap;
	}
	
	//获取选中图集的全部图片
	public static Map<String, Object> obatinPhotos(HttpServletRequest request)
	{
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", "-1");
		// 解密
		Map<String, String> requstMap = UrlDecOut.decrParam(request, false, LOG);
		if(requstMap.isEmpty())
			return resultMap;
		switch (Integer.parseInt(requstMap.get(RequestEncpParams.HTTPVER))) {
		case 1:
			T_phoalb[] t_phoalbs = ListByType.obtainPhotos(requstMap.get("pcoid"));
			if(t_phoalbs.length!=0)
			{
				resultMap.put("result", t_phoalbs);
				resultMap.put("code", "0");
			}
			break;
		case 2:
			break;
		}
		return resultMap;
	}
}
