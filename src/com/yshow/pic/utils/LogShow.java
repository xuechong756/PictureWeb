package com.yshow.pic.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

public class LogShow {
	
	private LogShow(){}
	public static void out(final Logger log, Map<String, String[]> map)
	{
		//进行备份，防止map在输出时被主访问线程修改。因为输出用的是子线程，不卡访问线程加快对用户的数据响应。
		final Map<String, String[]> backupMap = new HashMap<>();
		Set<Map.Entry<String, String[]>> setEntry = map.entrySet();
		for(Map.Entry<String, String[]> buf : setEntry)
		{
			backupMap.put(buf.getKey(), buf.getValue());
		}
		new Thread(new Runnable(){
			@Override
			public void run() {
				Set<Map.Entry<String, String[]>> set = backupMap.entrySet();
				for(Map.Entry<String, String[]> en : set)
				{
					for(String value : en.getValue())
					{
						log.info(en.getKey() + ":" + value);
					}
				}
			}
		}).start();
	}

}
