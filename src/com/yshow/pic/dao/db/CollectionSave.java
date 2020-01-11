package com.yshow.pic.dao.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yshow.pic.model.db.pic.T_phoalb;
import com.yshow.pic.model.db.pic.T_phoatlas;
import com.yshow.pic.server.session.SqlSessionFact;

public class CollectionSave {
	private static final Logger LOG = LoggerFactory.getLogger(CollectionSave.class);
	//∂‡±Ì≤Â»Î
	public void phoatlas(List<T_phoatlas> list)
	{
		if(list==null || list.isEmpty())
			return;
		SqlSession sqlSession = SqlSessionFact.getSqlSession();
		try
		{
			T_phoatlasMapper t_phoatlasMapper = sqlSession.getMapper(T_phoatlasMapper.class);
			T_phoalbMapper t_phoalbMapper = sqlSession.getMapper(T_phoalbMapper.class);
			for(T_phoatlas t_phoatlas : list)
			{
				t_phoatlasMapper.insertSelective(t_phoatlas);
				List<T_phoalb> phList = t_phoatlas.getT_phoalbList();
				for(T_phoalb t_phoalb : phList)
				{
					t_phoalb.setPalPcoid(t_phoatlas.getPcoId());
					t_phoalbMapper.insertSelective(t_phoalb);
				}
			}
			sqlSession.commit();
		}catch(Exception e)
		{	
			sqlSession.rollback();
			LOG.error("", e);
		}finally{
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
}
