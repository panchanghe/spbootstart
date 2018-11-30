package com.ch.one.project.dao;

import com.ch.one.project.entity.Goods;
import com.ch.one.project.mapper.GoodsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/17 15:10
 * @Description:
 */
@Mapper
public interface GoodsDao extends GoodsMapper {
	List<Goods> getAllGoods();
}
