package com.yshow.pic.utils;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 一些公用的全局函数
 * 
 * @author hqm
 * 
 */
public final class GlobalUtils {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalUtils.class);
	private static GlobalUtils obj = null;
	
	private GlobalUtils(){}
	/**
	 * 取得 WEB-INF 目录所在的路径(不包括 WEB-INF 目录自身)
	 */
	public static String getWebInfPath() {
		return getWebInfPath(obj);
	}

	/**
	 * 取得 WEB-INF 目录所在的路径(不包括 WEB-INF 目录自身) 注意： obj 对象必须是 WEB-INF
	 * 子目录下的类对象实例或传入null
	 */
	public static String getWebInfPath(Object obj) {
		if (obj == null) {
			if (GlobalUtils.obj == null)
				GlobalUtils.obj = new GlobalUtils();
			obj = GlobalUtils.obj;
		}
		String path = obj.getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		if (path.startsWith("file://"))
			path = path.substring("file:/".length());
		else if (path.startsWith("file:"))
			path = path.substring("file:".length()); // solaris下测试发现就只有一个斜杠
		try {
			path = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("", e);
		}
		int iPos = path.indexOf("WEB-INF/");
		if (iPos != -1)
			path = path.substring(0, iPos);
		else
			path = "";
		return path.substring(1, path.length());
	}
	
	//获取Tomcat 绝对路径
	public static String getTomcatPath()
	{
		String origina = getWebInfPath();
		if(origina == null)
			return null;
		String[] paths = origina.split("/");
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 1; i < paths.length - 2; i++)
		{
			stringBuilder.append(paths[i] + "/");
		}
		return stringBuilder.toString();
	}

}
