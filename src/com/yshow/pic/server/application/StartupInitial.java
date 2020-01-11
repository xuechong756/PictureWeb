package com.yshow.pic.server.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yshow.pic.contant.GlobelContant;
import com.yshow.pic.dao.file.FileOperat;
import com.yshow.pic.server.buftable.TableBuffer;
import com.yshow.pic.utils.CalendarCus;

public class StartupInitial {
	private static final Logger LOG = LoggerFactory.getLogger(StartupInitial.class);
	public void initial(){
		TableBuffer.getT_baidulocal();
		picPublic();	
	}
	
	//计算开放的图片数量
	private void picPublic()
	{
		Properties properties = new FileOperat().readConfPic();
		String pics = properties.getProperty("pubpic", "200");
		String date = properties.getProperty("reference", null);
		Date referenceDate = null;
		if(date == null)
		{
			referenceDate = Calendar.getInstance().getTime();
		}
		else	
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				referenceDate = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				LOG.error("", e);
			}
		}
		int days = 1;
		if(referenceDate!=null)
		{
			days = (int)CalendarCus.getSubDays(Calendar.getInstance().getTime().getTime(), referenceDate.getTime());
		}
		GlobelContant.MAX_CURRENT = days*Integer.parseInt(pics);
	}
}
