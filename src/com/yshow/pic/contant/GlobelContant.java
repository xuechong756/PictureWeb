package com.yshow.pic.contant;

import com.yshow.pic.utils.GlobalUtils;

public class GlobelContant {
	// mybatis 配置路径
	public static final String MYBATIS_CONF = GlobalUtils.getWebInfPath() + "WEB-INF/mybatis-config.xml";
	// 前端传过来的itesm, 这里设置允许最大返回的条数
	public static final int ITEMS_MAX = 30;
	// 每天允许开放的图集数量在WEB-INF中配置
	public static int MAX_CURRENT;
	// 此配置文件路径
	public static final String CONFPIC_PATH = GlobalUtils.getWebInfPath() + "WEB-INF/db/conf/publicpics.properties";
}
