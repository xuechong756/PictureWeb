package com.yshow.pic.server.buftable;

import org.apache.ibatis.session.SqlSession;

import com.yshow.pic.dao.db.T_baidulocalMapper;
import com.yshow.pic.model.db.pic.T_baidulocal;
import com.yshow.pic.server.session.SqlSessionFact;

public class TableBuffer {
	private static T_baidulocal[] t_baidulocal;
	
	public static T_baidulocal[] getT_baidulocal()
	{
		if(t_baidulocal == null)
		{
			synchronized(String.CASE_INSENSITIVE_ORDER) {
				if(t_baidulocal == null)
				{
					SqlSession sqlSession = SqlSessionFact.getSqlSession();
					T_baidulocalMapper t_baidulocalMapper = sqlSession.getMapper(T_baidulocalMapper.class);
					t_baidulocal = t_baidulocalMapper.selectall();
					sqlSession.close();
				}
			}
		}
		return t_baidulocal;
	}
}
