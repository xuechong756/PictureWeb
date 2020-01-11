package com.yshow.pic.dao.db;

import java.util.List;

import com.yshow.pic.model.db.pic.T_phtype;

public interface T_phtypeMapper {
    int deleteByPrimaryKey(Integer hptpId);

    int insert(T_phtype record);

    int insertSelective(T_phtype record);

    T_phtype selectByPrimaryKey(Integer hptpId);
    int updateByPrimaryKeySelective(T_phtype record);
    List<T_phtype> selectallid();

    int updateByPrimaryKey(T_phtype record);
}