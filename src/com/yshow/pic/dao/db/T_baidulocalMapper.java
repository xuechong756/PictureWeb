package com.yshow.pic.dao.db;

import com.yshow.pic.model.db.pic.T_baidulocal;

public interface T_baidulocalMapper {
    int deleteByPrimaryKey(Integer blId);

    int insert(T_baidulocal record);

    int insertSelective(T_baidulocal record);

    T_baidulocal selectByPrimaryKey(Integer blId);
    T_baidulocal[] selectall();
    Integer selectTypeBybaiduid(Integer blBaiduid);
    int updateByPrimaryKeySelective(T_baidulocal record);

    int updateByPrimaryKey(T_baidulocal record);
}