package com.yshow.pic.contant;

import java.util.HashMap;
import java.util.Map;

public class PicContant {

	// 图片下载地址
	static final String PIC_URL_BASE = "http://apis.baidu.com/showapi_open_bus/pic/pic_search";
	public static final Map<String, String> requestHeader;

	static {
		requestHeader = new HashMap<>();// 百度图片key
		requestHeader.put("apikey", "047e583a3713b7db3b61c0f2069d3e46");
	}

	public static String getPicURL(int type, int page) {
		return PicContant.PIC_URL_BASE + "?type=" + type + "&page=" + page;
	}
}
