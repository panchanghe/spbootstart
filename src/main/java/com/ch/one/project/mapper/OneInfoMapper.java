package com.ch.one.project.mapper;

import com.ch.one.project.entity.OneInfo;
import com.ch.one.project.entity.OneInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OneInfoMapper {
    long countByExample(OneInfoExample example);

    int deleteByExample(OneInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OneInfo record);

    int insertSelective(OneInfo record);

    List<OneInfo> selectByExample(OneInfoExample example);

    OneInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OneInfo record, @Param("example") OneInfoExample example);

    int updateByExample(@Param("record") OneInfo record, @Param("example") OneInfoExample example);

    int updateByPrimaryKeySelective(OneInfo record);

    int updateByPrimaryKey(OneInfo record);

    int batchInsert(List<OneInfo> list);

    int batchUpdate(List<OneInfo> list);
}