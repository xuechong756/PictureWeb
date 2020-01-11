package com.yshow.pic.dao.db;

import java.util.List;

import com.yshow.pic.model.db.pic.T_phoatlas;
import com.yshow.pic.model.local.ParamType;

public interface T_phoatlasMapper {
    int deleteByPrimaryKey(Integer pcoId);

    int insert(T_phoatlas record);

    int insertSelective(T_phoatlas record);

    T_phoatlas selectByPrimaryKey(Integer pcoId);
    T_phoatlas selectCollectByKey(Integer pcoId);
    List<T_phoatlas> selectlbsBytype(ParamType paramtype);
    int selphcount(Integer id);
    int updateByPrimaryKeySelective(T_phoatlas record);

    int updateByPrimaryKey(T_phoatlas record);
}