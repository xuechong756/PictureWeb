package com.yshow.pic.dao.db;

import com.yshow.pic.model.db.pic.T_phoalb;

public interface T_phoalbMapper {
    int deleteByPrimaryKey(Integer palId);

    int insert(T_phoalb record);

    int insertSelective(T_phoalb record);

    T_phoalb selectByPrimaryKey(Integer palId);
    T_phoalb[] selectByPcoid(Integer pcoid);
    
    int updateByPrimaryKeySelective(T_phoalb record);

    int updateByPrimaryKey(T_phoalb record);
}