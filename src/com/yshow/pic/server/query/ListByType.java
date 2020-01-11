package com.yshow.pic.server.query;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yshow.pic.dao.db.T_phoalbMapper;
import com.yshow.pic.dao.db.T_phoatlasMapper;
import com.yshow.pic.dao.db.T_phtypeMapper;
import com.yshow.pic.model.db.pic.T_phoalb;
import com.yshow.pic.model.db.pic.T_phoatlas;
import com.yshow.pic.model.db.pic.T_phtype;
import com.yshow.pic.model.local.ParamType;
import com.yshow.pic.server.session.SqlSessionFact;
import com.yshow.pic.utils.StaticTools;

public class ListByType {
	private static final Logger LOG = LoggerFactory.getLogger(ListByType.class);

	public static List<T_phtype> morePicTypes() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFact.getSqlSession();
			T_phtypeMapper t_phtypeMapper = sqlSession.getMapper(T_phtypeMapper.class);
			return t_phtypeMapper.selectallid();
		} catch (Exception e) {
			LOG.error("", e);
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return Collections.emptyList();
	}

	// 更具类型获取图集封面
	public static List<T_phoatlas> obatinOver(ParamType paramType) {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFact.getSqlSession();
			T_phoatlasMapper t_phoatlasMapper = sqlSession.getMapper(T_phoatlasMapper.class);
			List<T_phoatlas> list = t_phoatlasMapper.selectlbsBytype(paramType);
			for (T_phoatlas t_phoatlas : list) {
				t_phoatlas.setSumPic(t_phoatlasMapper.selphcount(t_phoatlas.getPcoId()));
			}
			return list;
		} catch (Exception e) {
			LOG.error("", e);
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return Collections.emptyList();
	}

	// 根据图集id获取当中的图片
	public static T_phoalb[] obtainPhotos(String id) {
		if (StaticTools.isPosiInt(id)) {
			SqlSession sqlSession = null;
			try {
				sqlSession = SqlSessionFact.getSqlSession();
				T_phoalbMapper t_phoalbMapper = sqlSession.getMapper(T_phoalbMapper.class);
				return t_phoalbMapper.selectByPcoid(Integer.parseInt(id));
			} catch (Exception e) {
				LOG.error("", e);
			} finally {
				if (sqlSession != null)
					sqlSession.close();
			}
		}
		return new T_phoalb[0];
	}
}
