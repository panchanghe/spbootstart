package com.ch.one.project.mapper;

import com.ch.one.project.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface GoodsMapper {

    int deleteByPrimaryKey(Integer gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int batchInsert(List<Goods> list);

}