package com.yshow.pic.contant;

import com.yshow.pic.utils.GlobalUtils;

public class GlobelContant {
	// mybatis ����·��
	public static final String MYBATIS_CONF = GlobalUtils.getWebInfPath() + "WEB-INF/mybatis-config.xml";
	// ǰ�˴�������itesm, ��������������󷵻ص�����
	public static final int ITEMS_MAX = 30;
	// ÿ�������ŵ�ͼ��������WEB-INF������
	public static int MAX_CURRENT;
	// �������ļ�·��
	public static final String CONFPIC_PATH = GlobalUtils.getWebInfPath() + "WEB-INF/db/conf/publicpics.properties";
}
