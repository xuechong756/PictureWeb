package com.yshow.pic.server.session;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yshow.pic.contant.GlobelContant;

public class SqlSessionFact{
	
	private static final Logger LOG = LoggerFactory.getLogger(SqlSessionFact.class);
	private static SqlSessionFactory sqlSessionFactory;
	static 
	{
		try(InputStream inputStream = new FileInputStream(GlobelContant.MYBATIS_CONF);)
		{ 
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			LOG.error("", e);
		}
	}
	public static SqlSessionFactory getSqlSessionFactory()
	{
		return SqlSessionFact.sqlSessionFactory;
	}
	
	public static SqlSession getSqlSession()
	{
		return SqlSessionFact.sqlSessionFactory.openSession();
	}
}
